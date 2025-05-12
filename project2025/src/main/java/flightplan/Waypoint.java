package flightplan;

public class Waypoint {
    private double latitude;
    private double longitude;
    private int altitude; 
    private int speed;
    private double distance;
    private int heading;
    private double segmentTime;
    private double mach;
    private double sonicSpeed;

 public Waypoint
( double latitude, double longitude, int altitude, int speed, double distance, int heading,double segmentTime,double mach) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.speed = speed;
        this.distance = distance;
        this.heading = heading;
        this.segmentTime =segmentTime;
        this.mach = mach;
        
    }

    // 起点のみGPS座標を設定
    public static Waypoint createStartPoint(double latitude, double longitude, int altitude, int speed) {
        return new Waypoint(latitude, longitude, altitude, speed, 0, 0, 0, 0.0); // 最初のWPは距離=0、方位=0
    }

    public int getAltitude() { return altitude; }
    public int getSpeed() { return speed; }
    public double getDistance() { return distance; }
    public int getHeading() { return heading; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
	public double getSegmentTime() {return segmentTime;	}
    public double getMach() { return mach; }
    public double getSonicSpeed() { return sonicSpeed; }
    
    public void setMach(double mach) { this.mach = mach; }
    public void setSonicSpeed(double sonicSpeed) { this.sonicSpeed = sonicSpeed; }
    public void setLatitude(double latitude) {this.latitude=latitude;}
    public void setLongitude(double longitude) {this.longitude=longitude;}
    public void calculatePosition(double baseLat, double baseLon) {
        // 地球半径（海里） → 3440.065 NM
        double radiusNm = 3440.065;
        double delta = distance / radiusNm; // 角度ラジアン

        double theta = Math.toRadians(heading);
        double baseLatRad = Math.toRadians(baseLat);
        double baseLonRad = Math.toRadians(baseLon);

        double newLatRad = Math.asin(Math.sin(baseLatRad) * Math.cos(delta) +
                                     Math.cos(baseLatRad) * Math.sin(delta) * Math.cos(theta));

        double newLonRad = baseLonRad + Math.atan2(Math.sin(theta) * Math.sin(delta) * Math.cos(baseLatRad),
                                                   Math.cos(delta) - Math.sin(baseLatRad) * Math.sin(newLatRad));

        this.latitude = Math.toDegrees(newLatRad);
        this.longitude = Math.toDegrees(newLonRad);
    }
    
}
