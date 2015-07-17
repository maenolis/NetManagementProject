package gr.di.netmanagement.processdata;

import gr.di.netmanagement.beans.FileType;
import gr.di.netmanagement.beans.Location;
import gr.di.netmanagement.beans.Wifi;

import java.util.ArrayList;
import java.util.HashMap;

public class ProcessData {
	
	private HashMap<String, ArrayList<Object>> baseStationMap;
	
	private HashMap<String, ArrayList<Object>> batteryMap;

	private HashMap<String, ArrayList<Object>> gpsMap;
	
	private HashMap<String, ArrayList<Object>> wifiMap;
	
	private HashMap<String, Location> wifiLocations;

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

	private static final double PI = 3.14159265359f;

	public ProcessData() {

		DataReader dataReader = new DataReader();
		try {
			wifiMap = dataReader.readFile(FileType.WIFI);
		} catch (Exception e) {
			e.printStackTrace();
		}
		wifiLocations = new HashMap<String, Location>();
		//mapWifis();
	}
	
	/*public void mapWifis() {
		//TODO
		try {
			DataReader dr = new DataReader();
			accessPointsList = dr.readFile(FileType.WIFI);
			Wifi ap;
			for (Object obj : accessPointsList) {
				ap = (Wifi) obj;
				if (!wifiMap.containsKey(ap.getBssid())) {
					ArrayList<Wifi> list = new ArrayList<Wifi>();
					list.add(ap);
					wifiMap.put(ap.getBssid(), list);
				} else {
					wifiMap.get(ap.getBssid()).add(ap);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public void computeAccessPointsLocation() {

		for (String key : wifiMap.keySet()) {
			Double totalweight = 0.0, latitudeSum = 0.0, longtitudeSum = 0.0, latitude, longtitude;
			Double rssi;
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
