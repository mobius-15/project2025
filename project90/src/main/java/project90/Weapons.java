package project90;
import  java.util.Scanner;

public class Weapons {
	 String weaponType;
	 String name;
	 double weaponsSpeed;
	 int loadout;
	 int range;
	 int damages;
	 int weight;
	 Scanner scanner=new Scanner(System.in);
	
	public Weapons(String weaponType,String name,int loadout) {
		this.weaponType=weaponType;
		this.name=name;
		this.loadout=loadout;

		System.out.println(weaponType+"用装備:"+wName()+" "+loadout+"発を搭載中");
	}
	public String wName() {
		System.out.println("兵装名を入力");
		String wN=scanner.nextLine();
		if(wN==null||wN.length()<=3){throw new IllegalArgumentException("無効な値");}
		this.name=wN;
		return this.name;
	}
	
	public int wWeight() {
		
	if(this.name.equals("GBU-31")){this.weight=2160;System.out.print(this.weight+" lb"+this.loadout+"発 ");
		}else if(this.name.equals("AIM-120")){this.weight=350;System.out.println(this.weight+" lb"+this.loadout+"発 ");
		}else{System.out.println(this.name+"の重量を入力(lb)");
			this.weight=scanner.nextInt();
			System.out.println(this.weight+" lb"+this.loadout+"発 ");}

		return this.weight*this.loadout;
	}

	public void search() {
		
	}
	public void attack(int range) {
		
	}
	public String getWeaponType() {
		return weaponType;
	}
	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWeaponsSpeed() {
		return weaponsSpeed;
	}
	public void setWeaponsSpeed(double weaponsSpeed) {
		this.weaponsSpeed = weaponsSpeed;
	}
	public int getLoadout() {
		return loadout;
	}
	public void setLoadout(int loadout) {
		this.loadout = loadout;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getDamages() {
		return damages;
	}
	public void setDamages(int damages) {
		this.damages = damages;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Scanner getScanner() {
		return scanner;
	}
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
}
