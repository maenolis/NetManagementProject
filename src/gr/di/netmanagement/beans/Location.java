package gr.di.netmanagement.beans;

/**
 * The Class Location.
 */
public class Location {

	/** The latitude. */
	private double latitude;

	/** The longtitude. */
	private double longtitude;

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
		} else {
			this.latitude = -1.0f;
			this.longtitude = -1.0f;
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
}
