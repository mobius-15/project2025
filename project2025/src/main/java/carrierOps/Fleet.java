package carrierOps;

import java.util.HashMap;

public interface Fleet {
	public static HashMap<Integer,String>cvnp(){
		HashMap<Integer,String>paccvn=new HashMap<>();
		paccvn.put(68,"Nimitz");
		paccvn.put(69,"Dowight_D_Eisenhower");
		paccvn.put(70,"Carl_Vinson");
		paccvn.put(71, "Theodoa_Roosevelt");
		paccvn.put(72, "Abraham_Loncoln");
		paccvn.put(73,"George_Washington");
		paccvn.put(74,"John_C_Stennis");
		paccvn.put(75, "Harry_S_Truman");
		paccvn.put(76, "Ronald_Reagan");
		paccvn.put(77, "George_H_W_Bush");
		paccvn.put(78, "Gerald_Ford");
		return paccvn;
	}
	public static HashMap<Integer,String>cgp(){
		HashMap<Integer,String>paccg=new HashMap<>();
		
		return paccg;
	}
	public static HashMap<Integer,String>ddgp(){
		HashMap<Integer,String>pacddg=new HashMap<>();
		
		return pacddg;
	}
}
