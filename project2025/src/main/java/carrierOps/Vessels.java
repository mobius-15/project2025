package carrierOps;

public class Vessels {
//	private static String name;
	private String name;
	 int fullDisplacement; 
	private int basicPlacement;
	private int length;
	private int width;
	private int power;

//	public static String getName() {
//		return name;
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public int getPower() {
		return this.power;
	}
	public void setPower(int power) {
	this.power=power;
	}
	public int getBasicPlacement() {
		return basicPlacement;
	}
	public void setBasicPlacement(int basicPlacement) {
		this.basicPlacement = basicPlacement;
	}
}

