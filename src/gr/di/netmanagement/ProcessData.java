package gr.di.netmanagement;

import java.util.ArrayList;
import java.util.HashMap;

public class ProcessData {

	private ArrayList<Object> accessPointsList;

	private HashMap<String, ArrayList<Wifi>> wifiMap;

	private HashMap<String, Location> wifiLocations;

	public ArrayList<Object> getAccessPointsList() {

		return accessPointsList;
	}

	public void setAccessPointsList(ArrayList<Object> accessPointsList) {

		this.accessPointsList = accessPointsList;
	}

	public HashMap<String, ArrayList<Wifi>> getWifiMap() {

		return wifiMap;
	}

	public void setWifiMap(HashMap<String, ArrayList<Wifi>> wifiMap) {

		this.wifiMap = wifiMap;
	}

	public HashMap<String, Location> getWifiLocations() {

		return wifiLocations;
	}

	public void setWifiLocations(HashMap<String, Location> wifiLocations) {

		this.wifiLocations = wifiLocations;
	}

	private static final double PI = 3.14159265359f;

	ProcessData() {

		accessPointsList = new ArrayList<Object>();
		wifiMap = new HashMap<String, ArrayList<Wifi>>();
		wifiLocations = new HashMap<String, Location>();
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

	}

	public void computeAccessPointsLocation() {

		for (String key : wifiMap.keySet()) {
			Double totalweight = 0.0, latitudeSum = 0.0, longtitudeSum = 0.0, latitude, longtitude;
			Double rssi;
			double weight = 0;

			ArrayList<Wifi> list = wifiMap.get(key);
			if (list.size() < 2) {
				wifiLocations.put(key, list.get(0).getLocation());
				continue;
			}

			for (Wifi ap : list) {
				latitude = ap.getLocation().getLatitude();
				longtitude = ap.getLocation().getLongtitude();
				if (latitude == -1.0f && longtitude == -1.0f) {
					System.out.println("No location for this ap.");
				}
				rssi = (double) ap.getLevel();

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
