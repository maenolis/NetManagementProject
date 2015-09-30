package gr.di.netmanagement.beans;

import java.util.Date;

/**
 * The Class Wifi.
 */
public class Wifi implements LocationBean {

	public Wifi(final Wifi wifi) {

		this.id = wifi.getId();
		this.user = wifi.getUser();
		this.ssid = wifi.getSsid();
		this.bssid = wifi.getBssid();
		this.level = wifi.getLevel();
		this.frequency = wifi.getFrequency();
		this.location = wifi.getLocation();
		this.timestamp = wifi.getTimestamp();
	}

	/** The id. */
	private int id;

	/** The email. */
	private String user;

	/** The ssid. */
	private String ssid;

	/** The bssid. */
	private String bssid;

	/** The level. */
	private int level;

	/** The frequency. */
	private int frequency;

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

	public String getSsid() {

		return ssid;
	}

	public void setSsid(final String ssid) {

		this.ssid = ssid;
	}

	public String getBssid() {

		return bssid;
	}

	public void setBssid(final String bssid) {

		this.bssid = bssid;
	}

	public int getLevel() {

		return level;
	}

	public void setLevel(final int level) {

		this.level = level;
	}

	public int getFrequency() {

		return frequency;
	}

	public void setFrequency(final int frequency) {

		this.frequency = frequency;
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
	 * Instantiates a new wifi.
	 *
	 * @param id
	 *            the id
	 * @param email
	 *            the email
	 * @param ssid
	 *            the ssid
	 * @param bssid
	 *            the bssid
	 * @param level
	 *            the level
	 * @param frequency
	 *            the frequency
	 * @param latitude
	 *            the latitude
	 * @param longtitude
	 *            the longtitude
	 * @param timestamp
	 *            the timestamp
	 */
	public Wifi(final int id, final String user, final String ssid,
			final String bssid, final int level, final int frequency,
			final String latitude, final String longtitude, final Date timestamp) {

		this.id = id;
		this.user = user;
		this.ssid = ssid;
		this.bssid = bssid;
		this.level = level;
		this.frequency = frequency;
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

		return "Wifi [id=" + id + ", user=" + user + ", ssid=" + ssid
				+ ", bssid=" + bssid + ", level=" + level + ", frequency="
				+ frequency + ", location=" + location + ", timestamp="
				+ timestamp + "]";
	}

}
