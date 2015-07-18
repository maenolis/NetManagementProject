package gr.di.netmanagement.processdata;

import gr.di.netmanagement.beans.BaseStation;
import gr.di.netmanagement.beans.Battery;
import gr.di.netmanagement.beans.FileType;
import gr.di.netmanagement.beans.Gps;
import gr.di.netmanagement.beans.Location;
import gr.di.netmanagement.beans.Wifi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

// TODO: Auto-generated Javadoc
/**
 * The Class DataReader.
 */
public class DataReader {

	/** The base station stream. */
	private static InputStream baseStationStream;

	/** The battery stream. */
	private static InputStream batteryStream;

	/** The gps stream. */
	private static InputStream gpsStream;

	/** The wifi stream. */
	private static InputStream wifiStream;

	/** The base station map. */
	private HashMap<String, ArrayList<Object>> baseStationMap;

	/** The base station readed. */
	private boolean baseStationReaded;

	/** The battery map. */
	private HashMap<String, ArrayList<Object>> batteryMap;

	/** The battery readed. */
	private boolean batteryReaded;

	/** The gps map. */
	private HashMap<String, ArrayList<Object>> gpsMap;

	/** The gps readed. */
	private boolean gpsReaded;

	/** The wifi map. */
	private HashMap<String, ArrayList<Object>> wifiMap;

	/** The wifi readed. */
	private boolean wifiReaded;

	/** The wifi locations. */
	private HashMap<String, Location> wifiLocations;

	/** The users set. */
	private HashSet<String> usersSet;

	/** The users readed. */
	private boolean usersReaded;

	/** The Constant PI. */
	private static final double PI = 3.14159265359f;

	public HashMap<String, ArrayList<Object>> getBaseStationMap() {

		return baseStationMap;
	}

	public void setBaseStationMap(
			final HashMap<String, ArrayList<Object>> baseStationMap) {

		this.baseStationMap = baseStationMap;
	}

	public HashMap<String, ArrayList<Object>> getBatteryMap() {

		return batteryMap;
	}

	public void setBatteryMap(
			final HashMap<String, ArrayList<Object>> batteryMap) {

		this.batteryMap = batteryMap;
	}

	public HashMap<String, ArrayList<Object>> getGpsMap() {

		return gpsMap;
	}

	public void setGpsMap(final HashMap<String, ArrayList<Object>> gpsMap) {

		this.gpsMap = gpsMap;
	}

	public HashMap<String, ArrayList<Object>> getWifiMap() {

		return wifiMap;
	}

	public void setWifiMap(final HashMap<String, ArrayList<Object>> wifiMap) {

		this.wifiMap = wifiMap;
	}

	public HashMap<String, Location> getWifiLocations() {

		return wifiLocations;
	}

	public void setWifiLocations(final HashMap<String, Location> wifiLocations) {

		this.wifiLocations = wifiLocations;
	}

	public HashSet<String> getUsersSet() {

		readUsers();
		return usersSet;
	}

	public void setUsersSet(final HashSet<String> usersSet) {

		this.usersSet = usersSet;
	}

