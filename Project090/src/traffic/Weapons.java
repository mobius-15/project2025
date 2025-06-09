package traffic;
import  java.util.Scanner;

public class Weapons {
	 String weaponType;
	 String name;
	 double weaponsSpeed;
	 int loadout;
	 int range;
	 int damages;
	 int weight;
	
	public Weapons(String weaponType,String name,int loadout) {
		this.weaponType=weaponType;
		this.name=name;
		this.loadout=loadout;

		System.out.println(weaponType+"用装備:"+wName()+" "+loadout+"発を搭載中");
	}
	public String wName() {
		System.out.println("兵装名を入力");
		String wN=new Scanner(System.in).nextLine();
		if(wN==null||wN.length()<=3){throw new IllegalArgumentException("無効な値");}
		this.name=wN;
		return this.name;
	}
	
	public int wWeight() {
		
	if(this.name.equals("GBU-31")){this.weight=2160;System.out.print(this.weight+" lb"+this.loadout+"発 ");
		}else if(this.name.equals("AIM-120")){this.weight=350;System.out.println(this.weight+" lb"+this.loadout+"発 ");
		}else{System.out.println(this.name+"の重量を入力(lb)");
			this.weight=new Scanner(System.in).nextInt();
			System.out.println(this.weight+" lb"+this.loadout+"発 ");}

		return this.weight*this.loadout;
	}

	public void search() {
		
	}
	public void attack(int range) {
		
	}
}
