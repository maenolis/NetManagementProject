package gr.di.netmanagement;

import java.util.Date;

public class Wifi {

	private int id;

	private String email;

	private String ssid;

	private String bssid;

	private int level;

	private int frequency;

	private float latitude;

	private float longtitude;

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

	public float getLatitude() {

		return latitude;
	}

	public void setLatitude(float latitude) {

		this.latitude = latitude;
	}

	public float getLongtitude() {

		return longtitude;
	}

	public void setLongtitude(float longtitude) {

		this.longtitude = longtitude;
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
		if (!(latitude.charAt(0) == 'N')) {
			this.latitude = Float.valueOf(latitude);
		} else {
			this.latitude = -1.0f;
		}
		if (!(longtitude.charAt(0) == 'N')) {
			this.longtitude = Float.valueOf(longtitude);
		} else {
			this.longtitude = -1.0f;
		}
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {

		return "Wifi [id=" + id + ", email=" + email + ", ssid=" + ssid
				+ ", bssid=" + bssid + ", level=" + level + ", frequency="
				+ frequency + ", latitude=" + latitude + ", longtitude="
				+ longtitude + ", timestamp=" + timestamp + "]";
	}

}
