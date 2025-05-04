package aircrafts;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import weapons.Weapons;
public class FA_18 extends Aircrafts  implements Serializable{	

		Random random=new Random();		
		private boolean afterburnerOn = false;
		Map<Integer, Loadout> stations = new HashMap<>();

 public FA_18() {
			setName("F/A-18C");
			setType("multiRole");
			setNEngine(2);
			setCruiseSpeed(480);
			setCeilingAlt(50000);
			setIntFuel(10800);
			setSfc(0.81);
			setMilThrust(11000);
			setEmptyWeight(25100);
			setFullWeight(50200);
			setA_bThrust(17700);
			setWeaponized(true);
			setnTank(1);
		}

 public void enableAfterburner() {
	    this.afterburnerOn = true;
	    setSfc(1.74);
	}

	public void disableAfterburner() {
	    this.afterburnerOn = false;
	    setSfc(0.81); // default
	}

	public boolean isAfterburnerOn() {
	    return afterburnerOn;
	}	    		   
	    public void initializeStations(int count) {
	        for (int i = 1; i <= count; i++) {
	            stations.put(i, new Loadout(i));
	        }
	    }
	    public void assignWeaponsToStation(int stationNumber, Weapons weapon) {
	        if (!stations.containsKey(stationNumber)) {
	            throw new IllegalArgumentException("Invalid station number: " + stationNumber);
	        }
	        stations.get(stationNumber).assignWeapons(weapon);
	    }

		public Map<Integer, Loadout> getStations() {
			return stations;
		}

		public void setStations(Map<Integer, Loadout> stations) {
			this.stations = stations;
		}
}

