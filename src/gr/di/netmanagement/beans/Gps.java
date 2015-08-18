package gr.di.netmanagement.beans;

import java.util.Date;

/**
 * The Class Gps.
 */
public class Gps implements LocationBean {

	/** The id. */
	private int id;

	/** The email. */
	private String user;

	/** The location. */
	private Location location;

	/** The timestamp. */
	private Date timestamp;

	@Override
	public int getId() {

		return id;
	}

	public void setId(final int id) {

		this.id = id;
	}

	@Override
	public String getUser() {

		return user;
	}

	public void setUser(final String user) {

		this.user = user;
	}

	@Override
	public Location getLocation() {

		return location;
	}

	public void setLocation(final Location location) {

		this.location = location;
	}

	@Override
	public Date getTimestamp() {

		return timestamp;
	}

	public void setTimestamp(final Date timestamp) {

		this.timestamp = timestamp;
	}

	/**
	 * Instantiates a new gps.
	 *
	 * @param id
	 *            the id
	 * @param email
	 *            the email
	 * @param latitude
	 *            the latitude
	 * @param longtitude
	 *            the longtitude
	 * @param timestamp
	 *            the timestamp
	 */
	public Gps(final int id, final String user, final String latitude,
			final String longtitude, final Date timestamp) {

		this.id = id;
		this.user = user;
		this.location = new Location(latitude, longtitude);
		this.timestamp = timestamp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "Gps [id=" + id + ", user=" + user + ", location=" + location
				+ ", timestamp=" + timestamp + "]";
	}

}
