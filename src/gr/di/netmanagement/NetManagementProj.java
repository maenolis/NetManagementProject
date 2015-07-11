package gr.di.netmanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
		//alla
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		try {
			fr = new FileReader(baseStationFile);
			br = new BufferedReader(fr, 1024);
			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
