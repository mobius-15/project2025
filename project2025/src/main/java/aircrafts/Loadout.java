package aircrafts;

import weapons.Weapons;

public class Loadout {
    private int stationNumber;
    private Weapons weapon ;
    private Loadout weaponsName;
    private Loadout weaponsWeight;
    private Loadout category;
    

    public Loadout(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public void assignWeapons(Weapons weapon) {
        this.weapon = weapon;
    }

    public Weapons getWeapons() {
        return weapon;
    }

    public int getStationNumber() {
        return stationNumber;
    }

	public Weapons getWeapon() {
		return weapon;
	}

	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}

}

