package gr.di.netmanagement;

import java.util.Date;

public class BaseStation{

	private int id;
	private String user;
	private String operator;
	private String mcc;
	private String mnc;
	private String cid;
	private String lac;
	private float latitude;
	private float longtitude;
	private Date timestamp;
	
	public BaseStation(int id, String user, String operator, String mcc,
			String mnc, String cid, String lac, long latitude, long longtitude,
			Date timestamp) {
		super();
		this.id = id;
		this.user = user;
		this.operator = operator;
		this.mcc = mcc;
		this.mnc = mnc;
		this.cid = cid;
		this.lac = lac;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.timestamp = timestamp;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public String getUser(){
		return user;
	}
	
	public void setUser(String user){
		this.user=user;
	}
	
	public String getOperator(){
		return operator;
	}
	
	public void setOperator(String operator){
		this.operator=operator;
	}
	
	public String getMcc(){
		return mcc;
	}
	
	public void setMcc(String mcc){
		this.mcc=mcc;
	}
	
	public String getMnc(){
		return mnc;
	}
	
	public void setMnc(String mnc){
		this.mnc=mnc;
	}
	
	public String getCid(){
		return cid;
	}
	
	public void setCid(String cid){
		this.cid=cid;
	}
	
	public String getLac(){
		return lac;
	}
	
	public void setLac(String lac){
		this.lac=lac;
	}
	
	public float getLatitude(){
		return latitude;
	}
	
	public void setLatitude(float latitude){
		this.latitude=latitude;
	}
	
	public float getLongtitude(){
		return longtitude;
	}
	
	public void setLongtitude(float longtitude){
		this.longtitude=longtitude;
	}
	
	public Date getTimestamp(){
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp){
		this.timestamp=timestamp;
	}


	
}
