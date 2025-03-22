package aircrafts;
import java.util.List;

public abstract class Aircrafts {
	private String name,type;
	String mission;
	private int nEngine;
	private int cruiseSpeed;
	private int combatRange,ferryRange;
	private int ceilingAlt;
	private int milThrust;// 推力 (N)
	private int intfuel,emptyWeight,fullWeight;
//	boolean loadout;
	int wpCount;
	int loadout;
	 double fuelFlow; 
	int rpm;
	int cas,tas;
	int altitude,distance;

	static final double ρ0=1.225;//海面上での標準大気密度(kg/m3)
	double ρρ0;
//	FlightPlan distance;
	double sfc;
	private boolean weaponized;
	boolean carrierops;
	 private String modex;
//	List<int[]>fpList;
	public int getFullWeight() {
		return this.fullWeight;
	}
	public void setFullWeight(int fullWeight) {
		this.fullWeight=fullWeight;
	}
	public int getNEngine() {
	return this.nEngine;
}
	public void setNEngine(int nEngine) {
	this.nEngine=nEngine;
	}
	public int getCruiseSpeed() {
		return this.cruiseSpeed;
	}
	public void setCruiseSpeed(int cruiseSpeed) {
		if(cruiseSpeed<=100){throw new IllegalArgumentException("Stall");}
		this.cruiseSpeed=cruiseSpeed;
	}
	public int getCeilingAlt() {
		return this.ceilingAlt;
	}
	public void setCeilingAlt(int ceilingAlt) {

		this.ceilingAlt=ceilingAlt;
	}
	public int getIntFuel() {
		return this.intfuel;
	}
	public void setIntFuel(int intFuel){
		this.intfuel=intFuel;
	}
	public int getnEngine() {
		return nEngine;
	}
	public void setnEngine(int nEngine) {
		this.nEngine = nEngine;
	}
	public int getCombatRange() {
		return combatRange;
	}
	public void setCombatRange(int combatRange) {
		this.combatRange = combatRange;
	}
	public int getFerryRange() {
		return ferryRange;
	}
	public void setFerryRange(int ferryRange) {
		this.ferryRange = ferryRange;
	}
	public int getMilThrust() {
		return milThrust;
	}
	public void setMilThrust(int milThrust) {
		this.milThrust = milThrust;
	}
	public int getIntfuel() {
		return intfuel;
	}
	public void setIntfuel(int intfuel) {
		this.intfuel = intfuel;
	}
	public double getFuelFlow() {
		fuelFlow=getMilThrust()*getSfc();
		return fuelFlow;
	}
	public void setFuelFlow(double fuelFlow) {
		this.fuelFlow = fuelFlow;
	}
	public int getRpm() {
		return rpm;
	}
	public void setRpm(int rpm) {
		this.rpm = rpm;
	}
	public int getTas() {
		return tas;
	}
	public void setTas(int Tas) {
		this.tas = Tas;
	}

	public double getSfc() {
		return sfc;
	}
	public void setSfc(double sfc) {
		this.sfc = sfc;
	}
	public String getType() {
		return this.type;
	}
//	public String toString() {
//		return this.name+":"+this.type+":";
//	}
	public void setType(String type) {
		this.type = type;
	}
	public void launch(String name ) {

		System.out.println(name+"を発艦");
	}
//	public static Aircrafts createAircrafts() {
//		return new Aircrafts();
//	}
	public boolean equals(Object o) {
		if(this==o) {return true;}
		if(o instanceof Aircrafts) {
		if(this.type.trim().equals(this.type)) {return true;}
		}return false;
	}
	public double tas(double ρ) {
		double tas=cas/(Math.sqrt(1/(this.ρ0/ρ)));
		return tas;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	
	}
    public void modex(List<String> squadronCodes) {
        System.out.println("Squadron Modex:");
        for (String code : squadronCodes) {
            System.out.println(code);
        }
    }
	public String getModex() {
		return modex;
	}
	public void setModex(String modex) {
		this.modex = modex;
	}
	public boolean isWeaponized() {//boolean型getter
		return weaponized;
	}
	public void setWeaponized(boolean weaponized) {
		this.weaponized = weaponized;
	}
	public double calculateTSFC() {

	    if (milThrust > 0) {
	       this.sfc=(double)( fuelFlow / milThrust); // TSFCの計算
	    }   return sfc;

	}
	public double calculateDensityRatio(double altitude) {
	        // 標準大気モデルに基づく密度比の簡易計算
	       return Math.exp(-altitude / 10000); // 1万フィートごとにおよそ63%減少
	}

	 public int calculateTAS() {
	        ρρ0 = calculateDensityRatio(this.altitude);
	        this.tas=(int)(cas*(1/Math.sqrt(ρρ0)));
	        return  tas;// 真対気速度 (TAS) 計算
 
	 }
	public int getEmptyWeight() {
		return emptyWeight;
	}
	public void setEmptyWeight(int emptyWeight) {
		this.emptyWeight = emptyWeight;
		

	}
	
}
