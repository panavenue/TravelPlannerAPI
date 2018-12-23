package entity;

public class Views {
	private double hoursNeeded;
	public double getHoursNeeded() {
		return hoursNeeded;
	}
	public double getRate() {
		return rate;
	}
	private double rate;
	public Views(double hoursNeeded, double rate) {
		this.hoursNeeded = hoursNeeded;
		this.rate = rate;
	}
}
