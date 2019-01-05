package entity;

import java.util.Collection;

import org.json.JSONException;
import org.json.JSONObject;

import entity.POI.POIBuilder;

public class POI {
	private String ID;
	private double DURATION;
	private double RATING;
	private double LAT;
	private double LONG;
	
	public static class POIBuilder{
		private String ID;
		private double DURATION;
		private double RATING;
		private double LAT;
		private double LONG;
		public void setID(String iD) {
			ID = iD;
		}
		public void setDURATION(double dURATION) {
			DURATION = dURATION;
		}
		public void setRATING(double rATING) {
			RATING = rATING;
		}
		public void setLAT(double lAT) {
			LAT = lAT;
		}
		public void setLONG(double lONG) {
			LONG = lONG;
		}
		public POI build() {
			return new POI(this);
		}
	}
	private POI(POIBuilder builder) {
		this.ID = builder.ID;
		this.DURATION = builder.DURATION;
		this.LAT = builder.LAT;
		this.LONG = builder.LONG;
		this.RATING = builder.RATING;
	}
	public String getID() {
		return ID;
	}
	public double getDURATION() {
		return DURATION;
	}
	public double getRATING() {
		return RATING;
	}
	public double getLAT() {
		return LAT;
	}
	public double getLONG() {
		return LONG;
	}
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("id", ID);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
