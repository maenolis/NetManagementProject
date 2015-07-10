package gr.di.netmanagement;

import java.io.File;

public class NetManagementProj {

	private static File baseStationFile;

	private static File batteryFile;

	private static File gpsFile;

	private static File wifiFile;

	public static void main(String[] args) {

		baseStationFile = new File("data/base_station.csv");
		batteryFile = new File("data/battery.csv");
		gpsFile = new File("data/gps.csv");
		wifiFile = new File("data/wifi.csv");
	}
}
