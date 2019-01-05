package recommendation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.User;
import entity.Day;
import entity.POI;

public class ViewRecommendation {
	private static final String ID = "id";
	private static final String RATING = "rating";
	private static final String DURATION = "expectedStayDuration";
	private static final String LAT = "lat";
	private static final String LONG = "long";
	
	public List<POI> getFromAndroid(JSONArray inputs) throws JSONException{
		List<POI> POIList = new ArrayList<>();
		for(int i = 0; i < inputs.length(); i++) {
			JSONObject obj = inputs.getJSONObject(i);
			POI.POIBuilder builder = new POI.POIBuilder();
			if(!obj.isNull(ID)) {
				builder.setID(obj.getString(ID));
			}
			if(!obj.isNull(DURATION)) {
				builder.setDURATION(obj.getDouble(DURATION));
			}
			if(!obj.isNull(RATING)) {
				builder.setRATING(obj.getDouble(RATING));
			}
			if(!obj.isNull(LAT)) {
				builder.setLAT(obj.getDouble(LAT));
			}
			if(!obj.isNull(LONG)) {
				builder.setLONG(obj.getDouble(LONG));
			}
			POIList.add(builder.build());
		}
		return POIList;
	}
	public static List<Day> generatePath(User u, List<POI> listFromFront){
		int hoursPerDay = u.getHoursPerDay();
		int numberOfDays = u.getNumberOfDays();
		List<Day> dayList = new ArrayList<>();
		for(int i = 0; i < numberOfDays; i++) {
			dayList.add(new Day(i, u.getHoursPerDay()));
		}
		PriorityQueue<POI> pq = new PriorityQueue<POI>(new Comparator<POI>() {
			@Override
			public int compare(POI a, POI b) {
				if(a.getRATING() == b.getRATING()) {
					return 0;
				} else {
					return (int)(b.getRATING() - a.getRATING());
				}
			}
		});
		// add POIs from front-end to pq
		for(int i = 0; i < listFromFront.size(); i++) {
			if(listFromFront.get(i).getDURATION() <= hoursPerDay) {
				pq.offer(listFromFront.get(i));
			}
		}
	
		int index = 0;
		// check availability and assign views to each day
		while(index < dayList.size() && !pq.isEmpty()) {
			POI cur = pq.peek();
			if(cur.getDURATION() <= dayList.get(index).hoursRemain) {
				dayList.get(index).dailyPOI.add(pq.poll());
				dayList.get(index).hoursRemain -= cur.getDURATION();
			} else {
				index++;
			}
		}
		index = 0;
		if(pq.isEmpty()) {
			return dayList;
		} else {
			// do the process again to fill more views
			while(index < dayList.size() && !pq.isEmpty()) {
				POI cur = pq.peek();
				if(cur.getDURATION() <= dayList.get(index).hoursRemain) {
					dayList.get(index).dailyPOI.add(pq.poll());
					dayList.get(index).hoursRemain -= cur.getDURATION();
				} else {
					index++;
				}
			}
		}
		return dayList;
	}
}
