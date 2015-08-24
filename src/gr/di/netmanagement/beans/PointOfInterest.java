package gr.di.netmanagement.beans;

import java.util.Date;

/**
 * The Class PointOfInterest.
 */
public class PointOfInterest {

	/** The location. */
	private Location location;

	/** The start. */
	private Date start;

	/** The end. */
	private Date end;

	public Location getLocation() {
		return location;
	}

	public void setLocation(final Location location) {
		this.location = location;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(final Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(final Date end) {
		this.end = end;
	}

	/**
	 * Instantiates a new point of interest.
	 *
	 * @param location
	 *            the location
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 */
	public PointOfInterest(final Location location, final Date start,
			final Date end) {

		this.location = location;
		this.start = start;
		this.end = end;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PointOfInterest [location=" + location + ", start=" + start
				+ ", end=" + end + "]";
	}

}
