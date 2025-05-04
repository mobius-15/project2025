package mission;
	
import java.util.ArrayList;
import java.util.List;

import aircrafts.Aircrafts;
import flightplan.FlightPlan2;

public class Missions extends FlightPlan2{
	private int sorties;
	private String name;
	Aircrafts aircraft;
	
	public static List<String>getMissions(){
		List<String>missionType=new ArrayList<>();
		missionType.add("CAP");
		missionType.add("Strike");
		missionType.add("SEAD");
		missionType.add("Training");
		return missionType;
	}
	
	
	public void cap() {
		
	}
	public void strike() {
		
	}
	public void sead() {
		
	}
	public void search() {
		
	}
	
}
