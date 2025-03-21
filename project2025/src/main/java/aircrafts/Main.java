package aircrafts;
import java.util.Random;

import carrierOps.CVN;
import carrierOps.Fleet;
public class Main {

	public static void main(String[] args) throws Exception{
		// TODO 自動生成されたメソッド・スタブ
		Random random=new Random();
		CVN cvn =new CVN(Fleet.cvnp());
		
//		List<String>vModex102 = Hanger.vfa102();
//		List<String>vModex147=Hanger.vfa147();
//		List<String>vModex27=Hanger.vfa27();
//		List<String>vModex195=Hanger.vfa195();
//        List<String> randomModexList1 = Hanger.getRandomModexList(vfa102ModexList, 2);
//		CVW cvw5 
//		=new CVW(Hanger.vfa102(),Hanger.vfa147(),Hanger.vfa27(),Hanger.vfa195());
//		cvw5.setTailcode("NF");

		System.out.println("\n");

			
		
//		System.out.println(dback11.getName()+":"+vModex102.get(0));
			
		FlightPlan2 dplan11 = new FlightPlan2();
			
		dplan11.gconvertAndAdd(dplan11.FP);
//		dback11.calculateFuelUsage(dback11,2);
		dplan11.displayFlightPlans();
		

			
			
////			Aircrafts hornet21 = new FA_18E(,"multiRole",2,true);
////			FlightPlan hplan21 = new FlightPlan();
////			
//
////			
////			F_35C lightning21 = new F_35C("Anti_Ship_Strike","multiRole",1,true);
////			Aircrafts lightning22=lightning21;
////			FlightPlan lplan21 = new FlightPlan();
//			
////			Aircrafts colt01 =(FA_18E) hornet21;
//			System.out.println(cvn73.getBasicPlacement()+"t");
//			Aircrafts[]sPackage=new Aircrafts[3];
//			sPackage[0]=new FA_18("CAP","multiRole",2,true);
//			sPackage[1]=new FA_18E("SEAD","multiRole",2,true);
//			sPackage[2]=new F_35C("Anti_Ship_Strike","multiRole",1,true);
//			for(Aircrafts a:sPackage) {a.launch(cvn73.getName());}
//			Aircrafts cvw=new CVW();
//			Hanger.vfa102();
//			
////			Aircrafts lightning11 = new F_35B("Ground_Strike","multiRole",1,true);
////			FlightPlan lplan11 = new FlightPlan();
//		
			
		}
	
}