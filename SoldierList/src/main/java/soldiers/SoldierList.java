package soldiers;

import java.util.ArrayList;

public class SoldierList {
	private ArrayList<SoldierBean>battalion=new ArrayList<SoldierBean>();
	
	private int indexer;

	public ArrayList<SoldierBean> getBattalion() {
		return battalion;
	}

	public void setBattalion(ArrayList<SoldierBean> battalion) {
		this.battalion = battalion;
	}

	public int getIndexer() {
		return indexer;
	}

	public void setIndexer(int indexer) {
		this.indexer = indexer;
	}
	public boolean hasnext() {
		if(this.battalion.size()<this.indexer) {
		return false;
		}else {
			return true;
		}
	}
		public SoldierBean getNext() {
			SoldierBean soldier=battalion.get(indexer);
			this.indexer++;
			return soldier;
		}
}
