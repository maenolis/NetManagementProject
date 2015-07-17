package gr.di.netmanagement;

import gr.di.netmanagement.beans.BaseStation;
import gr.di.netmanagement.beans.Battery;
import gr.di.netmanagement.beans.FileType;
import gr.di.netmanagement.beans.Gps;
import gr.di.netmanagement.beans.Wifi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DataReader {

	private static File baseStationFile;

	private static File batteryFile;

	private static File gpsFile;

	private static File wifiFile;

	public static File getBaseStationFile() {

		return baseStationFile;
	}

	public static void setBaseStationFile(File baseStationFile) {

		DataReader.baseStationFile = baseStationFile;
	}

	public static File getBatteryFile() {

		return batteryFile;
	}

	public static void setBatteryFile(File batteryFile) {

		DataReader.batteryFile = batteryFile;
	}

	public static File getGpsFile() {

		return gpsFile;
	}

	public static void setGpsFile(File gpsFile) {

		DataReader.gpsFile = gpsFile;
	}

	public static File getWifiFile() {

		return wifiFile;
	}

	public static void setWifiFile(File wifiFile) {

		DataReader.wifiFile = wifiFile;
	}

	public DataReader() {

		baseStationFile = new File("data/base_station.csv");
		batteryFile = new File("data/battery.csv");
		gpsFile = new File("data/gps.csv");
		wifiFile = new File("data/wifi.csv");
	}

	public ArrayList<Object> readFile(FileType fileType) throws Exception {

		ArrayList<Object> list = new ArrayList<Object>();
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		String[] splittedLine = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		Date date = null;
		File file = null;
		boolean firstLine = true;
		try {
			switch (fileType) {
			case BASE_STATION:
				file = getBaseStationFile();
				break;
			case BATTERY:
				file = getBatteryFile();
				break;
			case GPS:
				file = getGpsFile();
				break;
			case WIFI:
				file = getWifiFile();
				break;
			default:
				throw new Exception("Wrong FileType given.");
			}

			fr = new FileReader(file);
			br = new BufferedReader(fr, 1024);
			while ((line = br.readLine()) != null) {
				if (line.isEmpty()) {
					break;
				}
				if (firstLine) {
					firstLine = false;
					continue;
				}
				switch (fileType) {
				case BASE_STATION:
					splittedLine = new String[10];
					splittedLine = line.split("\\t");
					date = sf.parse(splittedLine[9]);
					list.add(new BaseStation(Integer.valueOf(splittedLine[0]),
							splittedLine[1], splittedLine[2], splittedLine[3],
							splittedLine[4], splittedLine[5], splittedLine[6],
							splittedLine[7], splittedLine[8], date));
					break;
				case BATTERY:
					splittedLine = new String[7];
					splittedLine = line.split("\\t");
					date = sf.parse(splittedLine[6]);
					list.add(new Battery(Integer.valueOf(splittedLine[0]),
							splittedLine[1], Integer.valueOf(splittedLine[2]),
							Integer.valueOf(splittedLine[3]), Integer
									.valueOf(splittedLine[4]), Integer
									.valueOf(splittedLine[5]), date));
					break;
				case WIFI:
					splittedLine = new String[9];
					splittedLine = line.split("\\t");
					date = sf.parse(splittedLine[8]);
					list.add(new Wifi(Integer.valueOf(splittedLine[0]),
							splittedLine[1], splittedLine[2], splittedLine[3],
							Integer.valueOf(splittedLine[4]), Integer
									.valueOf(splittedLine[5]), splittedLine[6],
							splittedLine[7], date));
					break;
				case GPS:
					splittedLine = new String[5];
					splittedLine = line.split("\\t");
					date = sf.parse(splittedLine[4]);
					list.add(new Gps(Integer.valueOf(splittedLine[0]),
							splittedLine[1], splittedLine[2], splittedLine[3],
							date));
					break;
				default:
					throw new Exception("Wrong FileType given.");
				}
				//System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}
}
