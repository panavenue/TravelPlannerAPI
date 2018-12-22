package entity;


public class Place {
	private String place_Id;
	private String placeName;
	private String lon;
	private String lat;
	
	public String getPlaceId() {
		return place_Id;
	}

	public String getPlaceName() {
		return placeName;
	}

	public String getLon() {
		return lon;
	}

	public String getLat() {
		return lat;
	}

	private Place(PlaceBuilder builder) {
		this.place_Id = builder.place_Id;
		this.placeName = builder.placeName;
		this.lon = builder.lon;
		this.lat = builder.lat;
	}
	
	public static class PlaceBuilder {
		public void setPlaceId(String placeId) {
			this.place_Id = placeId;
		}
		public void setPlaceName(String placeName) {
			this.placeName = placeName;
		}
		public void setLon(String lon) {
			this.lon = lon;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
		public String getPlaceId() {
			return place_Id;
		}
		public String getPlaceName() {
			return placeName;
		}
		public String getLon() {
			return lon;
		}
		public String getLat() {
			return lat;
		}
		private String place_Id;
		private String placeName;
		private String lon;
		private String lat;
		
		public Place build() {
			return new Place(this);
		}
	}
	

}
