package gr.di.netmanagement;

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

	public DataReader() {

		baseStationFile = new File("data/base_station.csv");
		batteryFile = new File("data/battery.csv");
		gpsFile = new File("data/gps.csv");
		wifiFile = new File("data/wifi.csv");
	}

	public ArrayList<Object> readFile(File file, FileType fileType)
			throws Exception {

		ArrayList<Object> list = new ArrayList<Object>();
		FileReader fr = null;
		BufferedReader br = null;
		String line = null;
		int counter = 0;
		try {
			fr = new FileReader(gpsFile);
			br = new BufferedReader(fr, 1024);
			while ((line = br.readLine()) != null) {
				if (counter < 2) {
					counter++;
					continue;
				}
				switch (fileType) {
				case BASE_STATION:
					break;
				case BATTERY:
					break;
				case WIFI:
					break;
				case GPS:
					String[] splittedLine = new String[5];
					splittedLine = line.split("\\t");
					SimpleDateFormat sf = new SimpleDateFormat(
							"yyyy-mm-dd HH:mm:ss");
					Date date = sf.parse(splittedLine[4]);
					list.add(new Gps(splittedLine[0], splittedLine[1], Float
							.valueOf(splittedLine[2]), Float
							.valueOf(splittedLine[3]), date));
					break;
				default:
					throw new Exception("Wrong FileType given.");
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
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}
}
