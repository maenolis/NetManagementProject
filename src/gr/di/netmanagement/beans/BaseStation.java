package gr.di.netmanagement.beans;

import java.util.Date;

/**
 * The Class BaseStation.
 */
public class BaseStation implements LocationBean {

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
		this.location = new Location(latitude, longtitude);
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

	public String getOperator() {

		String tmp = operator.toLowerCase();

		if (tmp.contains("cosmot")) {
			return "Cosmote";
		}
		if (tmp.contains("vodafon") || tmp.contains("cu")) {
			return "Vodafone";
		}
		if (tmp.contains("wind")) {
			return "Wind";
		}
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
