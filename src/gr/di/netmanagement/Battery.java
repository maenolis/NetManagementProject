package gr.di.netmanagement;

import java.util.Date;

public class Battery {

	private int id;

	private String user;

	private int level;

	private int plugged;

	private int temperature;

	private int voltage;

	private Date timestamp;

	public Battery(int id, String user, int level, int plugged,
			int temperature, int voltage, Date timestamp) {

		this.id = id;
		this.user = user;
		this.level = level;
		this.plugged = plugged;
		this.temperature = temperature;
		this.voltage = voltage;
		this.timestamp = timestamp;
	}

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public String getUser() {

		return user;
	}

	public void setUser(String user) {

		this.user = user;
	}

	public int getLevel() {

		return level;
	}

	public void setLevel(int level) {

		this.level = level;
	}

	public int getPlugged() {

		return plugged;
	}

	public void setPlugged(int plugged) {

		this.plugged = plugged;
	}

	public int getTemperature() {

		return temperature;
	}

	public void setTemperature(int temperature) {

		this.temperature = temperature;
	}

	public int getVoltage() {

		return voltage;
	}

	public void setVoltage(int voltage) {

		this.voltage = voltage;
	}

	public Date getTimestamp() {

		return timestamp;
	}

	public void setTimestamp(Date timestamp) {

		this.timestamp = timestamp;
	}

}
