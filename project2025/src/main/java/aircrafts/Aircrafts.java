package aircrafts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import weapons.Weapons;

public abstract class Aircrafts implements Serializable {
	private String name, type;
	String mission;
	private int nEngine;
	private int cruiseSpeed;
	private int combatRange, ferryRange;
	private int ceilingAlt;
	private int milThrust;// 推力 (N)
	private int intfuel;
	private int wpCount;
	private double emptyWeight,loadoutWeight, fullWeight;
	private double fuelFlow;
	private double remainingFuel;
	private int rpm;
	private int cas;
	private int a_bThrust;
	private int totalFuel;
	private int nTank;
	private int extFuel;
	private double sfc;
	private boolean weaponized;
	private boolean carrierops;
	private String modex;
	private List<Weapons> weaponList = new ArrayList<>();
	public int getA_bThrust() {
		return a_bThrust;
	}

	public void setA_bThrust(int a_bThrust) {
		this.a_bThrust = a_bThrust;
	}

	public int getTotalFuel() {
		return totalFuel;
	}

	public void setTotalFuel(int totalFuel) {
		this.totalFuel = totalFuel;
	}

	public double getRemainingFuel() {
		return remainingFuel;
	}

	public void setRemainingFuel(double remainingFuel) {
		this.remainingFuel = remainingFuel;
	}

	public int getExtFuel() {
		return extFuel;
	}

	public void setExtFuel(int extFuel) {
		this.extFuel = extFuel;
	}

	public double getFullWeight() {
		return this.fullWeight;
	}

	public void setFullWeight(int fullWeight) {
		this.fullWeight = fullWeight;
	}

	public int getNEngine() {
		return this.nEngine;
	}

	public void setNEngine(int nEngine) {
		this.nEngine = nEngine;
	}

	public int getCruiseSpeed() {
		return this.cruiseSpeed;
	}

	public void setCruiseSpeed(int cruiseSpeed) {
		if (cruiseSpeed <= 100) {
			throw new IllegalArgumentException("Stall");
		}
		this.cruiseSpeed = cruiseSpeed;
	}

	public int getCeilingAlt() {
		return this.ceilingAlt;
	}

	public void setCeilingAlt(int ceilingAlt) {

		this.ceilingAlt = ceilingAlt;
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

	public int getIntFuel() {
		return intfuel;
	}

	public void setIntFuel(int intfuel) {
		this.intfuel = intfuel;
	}

	public double getFuelFlow() {
		fuelFlow = getMilThrust() * getSfc();
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

	public double getSfc() {
		return sfc;
	}

	public void setSfc(double sfc) {
		this.sfc = sfc;
	}

	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o instanceof Aircrafts) {
			if (this.type.trim().equals(this.type)) {
				return true;
			}
		}
		return false;
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
			this.sfc = (double) (fuelFlow / milThrust); // TSFCの計算
		}
		return sfc;

	}

	public double getEmptyWeight() {
		return emptyWeight;
	}

	public void setEmptyWeight(int emptyWeight) {
		this.emptyWeight = emptyWeight;

	}

	public int getWpCount() {
		return wpCount;
	}

	public void setWpCount(int wpCount) {
		this.wpCount = wpCount;
	}

	public int getCas() {
		return cas;
	}

	public void setCas(int cas) {
		this.cas = cas;
	}

	public int getnTank() {
		return nTank;
	}

	public void setnTank(int nTank) {
		this.nTank = nTank;
	}

	public boolean isCarrierops() {
		return carrierops;
	}

	public void setCarrierops(boolean carrierops) {
		this.carrierops = carrierops;
	}
	public List<Weapons> getWeaponList() {
		return weaponList;
	}

	public void setWeaponList(List<Weapons> weaponList) {
		this.weaponList = weaponList;
	}

	public double getLoadoutWeight() {
		return loadoutWeight;
	}

	public void setLoadoutWeight(double loadoutWeight) {
		this.loadoutWeight = loadoutWeight;
	}
	

}
