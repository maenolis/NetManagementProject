package gr.di.netmanagement;

import java.util.Date;

public class Gps {

	private int id;

	private String email;

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

	public Gps(int id, String email, String latitude, String longtitude,
			Date timestamp) {

		this.id = id;
		this.email = email;
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

		return "Gps [id=" + id + ", email=" + email + ", latitude=" + latitude
				+ ", longtitude=" + longtitude + ", timestamp=" + timestamp
				+ "]";
	}

}
