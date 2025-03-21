package carrierOps;

import java.util.HashMap;

public class CVN extends Vessels implements Fleet{
Fleet cvn;			
	public CVN(HashMap<Integer,String>cvn) {
		setName(cvn.get(5));
//		setName();
		setPower(260000);
		setBasicPlacement(85000);
		 int cats;
	}
	public void reactor() {
		
	}
	public void catapult(int cats) {
		
	}
	public void airCrafts(int sorties) {
		
	}
	public void defense() {
		
	}
	public int direction() {
		return 180;
	}
}
