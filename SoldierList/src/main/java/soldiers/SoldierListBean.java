package soldiers;

import java.util.ArrayList;

public class SoldierListBean {
	private ArrayList<SoldierBean> alSoldier;
	private int intC =0;
	public void setAlSoldier(ArrayList<SoldierBean>alSoldier) {
		this.alSoldier=alSoldier;
	}
	public boolean hasNext() {
		boolean blResult=false;
		if(this.intC<this.alSoldier.size()) {
			blResult=true;
		}
		return blResult;
	}
	public SoldierBean getNext() {
		SoldierBean soldier =alSoldier.get(this.intC);
		this.intC++;
		return soldier;
	}
}
