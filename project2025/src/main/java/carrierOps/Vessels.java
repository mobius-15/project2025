package carrierOps;

public class Vessels {
//	private static String name;
	private String name;
	private int fullDisplacement; 
	private int basicPlacement;
	private int length;
	private int width;
	private int power;
	private int speed;
	private int direction;

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
	public int getFullDisplacement() {
		return fullDisplacement;
	}
	public void setFullDisplacement(int fullDisplacement) {
		this.fullDisplacement = fullDisplacement;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
}

