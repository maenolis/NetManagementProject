package gr.di.netmanagement.beans;

import org.apache.commons.math3.ml.clustering.Clusterable;

/**
 * The Class Location.
 */
public class Location implements Clusterable {

	/**
	 * The latitude.
	 * 
	 * bigger means to the north. less means to the south.
	 * 
	 * */
	private double latitude;

	/**
	 * The longtitude.
	 * 
	 * bigger means to the east. less means to the west.
	 * */
	private double longtitude;

	private boolean empty;

	public double getLatitude() {

		return latitude;
	}

	public void setLatitude(final double latitude) {

		this.latitude = latitude;

	}

	public double getLongtitude() {

		return longtitude;
	}

	public void setLongtitude(final double longtitude) {

		this.longtitude = longtitude;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(final boolean empty) {
		this.empty = empty;
	}

	/**
	 * Instantiates a new location.
	 *
	 * @param latitude
	 *            the latitude
	 * @param longtitude
	 *            the longtitude
	 */
	public Location(final String latitude, final String longtitude) {

		/* If "No latitude && No longtitude" */
		if (!(latitude.charAt(0) == 'N') && !(longtitude.charAt(0) == 'N')) {
			this.latitude = Double.valueOf(latitude);
			this.longtitude = Double.valueOf(longtitude);
			this.empty = false;
		} else {
			this.latitude = -1.0f;
			this.longtitude = -1.0f;
			this.empty = true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "Location [latitude=" + latitude + ", longtitude=" + longtitude
				+ "]";
	}

	/* Clusterable interface function. */
	@Override
	public double[] getPoint() {
		double[] retArray = new double[2];
		retArray[0] = this.getLatitude();
		retArray[1] = this.getLongtitude();
		return retArray;
	}
}
