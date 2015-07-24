package gr.di.netmanagement.beans;

import java.util.Date;

/**
 * The Class BaseStation.
 */
public class BaseStation {

	/** The id. */
	private int id;

	/** The user. */
	private String user;

	/** The operator. */
	private String operator;

	/** The mcc. */
	private String mcc;

	/** The mnc. */
	private String mnc;

	/** The cid. */
	private String cid;

	/** The lac. */
	private String lac;

	/** The location. */
	private Location location;

	/** The timestamp. */
	private Date timestamp;

	/**
	 * Instantiates a new base station.
	 *
	 * @param id
	 *            the id
	 * @param user
	 *            the user
	 * @param operator
	 *            the operator
	 * @param mcc
	 *            the mcc
	 * @param mnc
	 *            the mnc
	 * @param cid
	 *            the cid
	 * @param lac
	 *            the lac
	 * @param latitude
	 *            the latitude
	 * @param longtitude
	 *            the longtitude
	 * @param timestamp
	 *            the timestamp
	 */
	public BaseStation(final int id, final String user, final String operator,
			final String mcc, final String mnc, final String cid,
			final String lac, final String latitude, final String longtitude,
			final Date timestamp) {

		this.id = id;
		this.user = user;
		this.operator = operator;
		this.mcc = mcc;
		this.mnc = mnc;
		this.cid = cid;
		this.lac = lac;
		
		/*If "No latitude || No longtitude"*/
		if (!(latitude.charAt(0) == 'N') && !(longtitude.charAt(0) == 'N')) {
			this.location = new Location(Double.valueOf(latitude),
					Double.valueOf(longtitude));
		} else {
			this.location = new Location(-1.0f, -1.0f);
		}

		this.timestamp = timestamp;
	}

	public int getId() {

		return id;
	}

	public void setId(final int id) {

		this.id = id;
	}

	public String getUser() {

		return user;
	}

	public void setUser(final String user) {

		this.user = user;
	}

	public String getOperator() {

		return operator;
	}

	public void setOperator(final String operator) {

		this.operator = operator;
	}

	public String getMcc() {

		return mcc;
	}

	public void setMcc(final String mcc) {

		this.mcc = mcc;
	}

	public String getMnc() {

		return mnc;
	}

	public void setMnc(final String mnc) {

		this.mnc = mnc;
	}

	public String getCid() {

		return cid;
	}

	public void setCid(final String cid) {

		this.cid = cid;
	}

	public String getLac() {

		return lac;
	}

	public void setLac(final String lac) {

		this.lac = lac;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "BaseStation [id=" + id + ", user=" + user + ", operator="
				+ operator + ", mcc=" + mcc + ", mnc=" + mnc + ", cid=" + cid
				+ ", lac=" + lac + ", location=" + location + ", timestamp="
				+ timestamp + "]";
	}

}
