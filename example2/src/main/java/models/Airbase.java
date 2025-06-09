package models;

import java.util.Random;
import java.util.Scanner;

public class Airbase {
	public String name;
	public int baseType;
	private int movingSpeed;
	public String heading;
	public int vector;
	public int stock;
	Jet jet;
	Scanner scanner=new Scanner(System.in);
	Random random=new Random();
		
public	Airbase(String name,int movingSpeed,String heading){
		this.name=name;
		this.movingSpeed=movingSpeed;
		this.heading=baseHeading();
	}
	public void baseType(int type) {
		this.baseType=type;
		System.out.print("出撃拠点：");
		switch(type) {case 1 ->{System.out.println("CATOBAR");
		}case 2 ->{System.out.println("STOVL");
		} default ->{System.out.println("Airfield");}
		}
	}
	public int getMovingSpeed(){
		return this.movingSpeed;

	}
	public void bInformation(Jet jet,int sorties) {
		System.out.println(Jet.type+"を"+sorties+"機搭載");
	}

	public void baseDistance(double hours) {
		int baseDistance=this.movingSpeed*=hours;
		System.out.println("母艦は初期位置より進路"+this.vector+"方向に"+baseDistance+"nm移動");
	}	
	public String baseHeading() {
		System.out.println("拠点の方位を入力");
		int degree=scanner.nextInt();
	this.vector=(degree+360)%360 ;
	return  ""+this.vector+"";
	}
	public void landing(Jet jet,int wire) {
		wire=random.nextInt(5);
		int landingSpeed=random.nextInt(20)+jet.getStallSpeed();
		
		System.out.println(landingSpeed+"ノットで"+wire+"番ワイヤに着艦成功。作戦終了");
	}
	public void defense() {
		
	}
	public void stock() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBaseType() {
		return baseType;
	}
	public void setBaseType(int baseType) {
		this.baseType = baseType;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public int getVector() {
		return vector;
	}
	public void setVector(int vector) {
		this.vector = vector;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Jet getJet() {
		return jet;
	}
	public void setJet(Jet jet) {
		this.jet = jet;
	}
	public Scanner getScanner() {
		return scanner;
	}
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	public Random getRandom() {
		return random;
	}
	public void setRandom(Random random) {
		this.random = random;
	}
	public void setMovingSpeed(int movingSpeed) {
		this.movingSpeed = movingSpeed;
	}

}
