package weapons;

public  class Weapons {
    private String name;
    private String category;
    private double weightlb;
    private double dragIndex;
    private int maxSpeed;
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWeightlb() {
		return weightlb;
	}
	public void setWeightlb(double weightlb) {
		this.weightlb = weightlb;
	}
	public double getDragIndex() {
		return dragIndex;
	}
	public void setDragIndex(double dragIndex) {
		this.dragIndex = dragIndex;
	}
	public String getCategory() {
	    if (this instanceof AirToAir) return "Air-to-Air";
	    if (this instanceof JDAM) return "JDAM";
	    if (this instanceof AirToGround) return "Air-to-Ground";
	    return "Other";
	}
	public void setCategory(String category) {
		this.category=category;
	}
}
