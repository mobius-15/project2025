package flightplan;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import carrierOps.Carrier;
import logic.AircraftsLogic;

public class FlightPlan2{
	Waypoint wp;
	private int wpCount;
	private String id;

	public int getWpCount() {
		return wpCount;
	}

	public void setWpCount(int wpCount) {
		this.wpCount = wpCount;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private List<Waypoint> waypoints = new ArrayList<>();
	private Carrier carrier;
  
public void specify (int wayPoint,double startLat,double startLon) throws SQLException{
	AircraftsLogic aLogic = new AircraftsLogic();
	waypoints.add(Waypoint.createStartPoint(startLat, startLon, 35000, 450));

	for (int s = 0; s < wayPoint; s++) {
		setWpCount(getWpCount() + 1);

		int altitude = (int)(Math.random() * 40000 + 5000);
		double distance = Math.random() * 250 + 50;
		int heading = (int)(Math.random() * 360);
		int cas = 250;
		
		double tas = aLogic.calculateTAS(altitude, cas);
		double mach = aLogic.calculateMach(altitude, cas);
		double segmentTime = aLogic.calculateSegmentTime(distance, tas);
		
		Waypoint prev=waypoints.get(waypoints.size()-1);

		Waypoint wp = new Waypoint(0, 0, altitude, (int)tas, distance, heading, segmentTime, mach);
		wp.calculatePosition(prev.getLatitude(),prev.getLongitude());
		waypoints.add(wp);
	}
}

	public List<Waypoint>getWaypoints(){
		return waypoints;
}
	public void setWaypoints(List<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}
	public Carrier getCvn() {
		return carrier;
	}
	public void setCvn(Carrier carrier) {
		this.carrier = carrier;
	}
	
	public void updateWaypointCoordinates(double startLat, double startLon) {
	    double currentLat = startLat;
	    double currentLon = startLon;

	    for (int i = 0; i < waypoints.size(); i++) {
	        Waypoint wp = waypoints.get(i);
	        if (i == 0) {
	            wp.setLatitude(currentLat);
	            wp.setLongitude(currentLon);
	        } else {
	            wp.calculatePosition(currentLat, currentLon);
	            currentLat = wp.getLatitude();
	            currentLon = wp.getLongitude();
	        }
	    }
	}
	
	public String exportWaypointsAsJson() {
	    StringBuilder json = new StringBuilder("[");
	    for (int i = 0; i < waypoints.size(); i++) {
	        Waypoint wp = waypoints.get(i);
	        json.append(String.format(
	            "{\"lat\":%.6f, \"lon\":%.6f, \"alt\":%d, \"speed\":%d, \"dist\":%.1f, \"hdg\":%d}",
	            wp.getLatitude(), wp.getLongitude(), wp.getAltitude(), wp.getSpeed(),
	            wp.getDistance(), wp.getHeading()
	        ));
	        if (i < waypoints.size() - 1) json.append(",");
	    }
	    json.append("]");
	    return json.toString();
	}

}	

	
