package gr.di.netmanagement;

import java.util.Date;

public class Gps {

	private int id;

	private String email;

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

	public Gps(int id, String email, String latitude, String longtitude,
			Date timestamp) {

		this.id = id;
		this.email = email;
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

		return "Gps [id=" + id + ", email=" + email + ", location=" + location
				+ ", timestamp=" + timestamp + "]";
	}

}