	/**
	 * Instantiates a new data reader.
	 *
	 * @throws URISyntaxException
	 *             the URI syntax exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public DataReader() throws URISyntaxException, IOException {

		baseStationStream = (new URL(
				"http://localhost:8080/data/base_station.csv"))
				.openConnection().getInputStream();

		batteryStream = (new URL("http://localhost:8080/data/battery.csv"))
				.openConnection().getInputStream();
		gpsStream = (new URL("http://localhost:8080/data/gps.csv"))
				.openConnection().getInputStream();
		wifiStream = (new URL("http://localhost:8080/data/wifi.csv"))
				.openConnection().getInputStream();
		baseStationReaded = false;
		batteryReaded = false;
		gpsReaded = false;
		wifiReaded = false;
		usersReaded = false;
		wifiLocations = new HashMap<String, Location>();
		usersSet = new HashSet<String>();
	}

	/**
	 * Read file.
	 *
	 * @param fileType
	 *            the file type
	 * @return the hash map
	 * @throws Exception
	 *             the exception
	 */
	public HashMap<String, ArrayList<Object>> readFile(final FileType fileType)
			throws Exception {

		HashMap<String, ArrayList<Object>> map = new HashMap<String, ArrayList<Object>>();
		BufferedReader br = null;
		String line = null;
		String[] splittedLine = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		Date date = null;
		InputStream stream = null;
		boolean firstLine = true;
		try {
			switch (fileType) {
			case BASE_STATION:
				stream = baseStationStream;
				break;
			case BATTERY:
				stream = batteryStream;
				break;
			case GPS:
				stream = gpsStream;
				break;
			case WIFI:
				stream = wifiStream;
				break;
			default:
				throw new Exception("Wrong FileType given.");
			}

			br = new BufferedReader(new InputStreamReader(stream), 1024);
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
					usersSet.add(splittedLine[1]);
					BaseStation baseStation = new BaseStation(
							Integer.valueOf(splittedLine[0]), splittedLine[1],
							splittedLine[2], splittedLine[3], splittedLine[4],
							splittedLine[5], splittedLine[6], splittedLine[7],
							splittedLine[8], date);
					if (!map.containsKey(splittedLine[1])) {
						ArrayList<Object> list = new ArrayList<Object>();
						list.add(baseStation);
						map.put(splittedLine[1], list);
					} else {
						map.get(splittedLine[1]).add(baseStation);
					}

					break;
				case BATTERY:
					splittedLine = new String[7];
					splittedLine = line.split("\\t");
					date = sf.parse(splittedLine[6]);
					Battery battery = new Battery(
							Integer.valueOf(splittedLine[0]), splittedLine[1],
							Integer.valueOf(splittedLine[2]),
							Integer.valueOf(splittedLine[3]),
							Integer.valueOf(splittedLine[4]),
							Integer.valueOf(splittedLine[5]), date);
					if (!map.containsKey(splittedLine[1])) {
						ArrayList<Object> list = new ArrayList<Object>();
						list.add(battery);
						map.put(splittedLine[1], list);
					} else {
						map.get(splittedLine[1]).add(battery);
					}
					break;
				case WIFI:
					splittedLine = new String[9];
					splittedLine = line.split("\\t");
					date = sf.parse(splittedLine[8]);
					usersSet.add(splittedLine[1]);
					Wifi wifi = new Wifi(Integer.valueOf(splittedLine[0]),
							splittedLine[1], splittedLine[2], splittedLine[3],
							Integer.valueOf(splittedLine[4]),
							Integer.valueOf(splittedLine[5]), splittedLine[6],
							splittedLine[7], date);
					if (!map.containsKey(splittedLine[1])) {
						ArrayList<Object> list = new ArrayList<Object>();
						list.add(wifi);
						map.put(splittedLine[3], list);
					} else {
						map.get(splittedLine[3]).add(wifi);
					}
					break;
				case GPS:
					splittedLine = new String[5];
					splittedLine = line.split("\\t");
					date = sf.parse(splittedLine[4]);
					Gps gps = new Gps(Integer.valueOf(splittedLine[0]),
							splittedLine[1], splittedLine[2], splittedLine[3],
							date);
					if (!map.containsKey(splittedLine[1])) {
						ArrayList<Object> list = new ArrayList<Object>();
						list.add(gps);
						map.put(splittedLine[3], list);
					} else {
						map.get(splittedLine[3]).add(gps);
					}
					break;
				default:
					throw new Exception("Wrong FileType given.");
				}
				// System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
					// fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return map;
	}

	/**
	 * Read base stations.
	 */
	public void readBaseStations() {

		if (!baseStationReaded) {
			baseStationReaded = true;
			try {
				baseStationMap = readFile(FileType.BASE_STATION);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Read batteries.
	 */
	public void readBatteries() {

		if (!batteryReaded) {
			batteryReaded = true;
			try {
				batteryMap = readFile(FileType.BATTERY);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Read gps.
	 */
	public void readGps() {

		if (!gpsReaded) {
			gpsReaded = true;
			try {
				gpsMap = readFile(FileType.GPS);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Read wifis.
	 */
	public void readWifis() {

		if (!wifiReaded) {
			wifiReaded = true;
			try {
				wifiMap = readFile(FileType.WIFI);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Read users.
	 */
	public void readUsers() {

		if (!usersReaded) {
			usersReaded = true;
			readWifis();
			readBaseStations();
		}

	}

	/**
	 * Compute access points location.
	 */
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
				rssi = ((Wifi) ap).getLevel();

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
