package entity;

public class User {
	private int hoursPerDay;
	private int numberOfDays;
	public User(int h, int n) {
		this.hoursPerDay = h;
		this.numberOfDays = n;
	}
	public int getHoursPerDay() {
		return hoursPerDay;
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
}
