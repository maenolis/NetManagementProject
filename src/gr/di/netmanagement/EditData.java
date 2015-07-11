package gr.di.netmanagement;

import java.util.ArrayList;
import java.util.HashMap;

public class EditData {

	private ArrayList<Object> accessPointsList;

	HashMap<String, ArrayList<Wifi>> wifiMap;

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

	public void printWifiAccessPoints() {

		for (String key : wifiMap.keySet()) {
			System.out.println(wifiMap.get(key));
		}
	}
}
