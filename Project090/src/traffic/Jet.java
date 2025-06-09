package traffic;
import java.util.Scanner;
public class Jet {

		 String type;
		 String mission;
		private double cruiseSpeed;
		 int elements;
		 int wpDirection;
		 int distance=0;
		 int wayPoints=13;
		private int eWeight;
		private int intTank;
		int speed;
		int hour;
		 int fullWeight;
		private int combatRadius;
		private int stallSpeed=130;
		int fuel;
		private double fuelConsumption;
		private int maxAltitude;
		private int cruiseAltitude;
		int count=0;
		Weapons weapon;
		Airbase base;
		
	Jet(String type,String mission){
			this.type=type;
			this.mission=mission;
			this.elements*=2;
			this.fuel=fuel;
			this.maxAltitude=50000;
			this.cruiseSpeed=0.81;
		}
	public int getEWeight() {
		return this.eWeight;
	}
	public void setEWeight(int eWeight) {
		this.eWeight=eWeight;
	}
	public void printData(){
	    System.out.println("機種："+ this.type);
	    System.out.println("機数："+ this.elements);
	    System.out.println("飛行距離："+ this.distance+"nm");
	    System.out.println("燃料："+this.fuel+" lb");
	    System.out.println("総重量："+this.fullWeight+" lb");
	}
	public void information(Airbase base) {
		
		System.out.println(base.name+":"+base.getMovingSpeed()+"ノットで航行中。"+"進路は"+base.heading);	
		System.out.println("任務："+this.mission);
		System.out.println(base.name+"所属機:"+this.type+"\n"+"機数は"+(this.elements)*2+"機");
		
	}

	public int elements(int sorties) {
		int planes=sorties*this.elements;
		return planes;
	}
	public int[]fly(int distance) {
		
	  int[]wayPoints= new int[distance];
	  
		for(int w=0;w<wayPoints.length;w++){System.out.println("WP間の距離を入力");	
		wayPoints[w]=new Scanner(System.in).nextInt();
		if(wayPoints[w]>=500||wayPoints[w]<=1) {System.out.println("無効な数値");continue;}		
					
			this.distance+=wayPoints[w];
			
		}return wayPoints;
	}
	public void directions(int[]wayPoints) {
		
	}
//	public int[]directions(int wDirection){
//		int[]wpd=new int[wDirection];
//		for(int d=0;d<wpd.length;d++) {System.out.println("このWPの方位を入力");
//		wpd[d]=new Scanner(System.in).nextInt();this.wpDirection=(d+360)%360 ;}
//		
//			return wpd;
//	}
	public double cruising(double cruiseSpeed) {
		return cruiseSpeed*661.4708;
	}
	public int fullWeight(Weapons weapon1,Weapons weapon2) {
		return this.getEWeight()+weapon1.wWeight()+weapon2.wWeight();
	}
	public int getIntTank() {
		return this.intTank;
	}
	public void setIntTank(int intTank) {
		this.intTank=intTank;
	}
	
	public int fullFuel(int tankN) {
		tankN*=3000;
		 if(this.type.equals("F-35B")){setIntTank(13100);
		}else if(this.type.equals("F/A-18E")) {setIntTank(14480);}
		 if(this.type.equals("F-35B")||this.type.equals("F-35C")) { tankN=0;}
		this.fuel=getIntTank()+tankN;
		return this.fuel;
	
	}
	public double fuelconsumption(double fuelConsumption) {
		return this.speed/this.hour;
	}
	public void reFuel(int fuel) {
		if(this.fuel<=distance) {}
	}
	public int altitude() {
		return 24000;
	}
	public void attack() {
		
	}
	public void land(Airbase base1) {
		System.out.println("当該機は任務終了後、"+base1.name+"に着艦");
	}
	public int getStallSpeed() {
		return this.stallSpeed;
	}
//	public int setStallSpeed(int stallSpeed) {
//		
//		if(stallSpeed>=150){throw new IllegalArgumentException("無効");}
//
//		return 
//	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMission() {
		return mission;
	}
	public void setMission(String mission) {
		this.mission = mission;
	}
	public double getCruiseSpeed() {
		return cruiseSpeed;
	}
	public void setCruiseSpeed(double cruiseSpeed) {
		this.cruiseSpeed = cruiseSpeed;
	}
	public int getElements() {
		return elements;
	}
	public void setElements(int elements) {
		this.elements = elements;
	}
	public int getWpDirection() {
		return wpDirection;
	}
	public void setWpDirection(int wpDirection) {
		this.wpDirection = wpDirection;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getWayPoints() {
		return wayPoints;
	}
	public void setWayPoints(int wayPoints) {
		this.wayPoints = wayPoints;
	}
	public int geteWeight() {
		return eWeight;
	}
	public void seteWeight(int eWeight) {
		this.eWeight = eWeight;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getFullWeight() {
		return fullWeight;
	}
	public void setFullWeight(int fullWeight) {
		this.fullWeight = fullWeight;
	}
	public int getCombatRadius() {
		return combatRadius;
	}
	public void setCombatRadius(int combatRadius) {
		this.combatRadius = combatRadius;
	}
	public int getFuel() {
		return fuel;
	}
	public void setFuel(int fuel) {
		this.fuel = fuel;
	}
	public double getFuelConsumption() {
		return fuelConsumption;
	}
	public void setFuelConsumption(double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}
	public int getMaxAltitude() {
		return maxAltitude;
	}
	public void setMaxAltitude(int maxAltitude) {
		this.maxAltitude = maxAltitude;
	}
	public int getCruiseAltitude() {
		return cruiseAltitude;
	}
	public void setCruiseAltitude(int cruiseAltitude) {
		this.cruiseAltitude = cruiseAltitude;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Weapons getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapons weapon) {
		this.weapon = weapon;
	}

}
