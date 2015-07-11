package gr.di.netmanagement;

import java.util.Date;

public class Wifi {

	private String id;

	private String email;

	private String ssid;

	private String bssid;

	private String level;

	private String frequency;

	private float latitude;

	private float longtitude;

	private Date timestamp;

	public String getId() {

		return id;
	}

	public void setId(String id) {

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

	public String getLevel() {

		return level;
	}

	public void setLevel(String level) {

		this.level = level;
	}

	public String getFrequency() {

		return frequency;
	}

	public void setFrequency(String frequency) {

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

	public Wifi(String id, String email, String ssid, String bssid,
			String level, String frequency, float latitude, float longtitude,
			Date timestamp) {

		this.id = id;
		this.email = email;
		this.ssid = ssid;
		this.bssid = bssid;
		this.level = level;
		this.frequency = frequency;
		this.latitude = latitude;
		this.longtitude = longtitude;
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
