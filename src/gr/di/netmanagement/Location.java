package gr.di.netmanagement;

public class Location {

	private float latitude;

	private float longtitude;

	public float getLatitude() {

		return latitude;
	}

	public void setLatitude(float latitude) {

		this.latitude = latitude;
	}

	public float getLongtitude() {

		return longtitude;
	}

	public void setLongtitude(float longtitude) {

		this.longtitude = longtitude;
	}

	public Location(float latitude, float longtitude) {

		super();
		this.latitude = latitude;
		this.longtitude = longtitude;
	}

	@Override
	public String toString() {

		return "Location [latitude=" + latitude + ", longtitude=" + longtitude
				+ "]";
	}
}
