package aircrafts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class FA_18 extends Aircrafts{	
		private  int extFuel;
		private double remainingFuel;
		private int a_bThrust;
		private int totalFuel;
		int nTank;
		Random random=new Random();

 public FA_18() {
			setName("F/A-18C");
			setType("multiRole");
			setNEngine(2);
			setCruiseSpeed(480);
			setCeilingAlt(50000);
			setIntfuel(10800);
			setSfc(0.81);
			setMilThrust(11000);
			setEmptyWeight(25100);
			setFullWeight(50200);
			this.a_bThrust=17700;
			setWeaponized(true);
			setnTank(1);
		}
		public int rateOfClimb(int ceilingAlt) {
			return getCeilingAlt()/10;
		}
		public void afterBurner() {
			setSfc(1.74);
		
		}
		public void calculateFuelUsage(List<Waypoint>waypoints,int nTank) {
		    totalFuel = getIntFuel()+(extFuel*nTank); // 燃料を取得
		    remainingFuel=totalFuel;
					
		   for (int i=0 ; i< waypoints.size();i++) {
		    	Waypoint wp =waypoints.get(i);
		    	double timeHours =wp.getDistance()/(double)wp.getSpeed();
		    
		    	double fuelUsed;		               
		        if(i>0 && wp.getAltitude() >waypoints.get(i-1).getAltitude()) {
				    fuelUsed =(fuel()+climbingFF())*timeHours;
				}else {
				  fuelUsed=fuel()*timeHours;
				    }
		      remainingFuel -= fuelUsed;
		        
		        if(remainingFuel < 100) {remainingFuel=100; break; }
		    }
	          		    		    
		}   
	public double climbingFF() {
				double abflow=getSfc()*this.a_bThrust*getnEngine()*random.nextDouble(0.05);
				return abflow;

	}
	public double fuel() {
			double fuel=getFuelFlow()*getnEngine();
			return fuel;
	}
	public List<Double> calculateSegmentFuel(List<Waypoint> waypoints){
		 List<Double> fuelLog = new ArrayList<>();
		    for (int i=0 ; i< waypoints.size();i++) {
		    	Waypoint wp =waypoints.get(i);		    
		        double timeHours = wp.getSegmentTime() / 3600.0;
		        double fuelUsed = this.fuel() * getNEngine() * timeHours;
		        //1つ前のwp.getAltitudeが次のwpより低い場合
		        if(i>0 && wp.getAltitude() >waypoints.get(i-1).getAltitude()) {
				     
				    fuelUsed =(fuel()+climbingFF())*timeHours;
				  }else {
				      fuelUsed=fuel()*timeHours;
				    }
		        fuelLog.add(fuelUsed);
		    }
		    return fuelLog;
	}
	public double orbit(double radiusNm, double durationMin, double throttleRatio) {
	    // 出力制限に応じて推力とSFCを調整（仮の簡易モデル）
	    double thrust = getMilThrust() * throttleRatio;
	    double adjustedSFC = getSfc() * throttleRatio;

	    double fuelFlow = thrust * adjustedSFC * getNEngine(); // lb/h
	    double timeHours = durationMin / 60.0;

	    return fuelFlow * timeHours; 
	}
	public double cap(List<Waypoint> waypoints, int anchorIndex, double orbitTimeMin) {
	    if (anchorIndex < 0 || anchorIndex >= waypoints.size()) return 0;

	    Waypoint wp = waypoints.get(anchorIndex);
	    double radiusNm = 10.0; // 固定周回半径
	    double throttle = 0.4;

	    return orbit(radiusNm, orbitTimeMin, throttle);
	}
	    public  int getExtFuel() {
	        return extFuel;
	    }

	    public void setExtFuel(int extFuel) {
	        this.extFuel = extFuel;
	    }

	    // **残燃料取得**
	    public double getRemainingFuel() {
	        return remainingFuel;
	    }
		public int getnTank() {
			return nTank;
		}
		public void setnTank(int nTank) {
			this.nTank = nTank;
		}
	}

