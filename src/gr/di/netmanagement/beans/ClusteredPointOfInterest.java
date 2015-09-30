package gr.di.netmanagement.beans;

/**
 * The Class ClusteredPointOfInterest.
 */
public class ClusteredPointOfInterest {

	/** The location. */
	private Location location;

	/** The north west bound. */
	private Location northWestBound;

	/** The south west bound. */
	private final Location southWestBound;

	/** The south east bound. */
	private Location southEastBound;

	/** The north east bound. */
	private final Location northEastBound;

	public Location getLocation() {
		return location;
	}

	public void setLocation(final Location location) {
		this.location = location;
	}

	public Location getNorthWestBound() {
		return northWestBound;
	}

	public void setNorthWestBound(final Location northWestBound) {
		this.northWestBound = northWestBound;
	}

	public Location getSouthEastBound() {
		return southEastBound;
	}

	public void setSouthEastBound(final Location southEastBound) {
		this.southEastBound = southEastBound;
	}

	public Location getSouthWestBound() {
		return southWestBound;
	}

	public Location getNorthEastBound() {
		return northEastBound;
	}

	/**
	 * Instantiates a new clustered point of interest.
	 *
	 * @param location
	 *            the location
	 * @param northWestBound
	 *            the north west bound
	 * @param southEastBound
	 *            the south east bound
	 */
	public ClusteredPointOfInterest(final Location location,
			final Location northWestBound, final Location southEastBound) {
		super();
		this.location = location;
		this.northWestBound = northWestBound;
		this.southEastBound = southEastBound;

		this.southWestBound = new Location(String.valueOf(southEastBound
				.getLatitude()), String.valueOf(northWestBound.getLongtitude()));
		this.northEastBound = new Location(String.valueOf(northWestBound
				.getLatitude()), String.valueOf(southEastBound.getLongtitude()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClusteredPointOfInterest [location=" + location
				+ ", northWestBound=" + northWestBound + ", southWestBound="
				+ southWestBound + ", southEastBound=" + southEastBound
				+ ", northEastBound=" + northEastBound + "]";
	}

}
