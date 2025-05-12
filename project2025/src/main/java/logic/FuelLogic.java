package logic;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import aircrafts.Aircrafts;
import flightplan.Waypoint;

public class FuelLogic {
	Random random =new Random();

	public double fuel(Aircrafts aircraft) {
		double fuel=aircraft.getFuelFlow()*aircraft.getnEngine();
		return fuel;

	}
	public static double orbit(Aircrafts aircraft,double radiusNm, double durationMin, double throttleRatio) {
	    // 出力制限に応じて推力とSFCを調整（仮の簡易モデル）
	    double thrust = aircraft.getMilThrust() * throttleRatio;
	    double adjustedSFC = aircraft.getSfc() * throttleRatio;

	    double fuelFlow = thrust * adjustedSFC * aircraft.getNEngine(); // lb/h
	    double timeHours = durationMin / 60.0;

	    return fuelFlow * timeHours; 
	}
	public double climbingFF(Aircrafts aircraft) {// A/B使用を最大0.05時間ずつと仮定してランダム燃料増加
		double abflow=aircraft.getSfc()*aircraft.getA_bThrust()*aircraft.getnEngine()*random.nextDouble(0.05);
		return abflow;

}
	public List<Double> calculateSegmentFuel(List<Waypoint> waypoints,Aircrafts aircraft){
		 List<Double> fuelLog = new ArrayList<>();
		    for (int i=0 ; i< waypoints.size();i++) {
		    	Waypoint wp =waypoints.get(i);		    
		        double timeHours = wp.getSegmentTime() / 60.0;
		        double fuelUsed = this.fuel(aircraft)  * timeHours;
		        //1つ前のwp.getAltitudeが次のwpより低い場合
		        if(i>0 && wp.getAltitude() >waypoints.get(i-1).getAltitude()) {
				     
				    fuelUsed =(fuel(aircraft)+climbingFF(aircraft))*timeHours;
				  }else {
				      fuelUsed=fuel(aircraft)*timeHours;
				    }
		        fuelLog.add(fuelUsed);
		    }
		    return fuelLog;
	}
	public void calculateFuelUsage(List<Waypoint>waypoints,int nTank,Aircrafts aircraft) {
		aircraft.setTotalFuel(aircraft.getIntFuel()+(aircraft.getExtFuel()*aircraft.getnTank())); // 燃料を取得
		aircraft.setRemainingFuel(aircraft.getTotalFuel());
				
	   for (int i=0 ; i< waypoints.size();i++) {
	    	Waypoint wp =waypoints.get(i);
	    	double timeHours =wp.getDistance()/(double)wp.getSpeed();
	    
	    	double fuelUsed;		               
	        if(i>0 && wp.getAltitude() >waypoints.get(i-1).getAltitude()) {
			    fuelUsed =(fuel(aircraft)+climbingFF(aircraft))*timeHours;
			}else {
			  fuelUsed=fuel(aircraft)*timeHours;
			    }
	        aircraft.setRemainingFuel(aircraft.getRemainingFuel()-fuelUsed);
	        
	        if(aircraft.getRemainingFuel()< 100) {aircraft.setRemainingFuel(100); break; }
	    }
	}
}