package aircrafts;

public class F_35B extends STOVL {
	int runway=1000;
	public F_35B() {
		setName("F-35B");
		setType("multiRole");
		setNEngine(1);
		
		setIntfuel(13100);
		setWeaponized(true);
		carrierops=true;
		boolean vtol=true;
		
		}

}