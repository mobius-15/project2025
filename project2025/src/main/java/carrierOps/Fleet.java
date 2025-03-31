package carrierOps;

import java.util.TreeMap;

public interface Fleet {
	public static TreeMap<Integer,String>cvnp(){
		TreeMap<Integer,String>cvn=new TreeMap<>();
		cvn.put(68,"Nimitz");
		cvn.put(69,"Dowight_D_Eisenhower");
		cvn.put(70,"Carl_Vinson");
		cvn.put(71, "Theodoa_Roosevelt");
		cvn.put(72, "Abraham_Loncoln");
		cvn.put(73,"George_Washington");
		cvn.put(74,"John_C_Stennis");
		cvn.put(75, "Harry_S_Truman");
		cvn.put(76, "Ronald_Reagan");
		cvn.put(77, "George_H_W_Bush");
		cvn.put(78, "Gerald_Ford");
		return cvn;
	}
	public static TreeMap<Integer,String>cgp(){
		TreeMap<Integer,String>paccg=new TreeMap<>();
		
		return paccg;
	}
	public static TreeMap<Integer,String>ddgp(){
		TreeMap<Integer,String>pacddg=new TreeMap<>();
		
		return pacddg;
	}
}
