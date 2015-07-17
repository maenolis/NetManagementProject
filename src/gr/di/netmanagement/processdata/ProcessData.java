package gr.di.netmanagement.processdata;

import gr.di.netmanagement.beans.FileType;
import gr.di.netmanagement.beans.Location;
import gr.di.netmanagement.beans.Wifi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ProcessData {
	
	private HashMap<String, ArrayList<Object>> baseStationMap;
	
	private boolean baseStationReaded;
	
	private HashMap<String, ArrayList<Object>> batteryMap;

	private boolean batteryReaded;
	
	private HashMap<String, ArrayList<Object>> gpsMap;
	
	private boolean gpsReaded;
	
	private HashMap<String, ArrayList<Object>> wifiMap;
	
	private boolean wifiReaded;
	
	private HashMap<String, Location> wifiLocations;
	
	private HashSet<String> usersSet;
	
	private boolean usersReaded;
	
	private DataReader dataReader;

	public HashMap<String, ArrayList<Object>> getBaseStationMap() {
		return baseStationMap;
	}

	public void setBaseStationMap(HashMap<String, ArrayList<Object>> baseStationMap) {
		this.baseStationMap = baseStationMap;
	}

	public HashMap<String, ArrayList<Object>> getBatteryMap() {
		return batteryMap;
	}

	public void setBatteryMap(HashMap<String, ArrayList<Object>> batteryMap) {
		this.batteryMap = batteryMap;
	}

	public HashMap<String, ArrayList<Object>> getGpsMap() {
		return gpsMap;
	}

	public void setGpsMap(HashMap<String, ArrayList<Object>> gpsMap) {
		this.gpsMap = gpsMap;
	}

	public HashMap<String, ArrayList<Object>> getWifiMap() {
		return wifiMap;
	}

	public void setWifiMap(HashMap<String, ArrayList<Object>> wifiMap) {
		this.wifiMap = wifiMap;
	}

	public HashMap<String, Location> getWifiLocations() {

		return wifiLocations;
	}

	public void setWifiLocations(HashMap<String, Location> wifiLocations) {

		this.wifiLocations = wifiLocations;
	}

	public HashSet<String> getUsersSet() {
		readUsers();
		return usersSet;
	}

	public void setUsersSet(HashSet<String> usersSet) {
		this.usersSet = usersSet;
	}

	private static final double PI = 3.14159265359f;

	public ProcessData() {
		dataReader = new DataReader();
		baseStationReaded = false;
		batteryReaded = false;
		gpsReaded= false;
		wifiReaded = false;
		usersReaded = false;
		wifiLocations = new HashMap<String, Location>();
		usersSet = new HashSet<String>();
	}
	
	public void readBaseStations() {
		if (!baseStationReaded) {
			baseStationReaded = true;
			try {
				baseStationMap = dataReader.readFile(FileType.BASE_STATION);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void readBatteries() {
		if (!batteryReaded) {
			batteryReaded = true;
			try {
				batteryMap = dataReader.readFile(FileType.BATTERY);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void readGps() {
		if (!gpsReaded) {
			gpsReaded = true;
			try {
				gpsMap = dataReader.readFile(FileType.GPS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void readWifis() {
		if (!wifiReaded) {
			wifiReaded = true;
			try {
				wifiMap = dataReader.readFile(FileType.WIFI);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
	
	public void readUsers() {
		if (!usersReaded) {
			usersReaded = true;
			readWifis();
			readBaseStations();
			//usersSet.addAll(wifiMap.keySet());
			usersSet.addAll(baseStationMap.keySet());
		}
		
	}

	public void computeAccessPointsLocation() {
		
		readWifis();
		for (String key : wifiMap.keySet()) {
			double totalweight = 0.0, latitudeSum = 0.0, longtitudeSum = 0.0, latitude, longtitude;
			double rssi;
			double weight = 0;

			ArrayList<Object> list = wifiMap.get(key);
			if (list.size() < 2) {
				wifiLocations.put(key, ((Wifi) list.get(0)).getLocation());
				continue;
			}

			for (Object ap : list) {
				latitude = ((Wifi) ap).getLocation().getLatitude();
				longtitude = ((Wifi) ap).getLocation().getLongtitude();
				if (latitude == -1.0f && longtitude == -1.0f) {
					System.out.println("No location for this ap.");
				}
				rssi = (double) ((Wifi) ap).getLevel();

				// Convert latitude and longtitude from degrees to radians.
				latitude = latitude * PI / 180;
				longtitude = longtitude * PI / 180;

				// Convert dBm to mW.
				weight = Math.pow(10, rssi / 10);
				totalweight += weight;

				// Calculate latitude*weight and longtitude*weight
				latitudeSum += latitude * weight;
				longtitudeSum += longtitude * weight;
			}
			// Calculate the weighted latitude and longtitude.
			latitude = latitudeSum / totalweight;
			longtitude = longtitudeSum / totalweight;

			wifiLocations.put(key, new Location(latitude / (PI / 180),
					longtitude / (PI / 180)));
			// System.out.println(latitude / (PI / 180) + " kai " + longtitude
			// / (PI / 180));
		}
		System.out.println(wifiLocations.get("18:17:25:21:93:02"));
	}
}
