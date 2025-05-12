package flightplan;

public class Atmosphere {
    private double densityRatio;
    private double sonicSpeed;

    public Atmosphere(double densityRatio, double sonicSpeed) {
        this.densityRatio = densityRatio;
        this.sonicSpeed = sonicSpeed;
    }

    public double getDensityRatio() {
        return densityRatio;
    }

    public double getSonicSpeed() {
        return sonicSpeed;
    }
}
