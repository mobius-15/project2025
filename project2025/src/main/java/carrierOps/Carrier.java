package carrierOps;

public class Carrier extends Vessels implements Fleet{
Fleet cvn;	

private double latitude;
private double longitude;
private int speed;
private int cats;

	public Carrier(String name,double lat,double lon,int speed,int displacement) {
//		setName(cvn.get(5));
		 super(name, displacement);
		 	this.setName(name);
		    this.latitude = lat;
		    this.longitude = lon;
		    this.speed = speed;
		setPower(260000);
		setDisplacement(85000);
		setCats(4);
        
        
		 
	}
	public void reactor() {
		
	}
	public void catapult(int cats) {
		
	}
	public void airCrafts(int sorties) {
		
	}
	public void defense() {
		
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getCats() {
		return cats;
	}
	public void setCats(int cats) {
		this.cats = cats;
	}

}
