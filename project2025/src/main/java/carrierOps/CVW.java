package carrierOps;

import java.util.List;

import aircrafts.Aircrafts;
import aircrafts.Hanger;

public class CVW extends Hanger {
	
	Vessels name;
	Aircrafts getName;
	private String tailcode;
	
	public CVW
	(List<String> vfa102modexList,List<String>vfa147modexList,List<String>vfa27modexList,
	List<String>vfa195modexList){
		
//	Aircrafts[]cvw=new Aircrafts[4];//空母航空団の編成　一般的な編成
//	cvw[0]=new FA_18E(getName(),vfa27modexList.get(5),getType(),2,true);
//	cvw[1]=new F_35C(getName(),vfa147modexList.get(7),getType(),1,true);
//	cvw[2]=new FA_18F(getName(),vfa102modexList.get(3),getType(),2,true);
//	cvw[3]=new FA_18E(getName(),vfa195modexList.get(9),getType(),2,true);
//	cvw[4]=new EA_18G(getName(),vaq141modexList.get(9),getType(),2,true);
//	cvw[5]=new E_2D(getName(),vaw125modexList.get(9),getType(),2,true);
//	cvw[6]=new CMV_22(getName(),vrc30modexList.get(9),getType(),2,true);
//	cvw[7]=new MH_60R(getName(),hsc12modexList.get(9),getType(),2,true);
//	cvw[8]=new MH_60S(getName(),hsm771modexList.get(9),getType(),2,true);
		
//	for(Aircrafts i:cvw) {System.out.println(i.getName()+":"+i.getModex());}

	}
	public void deployment(Vessels name,Aircrafts[]squadron) {
		
	}
	public String toString() {
		return this.getName()+":"+this.getSquadron();
	}
	public String getTailcode() {
		return tailcode;
	}
	public void setTailcode(String tailcode) {
		this.tailcode = tailcode;
	}
}
