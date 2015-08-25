package gr.di.netmanagement.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class Battery.
 */
public class Battery implements Bean {

	/** The id. */
	private int id;

	/** The user. */
	private String user;

	/** The level. */
	private int level;

	/** The plugged. */
	private int plugged;

	/** The temperature. */
	private int temperature;

	/** The voltage. */
	private int voltage;

	/** The timestamp. */
	private Date timestamp;

	/**
	 * Instantiates a new battery.
	 *
	 * @param id
	 *            the id
	 * @param user
	 *            the user
	 * @param level
	 *            the level
	 * @param plugged
	 *            the plugged
	 * @param temperature
	 *            the temperature
	 * @param voltage
	 *            the voltage
	 * @param timestamp
	 *            the timestamp
	 */
	public Battery(final int id, final String user, final int level,
			final int plugged, final int temperature, final int voltage,
			final Date timestamp) {

		this.id = id;
		this.user = user;
		this.level = level;
		this.plugged = plugged;
		this.temperature = temperature;
		this.voltage = voltage;
		this.timestamp = timestamp;
	}

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

	public int getLevel() {

		return level;
	}

	public void setLevel(final int level) {

		this.level = level;
	}

	public int getPlugged() {

		return plugged;
	}

	public void setPlugged(final int plugged) {

		this.plugged = plugged;
	}

	public int getTemperature() {

		return temperature;
	}

	public void setTemperature(final int temperature) {

		this.temperature = temperature;
	}

	public int getVoltage() {

		return voltage;
	}

	public void setVoltage(final int voltage) {

		this.voltage = voltage;
	}

	@Override
	public Date getTimestamp() {

		return timestamp;
	}

	/* Short date representation. */
	public String getTimestampShortString() {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(this.timestamp);
	}

	public String getTimeStampLongString() {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(this.timestamp);
	}

	public void setTimestamp(final Date timestamp) {

		this.timestamp = timestamp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "Battery [id=" + id + ", user=" + user + ", level=" + level
				+ ", plugged=" + plugged + ", temperature=" + temperature
				+ ", voltage=" + voltage + ", timestamp=" + timestamp + "]";
	}
}
