package gr.di.netmanagement.processdata;

import gr.di.netmanagement.beans.BaseStation;
import gr.di.netmanagement.beans.Bean;
import gr.di.netmanagement.beans.Location;
import gr.di.netmanagement.beans.Wifi;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;

public class DataAnalysis {

	public static ArrayList<Location> analizeLocations(final Date from,
			final Date to, final String user,
			final DataProcessor dataProcessor, final float dMin,
			final float dMax, final int tMax) {

		ArrayList<Object> currentList = discardFromDate(from, to, user,
				dataProcessor);

		currentList = discardFromTime(currentList, tMax);

		return null;
	}

	private static ArrayList<Object> discardFromDate(final Date from,
			final Date to, final String user, final DataProcessor dataProcessor) {

		if (from.after(to)) {
			throw new IllegalArgumentException(
					"Dates not declare a valid range!");
		}
		/* List to return. */
		ArrayList<Object> retList = new ArrayList<Object>();

		/* Get wifi and baseStation lists for given user. */
		ArrayList<Object> wifiList = dataProcessor.getWifiMap().get(user);
		ArrayList<Object> baseStationList = dataProcessor.getBaseStationMap()
				.get(user);
		/* Gather wifi locations. */
		for (Object wifiObj : wifiList) {
			Wifi wifi = (Wifi) wifiObj;
			if (wifi.getTimestamp().after(from)
					&& wifi.getTimestamp().before(to)) {
				retList.add(wifi);
			}
		}
		/* Gather base station locations. */
		for (Object baseStationObj : baseStationList) {
			BaseStation baseStation = (BaseStation) baseStationObj;
			if (baseStation.getTimestamp().after(from)
					&& baseStation.getTimestamp().before(to)) {
				retList.add(baseStation);
			}
		}
		return retList;
	}

	public static ArrayList<Object> discardFromTime(
			final ArrayList<Object> list, final int tMax) {

		/* Lists with less than 3 beans check. */
		if (list.size() == 1) {
			return list;
		} else if (list.size() == 2) {
			Bean bean1 = (Bean) list.get(0);
			Bean bean2 = (Bean) list.get(1);
			if (timeDistance(bean1.getTimestamp(), bean2.getTimestamp(), tMax)) {
				return list;
			} else {
				return null;
			}
		}

		TreeMap<Date, Object> map = new TreeMap<Date, Object>();
		/* Sort beans by date. */
		for (Object obj : list) {
			Bean bean = (Bean) obj;
			map.put(bean.getTimestamp(), bean);
		}

		/* Check beans for time distance. */
		Object[] dates = map.keySet().toArray();
		TreeSet<Date> datesSet = new TreeSet<Date>();

		for (int i = 1; i < dates.length - 1; i++) {
			if (timeDistance((Date) dates[i - 1], (Date) dates[i], tMax)) {
				datesSet.add((Date) dates[i - 1]);
				datesSet.add((Date) dates[i]);
			}
			if (timeDistance((Date) dates[i], (Date) dates[i + 1], tMax)) {
				datesSet.add((Date) dates[i]);
				datesSet.add((Date) dates[i + 1]);
			}
		}

		/* Add beans to cleared list. */
		list.clear();
		for (Date date : datesSet) {
			list.add(map.get(date));
		}
		return list;
	}

	/* Check if time distance between 2 dates is less than tMax minutes. */
	private static boolean timeDistance(final Date date1, final Date date2,
			final int tMax) {

		long diffInMilliseconds = date1.getTime() - date2.getTime();
		return (Math.abs(diffInMilliseconds) < tMax * 60 * 1000);
	}

	private static double distanceInMeters(final double lat1,
			final double lon1, final double lat2, final double lon2) {

		/* Radius of the earth. */
		final int R = 6371;

		Double latDistance = Math.toRadians(lat2 - lat1);
		Double lonDistance = Math.toRadians(lon2 - lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2)
				* Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		/* Convert to meters. */
		double distance = R * c * 1000;

		distance = Math.pow(distance, 2);

		return Math.sqrt(distance);
	}
}
