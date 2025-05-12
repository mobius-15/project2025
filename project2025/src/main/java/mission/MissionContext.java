package mission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import aircrafts.Aircrafts;
import aircrafts.FA_18F;
import aircrafts.Loadout;
import carrierOps.Carrier;
import flightplan.FlightPlan2;

public class MissionContext implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String missionType;
    private FlightPlan2 flightPlan;
    private Aircrafts aircraft;
    private Carrier carrier;
    private List<Double> segmentFuelList;
    private double totalFlightTime;
    private int capIndex;
    private int capDuration;
    private List<Aircrafts> wingmen;
    private List<Target> targetPoints;
    private List<Loadout>loadouts;

	public MissionContext(String missionType, FlightPlan2 plan, FA_18F aircraft,
                          Carrier carrier, List<Double> segmentFuelList,
                          double totalFlightTime) {
        this.missionType = missionType;
        this.flightPlan = plan;
        this.aircraft = aircraft;
        this.carrier = carrier;
        this.segmentFuelList = segmentFuelList;
        this.totalFlightTime = totalFlightTime;
    }

    public String getMissionType() { return missionType; }
    public FlightPlan2 getFlightPlan() { return flightPlan; }
    public Aircrafts getAircraft() { return aircraft; }
    public Carrier getCarrier() { return carrier; }
    public List<Double> getSegmentFuelList() {     if (segmentFuelList == null){
        segmentFuelList = new ArrayList<>();}return segmentFuelList; }
    public double getTotalFlightTime() { return totalFlightTime; }
    public int getCapIndex() { return capIndex; }
    public int getCapDuration() { return capDuration; }
    public List<Target> getTargetPoints() { if (targetPoints == null) {
    	targetPoints = new ArrayList<>();} return targetPoints;}

	public void setTargetPoints(List<Target> targetPoints) {
		this.targetPoints = targetPoints;
	}

	public void setMissionType(String missionType) {this.missionType = missionType;	}

	public void setFlightPlan(FlightPlan2 flightPlan) {	this.flightPlan = flightPlan;	}

	public void setAircraft(Aircrafts aircraft) {this.aircraft = aircraft;	}

	public void setCarrier(Carrier carrier) {this.carrier = carrier;}

	public void setSegmentFuelList(List<Double> segmentFuelList) {this.segmentFuelList = segmentFuelList;}

	public void setTotalFlightTime(double totalFlightTime) {this.totalFlightTime = totalFlightTime;	}

	public List<Aircrafts> getWingmen() {
		return wingmen;
	}

	public void setWingmen(List<Aircrafts> wingmen) {
		this.wingmen = wingmen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setCapIndex(int wpIndex) {
		this.capIndex = wpIndex;
	}

	public void setCapDuration(int durationMinutes) {
		this.capDuration = durationMinutes;
	}
	public void addExtraFuelToSegment(int wpIndex, double extraFuel) {
	    if (segmentFuelList == null || wpIndex < 0 || wpIndex >= segmentFuelList.size()) {
	        System.err.println("Invalid index or segmentFuelList is null");
	        return;
	    }
	    double old = segmentFuelList.get(wpIndex);
	    segmentFuelList.set(wpIndex, old + extraFuel);
	}
	public void initializeFuelList(int size) {
	    segmentFuelList = new ArrayList<>(size);
	    for (int i = 0; i < size; i++) {
	        segmentFuelList.add(0.0);
	    }
	}

	public List<Loadout> getLoadouts() {
		// TODO 自動生成されたメソッド・スタブ
		return loadouts;
	}

	public void setLoadouts(List<Loadout> loadouts) {
		this.loadouts = loadouts;
	}
}