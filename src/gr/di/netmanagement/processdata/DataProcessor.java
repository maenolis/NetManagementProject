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

import javax.servlet.http.HttpSession;

/**
 * The Class DataReader.
 */
public class DataProcessor {

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

	/** The base station locations. */
	private HashMap<String, Location> bsLocations;

	/** The users set. */
	private HashSet<String> usersSet;

	/** The users readed. */
	private boolean usersReaded;

	/** The Constant PI. */
	private static final double PI = 3.14159265359f;

	public HashMap<String, ArrayList<Object>> getBaseStationMap() {

		readBaseStations();
		return baseStationMap;
	}

	public void setBaseStationMap(
			final HashMap<String, ArrayList<Object>> baseStationMap) {

		this.baseStationMap = baseStationMap;
	}

	public HashMap<String, ArrayList<Object>> getBatteryMap() {

		/* Null pointer prevention. */
		readBatteries();
		return batteryMap;
	}

	public void setBatteryMap(
			final HashMap<String, ArrayList<Object>> batteryMap) {

		this.batteryMap = batteryMap;
	}

	public HashMap<String, ArrayList<Object>> getGpsMap() {

		/* Null pointer prevention. */
		readGps();
		return gpsMap;
	}

	public void setGpsMap(final HashMap<String, ArrayList<Object>> gpsMap) {

		this.gpsMap = gpsMap;
	}

	public HashMap<String, ArrayList<Object>> getWifiMap() {

		/* Null pointer prevention. */
		readWifis();
		return wifiMap;
	}

	public void setWifiMap(final HashMap<String, ArrayList<Object>> wifiMap) {

		this.wifiMap = wifiMap;
	}

	public HashMap<String, Location> getWifiLocations() {

		/* Null pointer prevention. */
		readWifis();
		return wifiLocations;
	}

	public void setWifiLocations(final HashMap<String, Location> wifiLocations) {

		this.wifiLocations = wifiLocations;
	}

	public HashMap<String, Location> getbsLocations() {

		/* Null pointer prevention. */
		readBaseStations();
		return bsLocations;
	}

