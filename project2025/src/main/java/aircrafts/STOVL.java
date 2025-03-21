package aircrafts;
import carrierOps.Vessels;
public class STOVL extends Aircrafts{
	
	Vessels name;
	
	public void launch(Vessels name) {
		System.out.println(name+"を発艦");
		 System.out.println("カタパルト使わず");
	}
}
