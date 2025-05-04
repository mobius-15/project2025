package aircrafts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weapons.Weapons;

public class F_35B extends STOVL {
	int runway=1000;
	private List<Weapons> weaponList = new ArrayList<>();
	Map<Integer, Loadout> stations = new HashMap<>();
	public F_35B() {
		setName("F-35B");
		setType("multiRole");
		setNEngine(1);		
		setIntFuel(13100);
		setWeaponized(true);
		setCarrierops(true);
		boolean vtol=true;		
		
		}
	public void addWeapon(Weapons weapon) {
		weaponList.add(weapon);
	}

	public double getWeaponsWeight() {
		double totalWeapon=0;
		for(Weapons ww:weaponList) {
			totalWeapon+= ww.getWeightlb();
		}
		return totalWeapon;
//	    return airToGround.stream().mapToDouble(AirToGround::getWeightlb).sum();
	}
    public void clearLoadout() {	        
        weaponList.clear();
    }
    public double getTotalLoadout() {
    	double loadout=(int)(getWeaponsWeight());
    	return loadout;
	}
    public void initializeStations(int count) {
        for (int i = 1; i <= count; i++) {
            stations.put(i, new Loadout(i));
        }
    }
    public void assignWeaponsToStation(int stationNumber, Weapons weapon) {
        stations.get(stationNumber).assignWeapons(weapon);
    }
}


