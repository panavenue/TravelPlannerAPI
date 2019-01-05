package entity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;


public class Day {
	public final int dayId;
	public List<POI> dailyPOI;
	public double hoursRemain;
	public Day(int dayId, double hoursRemain) {
		this.dayId = dayId;
		this.hoursRemain = hoursRemain;
		dailyPOI = new ArrayList<POI>();
	}
	public JSONArray toJSONArray() {
		JSONArray arr = new JSONArray();
		for(int i = 0; i < dailyPOI.size(); i++) {
			arr.put(dailyPOI.get(i).toJSONObject());
		}
		return arr;
	}
}
