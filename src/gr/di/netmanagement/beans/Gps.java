package gr.di.netmanagement.beans;

import java.util.Date;

/**
 * The Class Gps.
 */
public class Gps {

	/** The id. */
	private int id;

	/** The email. */
	private String email;

	/** The location. */
	private Location location;

	/** The timestamp. */
	private Date timestamp;

	public int getId() {

		return id;
	}

	public void setId(final int id) {

		this.id = id;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(final String email) {

		this.email = email;
	}

	public Location getLocation() {

		return location;
	}

	public void setLocation(final Location location) {

		this.location = location;
	}

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
	public Gps(final int id, final String email, final String latitude,
			final String longtitude, final Date timestamp) {

		this.id = id;
		this.email = email;
		/*If "No latitude || No longtitude"*/
		if (!(latitude.charAt(0) == 'N') && !(longtitude.charAt(0) == 'N')) {
			this.location = new Location(Double.valueOf(latitude),
					Double.valueOf(longtitude));
		} else {
			this.location = new Location(-1.0f, -1.0f);
		}
		this.timestamp = timestamp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "Gps [id=" + id + ", email=" + email + ", location=" + location
				+ ", timestamp=" + timestamp + "]";
	}

}
