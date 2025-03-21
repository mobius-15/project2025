package aircrafts;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightPlan2 extends Aircrafts{

	private List<Waypoint> waypoints = new ArrayList<>();
	
    private static final String URL
= "jdbc:mysql://localhost:3306/standard_atmosphere?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root"; 
    private static final String PASSWORD = "qq3g9yp"; 

	public static  Connection getConnection() throws SQLException  {

		return DriverManager.getConnection(URL,USER,PASSWORD);
		
	}
	public void specify (int wayPoint,double startLat,double startLon){
				
		try(Connection conn=getConnection()) {
			waypoints.add(Waypoint.createStartPoint(startLat, startLon, 35000, 450));
	for(int s=0;s<wayPoint;s++){super.wpCount++;
	
	int altitude = Math.max(100, Math.min(50000, (int) (Math.random() * 50000)));
    double densityRatio = getDataBase(conn, altitude);
    int speed = Math.max(130, Math.min(1000, (int) (250 * (1 / Math.sqrt(densityRatio)))));
    double distance = Math.max(5, Math.min(300, Math.random() * 300));
    int heading = (int) (Math.random() * 360);
    
    waypoints.add(new Waypoint(0, 0, altitude, speed, distance, heading,0));
				}
			}catch(SQLException e) {
				System.err.println("SQLError: " + e.getMessage());
		}
		
	}
		public List<Waypoint>getWaypoints(){
			return waypoints;
	}
	private double getDataBase(Connection conn,int altitude)throws SQLException {
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT density_ratio FROM altitude LEFT JOIN density ON altitude.id=density.id ")
		.append("WHERE feet = (SELECT feet FROM altitude WHERE feet <= ? ORDER BY ABS(feet - ?) LIMIT 1) ");
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
			pstmt.setInt(1,altitude);
			pstmt.setInt(2,altitude);
			ResultSet rs=pstmt.executeQuery();
		
			if(rs.next()){return rs.getDouble("density_ratio");
			}else {	System.err.println("取得できず"); 
			return super.ρρ0;}
		}
	    
	}
	public void setWaypoints(List<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}
}	

	
