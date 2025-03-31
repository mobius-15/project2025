package aircrafts;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import carrierOps.CVN;
import dao.AtmosphereDAO;

public class FlightPlan2 extends Aircrafts{

	private List<Waypoint> waypoints = new ArrayList<>();
	private CVN cvn;
    
public static class AtmosphereData{public double densityRatio,sonicSpeed;
     public AtmosphereData (double ratio,double speed){
        this.densityRatio = ratio;
        this.sonicSpeed = speed;}
    }

public AtmosphereData getDataBase(int altitude)throws SQLException {
	return AtmosphereDAO.fetchDataByAltitude(altitude);	
    
}

public double convertTAS(int altitude,int cas){
			 
	try {AtmosphereData data = getDataBase(altitude);
	return cas / Math.sqrt(data.densityRatio);
	} catch (SQLException e) {
		System.err.println("Can't Convert:"+e.getMessage());
	return cas;	
	}	
}
public double getSonicSpeed(int altitude) {
	try {AtmosphereData data = getDataBase(altitude);	
	return data.sonicSpeed;
	}catch(SQLException e){
		System.err.println("Couldn't Get:"+e.getMessage());
		return 340.29;
	}
}
public void specify (int wayPoint,double startLat,double startLon) throws SQLException{
	waypoints.add(Waypoint.createStartPoint(startLat, startLon, 35000, 450));
	for(int s=0;s<wayPoint;s++){
		super.wpCount++;
		
	int altitude = Math.max(100, Math.min(50000, (int) (Math.random() * 50000)));
	AtmosphereData data=getDataBase(altitude);
	double tas=cas/Math.sqrt(data.densityRatio);
	double sonicSpeed=data.sonicSpeed;
	double mach=tas*0.514444/sonicSpeed;
    int speed = (int)Math.max(130, Math.min(1000, (int) (250 * (1 / Math.sqrt(data.densityRatio)))));
    
    double distance = Math.max(5, Math.min(300, Math.random() * 300));
    int heading = (int) (Math.random() * 360);
    
    double segmentTime=(distance*3600)/tas;
    
    Waypoint wp=new Waypoint(0, 0, altitude, speed, distance, heading, segmentTime, mach, sonicSpeed);
//    wp.setMach(mach);
//    wp.setSonicSpeed(sonicSpeed);
    waypoints.add(wp);
		}
	}

	public List<Waypoint>getWaypoints(){
		return waypoints;
}
	public void setWaypoints(List<Waypoint> waypoints) {
		this.waypoints = waypoints;
	}
	public CVN getCvn() {
		return cvn;
	}
	public void setCvn(CVN cvn) {
		this.cvn = cvn;
	}
}	

	
