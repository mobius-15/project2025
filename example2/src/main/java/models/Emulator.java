package models;

public class Emulator {
	Jet jet;
	double ρ=0.475;
	double mach_s=236;
	double wingArea=jet.getWingArea();
	double fuelConsumption=jet.getFuelConsumption();
	
	public void fuelConsumption(Jet jet) {
		double sfc=0.81;
		int nEngine=2;
		int milThrust=11000;
		int abThrust=17000;
		double d=drag(jet)/4.4482;
		double fc=sfc*d*nEngine;
		jet.setFuelConsumption(fc);
	}
	public double liftCoefficient(Jet jet) {
		int weight = jet.getFullWeight();
		int n=222411;		
		double cruise=jet.getCruiseSpeed();
		
		double lc=n/(0.5*(ρ*Math.pow(mach_s,2)*wingArea));
	return lc;  
	}
	public double drag(Jet jet) {
		 double dc=0.03;
		 double drag=1/(dc*(ρ*Math.pow(mach_s,2)*wingArea));
		
		return drag;
	}
}
