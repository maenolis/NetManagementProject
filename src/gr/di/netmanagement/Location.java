package gr.di.netmanagement;

public class Location {

	private double latitude;

	private double longtitude;

	public double getLatitude() {

		return latitude;
	}

	public void setLatitude(double latitude) {

		this.latitude = latitude;
	}

	public double getLongtitude() {

		return longtitude;
	}

	public void setLongtitude(double longtitude) {

		this.longtitude = longtitude;
	}

	public Location(double latitude, double longtitude) {

		this.latitude = latitude;
		this.longtitude = longtitude;
	}

	@Override
	public String toString() {

		return "Location [latitude=" + latitude + ", longtitude=" + longtitude
				+ "]";
	}
}
