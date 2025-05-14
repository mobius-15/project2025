package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import aircrafts.Loadout;
import flightplan.FlightPlan2;
import flightplan.Waypoint;
import mission.MissionContext;
import mission.Target;
import weapons.Weapons;
public class MissionDataDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/air_strike?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public void saveFlightPlan(FlightPlan2 plan, String missionId, String missionType, String aircraftModex,
			String carrierName, double totalTime) throws SQLException {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

			// 1. ミッション全体を flightplans に保存
			String planSQL = "INSERT INTO flightplans (id, mission_type, total_flight_time, aircraft_modex, carrier_name) VALUES (?, ?, ?, ?, ?)";
			try (PreparedStatement ps = conn.prepareStatement(planSQL)) {
				ps.setString(1, missionId);
				ps.setString(2, missionType);
				ps.setDouble(3, totalTime);
				ps.setString(4, aircraftModex);
				ps.setString(5, carrierName);
				ps.executeUpdate();
			}

			// 2. 各Waypointを waypoints に保存
			String wpSQL = "INSERT INTO waypoints (plan_id, wp_index, altitude, speed, distance, heading, segment_time, mach, latitude, longitude) "
					+
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			try (PreparedStatement ps = conn.prepareStatement(wpSQL)) {
				List<Waypoint> wps = plan.getWaypoints();
				for (int i = 0; i < wps.size(); i++) {
					Waypoint wp = wps.get(i);
					ps.setString(1, missionId);
					ps.setInt(2, i);
					ps.setInt(3, wp.getAltitude());
					ps.setInt(4, wp.getSpeed());
					ps.setDouble(5, wp.getDistance());
					ps.setInt(6, wp.getHeading());
					ps.setDouble(7, wp.getSegmentTime());
					ps.setDouble(8, wp.getMach());
					ps.setDouble(9, wp.getLatitude());
					ps.setDouble(10, wp.getLongitude());
					ps.addBatch();
				}
				ps.executeBatch();
			}
		}
	}

	public FlightPlan2 loadFlightPlan(String missionId) {
		String sql = "SELECT * FROM flight_plans WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, missionId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				FlightPlan2 plan = new FlightPlan2();
				// mission名やmodexなどもセット可能ならここで

				String waypointsJson = rs.getString("waypoints");

				// JSON → List<Waypoint> に変換（Jacksonなどを利用）
				ObjectMapper mapper = new ObjectMapper();
				List<Waypoint> waypoints
				= mapper.readValue(	waypointsJson, new TypeReference<List<Waypoint>>(){});
				plan.setWaypoints(waypoints);

				return plan;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteFlightPlan(String id) {
		String sql = "DELETE FROM flight_plans WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, id);
			int affected = stmt.executeUpdate();
			return affected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public void saveLoadout(String missionId, List<Loadout> loadouts) {
	    String sql = "INSERT INTO loadouts (mission_id, station_number, weapon_name, weapon_weight, category) VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        for (Loadout l : loadouts) {
	            stmt.setString(1, missionId);
	            stmt.setInt(2, l.getStationNumber());
	            if (l.getWeapons() != null) {
	                stmt.setString(3, l.getWeapons().getName());
	                stmt.setDouble(4, l.getWeapons().getWeightlb());
	                stmt.setString(5, l.getWeapons().getCategory());
	            } else {
	                stmt.setNull(3, java.sql.Types.VARCHAR);
	                stmt.setNull(4, java.sql.Types.DOUBLE);
	                stmt.setNull(5, java.sql.Types.VARCHAR);
	            }
	            stmt.addBatch();
	        }
	        stmt.executeBatch();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void saveTargets(String missionId, List<Target> targets) {
	    if (missionId == null || missionId.isEmpty()) {
	        throw new IllegalArgumentException("Mission ID is required for saving targets");
	    }
	    if (targets == null || targets.isEmpty()) {
	        System.out.println("No targets to save for mission ID: " + missionId);
	        return;
	    }

	    String deleteSQL = "DELETE FROM target_points WHERE mission_id = ?";
	    String insertSQL = "INSERT INTO target_points (mission_id, name, lat, lon, radius) VALUES (?, ?, ?, ?, ?)";

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

	        // 先に削除
	        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL)) {
	            deleteStmt.setString(1, missionId);
	            deleteStmt.executeUpdate();
	        }

	        // 新たに追加
	        try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
	            for (Target tp : targets) {
	                insertStmt.setString(1, missionId);
	                insertStmt.setString(2, tp.getName());
	                insertStmt.setDouble(3, tp.getLat());
	                insertStmt.setDouble(4, tp.getLon());
	                insertStmt.setDouble(5, tp.getRadius());
	                insertStmt.addBatch();
	            }
	            insertStmt.executeBatch();
	        }

	    } catch (SQLException e) {
	        System.err.println("Error saving targets for mission ID: " + missionId);
	        e.printStackTrace();
	    }
	}
	  public List<Loadout> loadLoadout(String missionId) {
		    List<Loadout> list = new ArrayList<>();
		    String sql = "SELECT station_number, weapon_name, weapon_weight, category FROM loadouts WHERE mission_id = ? ORDER BY station_number ASC";

		    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		         PreparedStatement stmt = conn.prepareStatement(sql)) {

		        stmt.setString(1, missionId);
		        ResultSet rs = stmt.executeQuery();

		        while (rs.next()) {
		            int stationNumber = rs.getInt("station_number");
		            String weaponName = rs.getString("weapon_name");
		            double weight = rs.getDouble("weapon_weight");
		            String category = rs.getString("category");

		            Loadout loadout = new Loadout(stationNumber);
		            if (weaponName != null) {
		                Weapons weapon = new Weapons();
		                weapon.setName(weaponName);
		                weapon.setWeightlb(weight);
		                weapon.setCategory(category);
		                loadout.assignWeapons(weapon);
		            }
		            list.add(loadout);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return list;
		}

	    public List<Target> loadTargets(String missionId) {
	        List<Target> list = new ArrayList<>();
	        String sql = "SELECT name, lat, lon, radius FROM target_points WHERE mission_id = ?";

	        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	             PreparedStatement stmt = conn.prepareStatement(sql)) {

	            stmt.setString(1, missionId);
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                Target tp = new Target(null,0,0);
	                tp.setName(rs.getString("name"));
	                tp.setLat(rs.getDouble("lat"));
	                tp.setLon(rs.getDouble("lon"));
	                tp.setRadius(rs.getDouble("radius"));
	                list.add(tp);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	    public void saveMissionContext(MissionContext ctx) throws SQLException {
	        String missionId = ctx.getFlightPlan().getId();

	        saveFlightPlan(ctx.getFlightPlan(),ctx.getFlightPlan().getId(),ctx.getMissionType(),ctx.getAircraft().getModex(),ctx.getCarrier().getName(),ctx.getTotalFlightTime());
	        saveTargets(missionId, ctx.getTargetPoints());
	        saveLoadout(missionId, ctx.getLoadouts());
	    }
	}
