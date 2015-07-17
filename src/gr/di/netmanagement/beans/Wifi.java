package gr.di.netmanagement.beans;

import java.util.Date;

public class Wifi {

	private int id;

	private String email;

	private String ssid;

	private String bssid;

	private int level;

	private int frequency;

	private Location location;

	private Date timestamp;

	public int getId() {

		return id;
	}

	public void setId(int id) {

		this.id = id;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getSsid() {

		return ssid;
	}

	public void setSsid(String ssid) {

		this.ssid = ssid;
	}

	public String getBssid() {

		return bssid;
	}

	public void setBssid(String bssid) {

		this.bssid = bssid;
	}

	public int getLevel() {

		return level;
	}

	public void setLevel(int level) {

		this.level = level;
	}

	public int getFrequency() {

		return frequency;
	}

	public void setFrequency(int frequency) {

		this.frequency = frequency;
	}

	public Location getLocation() {

		return location;
	}

	public void setLocation(Location location) {

		this.location = location;
	}

	public Date getTimestamp() {

		return timestamp;
	}

	public void setTimestamp(Date timestamp) {

		this.timestamp = timestamp;
	}

	public Wifi(int id, String email, String ssid, String bssid, int level,
			int frequency, String latitude, String longtitude, Date timestamp) {

		this.id = id;
		this.email = email;
		this.ssid = ssid;
		this.bssid = bssid;
		this.level = level;
		this.frequency = frequency;
		if (!(latitude.charAt(0) == 'N') && !(longtitude.charAt(0) == 'N')) {
			this.location = new Location(Double.valueOf(latitude),
					Double.valueOf(longtitude));
		} else {
			this.location = new Location(-1.0f, -1.0f);
		}
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {

		return "Wifi [id=" + id + ", email=" + email + ", ssid=" + ssid
				+ ", bssid=" + bssid + ", level=" + level + ", frequency="
				+ frequency + ", location=" + location + ", timestamp="
				+ timestamp + "]";
	}

}
