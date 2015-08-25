package gr.di.netmanagement.beans;

public class CLusteredPointOfInterest {

	private Location location;

	private Location northWestBound;

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

	public CLusteredPointOfInterest(final Location location,
			final Location northWestBound, final Location southEastBound) {
		super();
		this.location = location;
		this.northWestBound = northWestBound;
		this.southEastBound = southEastBound;
	}

	@Override
	public String toString() {
		return "CLusteredPointOfInterest [location=" + location
				+ ", northWestBound=" + northWestBound + ", southEastBound="
				+ southEastBound + "]";
	}

}
