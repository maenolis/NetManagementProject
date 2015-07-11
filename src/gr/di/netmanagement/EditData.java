package gr.di.netmanagement;

import java.util.ArrayList;
import java.util.HashMap;

public class EditData {

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

	private static final float PI = 3.14f;

	EditData() {

		accessPointsList = new ArrayList<Object>();
		wifiMap = new HashMap<String, ArrayList<Wifi>>();
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
			ArrayList<Wifi> list = wifiMap.get(key);
			if (list.size() < 2) {
				continue;
			}
			// TODO
			System.out.println(list);
		}
	}
}
