package entity;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	private int hoursPerDay;
	private int numberOfDays;
	public User(JSONObject userInfo) {
		try {
			this.hoursPerDay = userInfo.getInt("vacationHoursDay");
			this.numberOfDays = userInfo.getInt("vacationDuration");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public int getHoursPerDay() {
		return hoursPerDay;
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
}