	public void setbsLocations(final HashMap<String, Location> bsLocations) {

		this.bsLocations = bsLocations;
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
	public DataProcessor() {

		/* Open streams for file reading from file server(tomcat). */
		try {
			baseStationStream = (new URL(
					"http://localhost:8080/data/base_station.csv"))
					.openConnection().getInputStream();
			batteryStream = (new URL("http://localhost:8080/data/battery.csv"))
					.openConnection().getInputStream();
			gpsStream = (new URL("http://localhost:8080/data/gps.csv"))
					.openConnection().getInputStream();
			wifiStream = (new URL("http://localhost:8080/data/wifi.csv"))
					.openConnection().getInputStream();
		} catch (IOException e) {
			try {
				baseStationStream.close();
				batteryStream.close();
				gpsStream.close();
				wifiStream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		/* Initialize variables */
		baseStationReaded = false;
		batteryReaded = false;
		gpsReaded = false;
		wifiReaded = false;
		usersReaded = false;
		wifiLocations = new HashMap<String, Location>();
		bsLocations = new HashMap<String, Location>();
		usersSet = new HashSet<String>();
	}

	public static DataProcessor getInstance(final HttpSession session) {

		/*
		 * if dataProcessor is set in session skip dataProcessor creation, data
		 * reading, streams etc
		 */
		if (session.getAttribute("dataProcessor") == null) {
			session.setAttribute("dataProcessor", new DataProcessor());
		}

		return (DataProcessor) session.getAttribute("dataProcessor");
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

		/* return object */
		HashMap<String, ArrayList<Object>> map = new HashMap<String, ArrayList<Object>>();
		BufferedReader br = null;
		String line = null;

		/* date formatting object */
		String[] splittedLine = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		InputStream stream = null;
		boolean firstLine = true;
		try {
			/* choose stream */
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

			/* read the entire stream */
			br = new BufferedReader(new InputStreamReader(stream), 1024);
			while ((line = br.readLine()) != null) {

				if (line.isEmpty()) {
					break;
				}

				/* avoid first line */
				if (firstLine) {
					firstLine = false;
					continue;
				}

				/* choose file type */
				switch (fileType) {
				case BASE_STATION:
					/* split line */
					splittedLine = new String[10];
					splittedLine = line.split("\\t");
					/* parse date */
					date = sf.parse(splittedLine[9]);
					/* add user to users set */
					usersSet.add(splittedLine[1]);
					/* create bean */
					BaseStation baseStation = new BaseStation(
							Integer.valueOf(splittedLine[0]), splittedLine[1],
							splittedLine[2], splittedLine[3], splittedLine[4],
							splittedLine[5], splittedLine[6], splittedLine[7],
							splittedLine[8], date);
					/* check if key exists */
					if (!map.containsKey(splittedLine[1])) {
						/* add empty list with first element */
						ArrayList<Object> list = new ArrayList<Object>();
						list.add(baseStation);
						map.put(splittedLine[1], list);
					} else {
						/* add element to existing list */
						map.get(splittedLine[1]).add(baseStation);
					}

					break;
				case BATTERY:
					/* split line */
					splittedLine = new String[7];
					splittedLine = line.split("\\t");
					/* parse date */
					date = sf.parse(splittedLine[6]);
					/* create bean */
					Battery battery = new Battery(
							Integer.valueOf(splittedLine[0]), splittedLine[1],
							Integer.valueOf(splittedLine[2]),
							Integer.valueOf(splittedLine[3]),
							Integer.valueOf(splittedLine[4]),
							Integer.valueOf(splittedLine[5]), date);
					/* check if key exists */
					if (!map.containsKey(splittedLine[1])) {
						/* add empty list with first element */
						ArrayList<Object> list = new ArrayList<Object>();
						list.add(battery);
						map.put(splittedLine[1], list);
					} else {
						/* add element to existing list */
						map.get(splittedLine[1]).add(battery);
					}
					break;
				case WIFI:
					/* split line */
					splittedLine = new String[9];
					splittedLine = line.split("\\t");
					/* parse date */
					date = sf.parse(splittedLine[8]);
					/* add user to users set */
					usersSet.add(splittedLine[1]);
					/* create bean */
					Wifi wifi = new Wifi(Integer.valueOf(splittedLine[0]),
							splittedLine[1], splittedLine[2], splittedLine[3],
							Integer.valueOf(splittedLine[4]),
							Integer.valueOf(splittedLine[5]), splittedLine[6],
							splittedLine[7], date);
					/* check if key exists */
					if (!map.containsKey(splittedLine[1])) {
						/* add empty list with first element */
						ArrayList<Object> list = new ArrayList<Object>();
						list.add(wifi);
						map.put(splittedLine[3], list);
					} else {
						/* add element to existing list */
						map.get(splittedLine[3]).add(wifi);
					}
					break;
				case GPS:
					/* split line */
					splittedLine = new String[5];
					splittedLine = line.split("\\t");
					/* parse date */
					date = sf.parse(splittedLine[4]);
					/* create bean */
					Gps gps = new Gps(Integer.valueOf(splittedLine[0]),
							splittedLine[1], splittedLine[2], splittedLine[3],
							date);
					/* check if key exists */
					if (!map.containsKey(splittedLine[1])) {
						/* add empty list with first element */
						ArrayList<Object> list = new ArrayList<Object>();
						list.add(gps);
						map.put(splittedLine[3], list);
					} else {
						/* add element to existing list */
						map.get(splittedLine[3]).add(gps);
					}
					break;
				default:
					throw new Exception("Wrong FileType given.");
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			/* close reader if exists */
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		/* close stream */
		stream.close();
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

		/* populate wifiMap */
		readWifis();
		for (String key : wifiMap.keySet()) {
			double totalweight = 0.0, latitudeSum = 0.0, longtitudeSum = 0.0, latitude, longtitude;
			double rssi;
			double weight = 0;

			ArrayList<Object> list = wifiMap.get(key);
			/* if only one record skip */
			if (list.size() < 2) {
				Wifi wifi = (Wifi) list.get(0);
				wifiLocations.put(key, wifi.getLocation());
				continue;
			}

			for (Object wifiObj : list) {
				Wifi wifi = (Wifi) wifiObj;
				latitude = wifi.getLocation().getLatitude();
				longtitude = wifi.getLocation().getLongtitude();
				/* if no location skip */
				if (latitude == -1.0f && longtitude == -1.0f) {
					System.out.println("No location for this ap.");
					continue;
				}
				rssi = wifi.getLevel();

				/* Convert latitude and longtitude from degrees to radians. */
				latitude = latitude * PI / 180;
				longtitude = longtitude * PI / 180;

				/* Convert dBm to mW. */
				weight = Math.pow(10, rssi / 10);
				totalweight += weight;

				/* Calculate latitude*weight and longtitude*weight */
				latitudeSum += latitude * weight;
				longtitudeSum += longtitude * weight;
			}
			/* Calculate the weighted latitude and longtitude. */
			latitude = latitudeSum / totalweight;
			longtitude = longtitudeSum / totalweight;

			/* add computed location to map */
			wifiLocations.put(
					key,
					new Location(String.valueOf(latitude / (PI / 180)), String
							.valueOf(longtitude / (PI / 180))));
		}
	}
}
