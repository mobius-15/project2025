package weapons;

public abstract class AirToAir {
    private String name;
    private int weightlb;
    private double dragIndex;
    private int maxSpeed;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeightlb() {
		return weightlb;
	}
	public void setWeightlb(int weightlb) {
		this.weightlb = weightlb;
	}
	public double getDragIndex() {
		return dragIndex;
	}
	public void setDragIndex(double dragIndex) {
		this.dragIndex = dragIndex;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

}
