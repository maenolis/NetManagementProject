package gr.di.netmanagement.beans;

/**
 * The Class ClusteredPointOfInterest.
 */
public class ClusteredPointOfInterest {

	/** The location. */
	private Location location;

	/** The north west bound. */
	private Location northWestBound;

	/** The south east bound. */
	private Location southEastBound;

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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CLusteredPointOfInterest [location=" + location
				+ ", northWestBound=" + northWestBound + ", southEastBound="
				+ southEastBound + "]";
	}

}
