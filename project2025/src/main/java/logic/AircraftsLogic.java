package logic;

import java.util.ArrayList;
import java.util.List;

import aircrafts.Aircrafts;
import dao.AtmosphereDAO;
import flightplan.Atmosphere;
import flightplan.Waypoint;
import weapons.Weapons;

public class AircraftsLogic {
		
	    public double calculateTAS(int altitude, int cas) {
	     Atmosphere data = AtmosphereDAO.fetchDataByAltitude(altitude);
	        return cas / Math.sqrt(data.getDensityRatio()); 
	 }

	    public double calculateMach(int altitude,int cas) {
	    Atmosphere data = AtmosphereDAO.fetchDataByAltitude(altitude);
	        return  calculateTAS(altitude,cas)* 0.514444 / data.getSonicSpeed();
	    }

	    /**
	     * 区間時間（分）を計算
	     */
	    public double calculateSegmentTime(double distanceNm, double tasKt) {
	        return (distanceNm/tasKt)*60;
	    }

	    /**
	     * 指定された数のダミーWPを自動生成（高度、距離、速度ランダム）
	     */
	    public static List<Waypoint> generateRandomWaypoints(int count, double startLat, double startLon) {
	        List<Waypoint> list = new ArrayList<>();
	        list.add(Waypoint.createStartPoint(startLat, startLon, 35000, 450));
	        AircraftsLogic aLogic=new AircraftsLogic();
	        for (int i = 0; i < count; i++) {
	            int altitude = (int) (Math.random() * 40000 + 5000);
	            double distance = Math.random() * 150 + 50;
	            int heading = (int) (Math.random() * 360);
	            
	            double tas = aLogic.calculateTAS(altitude, 250);
	            double mach = aLogic.calculateMach(altitude, 250);
	            
	            Waypoint wp = new Waypoint(0, 0, altitude, (int) tas, distance, heading,
	                    aLogic.calculateSegmentTime(distance, tas), mach);
	            list.add(wp);
	        }

	        return list;
	    }
		public int rateOfClimb(int ceilingAlt,Aircrafts aircraft) {
			return aircraft.getCeilingAlt()/10;
		}
		public double cap(List<Waypoint> waypoints, int wpIndex, int orbitTimeMin,Aircrafts aircraft) {
		    if (wpIndex < 0 || wpIndex >= waypoints.size()) return 0;

//		    Waypoint wp = waypoints.get(wpIndex);
		    double radiusNm = 10.0; // 固定周回半径
		    double throttle = 0.4;

		    return FuelLogic.orbit(aircraft,radiusNm, orbitTimeMin, throttle);
	}
		public void addWeapon(List<Weapons>weaponList,Weapons weapon) {
			weaponList.add(weapon);
		}


	    public void clearLoadout(List<Weapons>weaponList) {	        
	        weaponList.clear();
	    }
	    
}