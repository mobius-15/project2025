package aircrafts;

public class Waypoint {
    private double latitude;
    private double longitude;
    private int altitude; 
    private int speed;
    private double distance;
    private int heading;
    private double segmentTime;

 public Waypoint
( double latitude, double longitude, int altitude, int speed, double distance, int heading,double segmentTime) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.speed = speed;
        this.distance = distance;
        this.heading = heading;
        this.segmentTime =segmentTime;
    }

    // 起点のみGPS座標を設定
    public static Waypoint createStartPoint(double latitude, double longitude, int altitude, int speed) {
        return new Waypoint(latitude, longitude, altitude, speed, 0, 0, 0); // 最初のWPは距離=0、方位=0
    }

    public int getAltitude() { return altitude; }
    public int getSpeed() { return speed; }
    public double getDistance() { return distance; }
    public int getHeading() { return heading; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
	public double getSegmentTime() {return segmentTime;	}
}
