package traffic;

public class Airbase {
	public String name;
	public int baseType;
	private int movingSpeed;
	public String heading;
	public int vector;
	public int stock;
	Jet jet;
		
	Airbase(String name,int movingSpeed,String heading){
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
		System.out.println(jet.type+"を"+sorties+"機搭載");
	}

	public void baseDistance(double hours) {
		int baseDistance=this.movingSpeed*=hours;
		System.out.println("母艦は初期位置より進路"+this.vector+"方向に"+baseDistance+"nm移動");
	}	
	public String baseHeading() {
		System.out.println("拠点の方位を入力");
		int degree=new java.util.Scanner(System.in).nextInt();
	this.vector=(degree+360)%360 ;
	return  ""+this.vector+"";
	}
	public void landing(Jet jet,int wire) {
		wire=new java.util.Random().nextInt(5);
		int landingSpeed=new java.util.Random().nextInt(20)+jet.getStallSpeed();
		
		System.out.println(landingSpeed+"ノットで"+wire+"番ワイヤに着艦成功。作戦終了");
	}
	public void defense() {
		
	}
	public void stock() {
		
	}

}
