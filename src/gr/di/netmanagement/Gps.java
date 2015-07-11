package gr.di.netmanagement;

import java.util.Date;

public class Gps {

	private String id;

	private String email;

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

	public Gps(String id, String email, float latitude, float longtitude,
			Date timestamp) {

		this.id = id;
		this.email = email;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {

		return "Gps [id=" + id + ", email=" + email + ", latitude=" + latitude
				+ ", longtitude=" + longtitude + ", timestamp=" + timestamp
				+ "]";
	}

}
