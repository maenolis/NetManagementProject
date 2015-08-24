package gr.di.netmanagement.processdata;

import gr.di.netmanagement.beans.Bean;
import gr.di.netmanagement.beans.Location;
import gr.di.netmanagement.beans.LocationBean;
import gr.di.netmanagement.beans.PointOfInterest;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * The Class DataAnalysis.
 */
public class DataAnalysis {

	/**
	 * Instantiates a new data analysis.
	 *
	 * @param numOfStayPoints
	 *            the num of stay points
	 */
	public DataAnalysis() {

	}

	/**
	 * Analize locations.
	 *
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @param user
	 *            the user
	 * @param dataProcessor
	 *            the data processor
	 * @param dMin
	 *            the d min
	 * @param dMax
	 *            the d max
	 * @param tMax
	 *            the t max
	 * @param maxMeters
	 *            the max meters
	 * @return the array list
	 */
	public static ArrayList<PointOfInterest> analizeLocations(final Date from,
			final Date to, final String user,
			final DataProcessor dataProcessor, final float tMin,
			final float dMax, final int tMax, final int maxMeters) {

		ArrayList<Object> list = discardFromDateRange(from, to, user,
				dataProcessor);

		list = discardFromTimeAndSpaceDistance(list, tMax, maxMeters);

		ArrayList<PointOfInterest> retList = new ArrayList<PointOfInterest>();

		int i = 0;
		int j;
		long t;
		double distance;
		final int numOfLocations = list.size();
		LocationBean bean1;
		LocationBean bean2;
		ArrayList<LocationBean> tmpList = new ArrayList<LocationBean>();

		while (i < numOfLocations) {
			j = i + 1;
			while (j < numOfLocations) {
				bean1 = (LocationBean) list.get(j);
				bean2 = (LocationBean) list.get(j - 1);
				t = timeDistance(bean1.getTimestamp(), bean2.getTimestamp());
				if (t > tMax) {
					i = j;
					break;
				}
				distance = distanceInMeters(bean1.getLocation(),
						bean2.getLocation());
				if (distance > dMax) {
					bean1 = (LocationBean) list.get(i);
					bean2 = (LocationBean) list.get(j - 1);
					t = timeDistance(bean1.getTimestamp(), bean2.getTimestamp());
					if (t > tMin) {
						for (int k = i; k < j; k++) {
							tmpList.add((LocationBean) list.get(k));
						}
						PointOfInterest poi = new PointOfInterest(
								calculateCentroid(tmpList),
								((LocationBean) list.get(i)).getTimestamp(),
								((LocationBean) list.get(j - 1)).getTimestamp());
						retList.add(poi);
					}
					i = j;
					break;
				}
				j = j + 1;
			}
		}

		return retList;
	}

	/**
	 * Discard from date range.
	 *
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @param user
	 *            the user
	 * @param dataProcessor
	 *            the data processor
	 * @return the array list
	 */
	private static ArrayList<Object> discardFromDateRange(final Date from,
			final Date to, final String user, final DataProcessor dataProcessor) {

		if (from.after(to)) {
			throw new IllegalArgumentException(
					"Dates do not declare a valid range!");
		}
		/* List to return. */
		final ArrayList<Object> retList = new ArrayList<Object>();

		/* Get wifi and baseStation lists for given user. */
		final ArrayList<Object> wifiList = dataProcessor.getWifiMap().get(user);
		final ArrayList<Object> baseStationList = dataProcessor
				.getBaseStationMap().get(user);
		/* Gather wifi locations. */
		for (final Object wifiObj : wifiList) {
			final Bean wifi = (Bean) wifiObj;
			if (wifi.getTimestamp().after(from)
					&& wifi.getTimestamp().before(to)) {
				retList.add(wifi);
			}
		}
		/* Gather base station locations. */
		for (final Object baseStationObj : baseStationList) {
			final Bean baseStation = (Bean) baseStationObj;
			if (baseStation.getTimestamp().after(from)
					&& baseStation.getTimestamp().before(to)) {
				retList.add(baseStation);
			}
		}
		return retList;
	}

	/**
	 * Discard from time distance.
	 *
	 * @param list
	 *            the list
	 * @param tMax
	 *            the t max
	 * @param maxMeters
	 *            the max meters
	 * @return the array list
	 */
	public static ArrayList<Object> discardFromTimeAndSpaceDistance(
			final ArrayList<Object> list, final int tMax, final int maxMeters) {

		/* Lists with less than 3 beans check. */
		if (list.size() == 1) {
			return list;
		} else if (list.size() == 2) {
			final Bean bean1 = (Bean) list.get(0);
			final Bean bean2 = (Bean) list.get(1);
			if (timeDistanceBoolean(bean1.getTimestamp(), bean2.getTimestamp(),
					tMax)) {
				return list;
			} else {
				return null;
			}
		}

		final TreeMap<Date, LocationBean> map = new TreeMap<Date, LocationBean>();
		/* Sort beans by date. */
		for (final Object obj : list) {
			final LocationBean bean = (LocationBean) obj;
			map.put(bean.getTimestamp(), bean);
		}

		/* Check beans for time distance. */
		final Object[] dates = map.keySet().toArray();
		final TreeSet<Date> datesSet = new TreeSet<Date>();
		final TreeSet<Date> emptyDatesSet = new TreeSet<Date>();

		for (int i = 1; i < dates.length; i++) {
			final Date date1 = (Date) dates[i - 1];
			final Date date2 = (Date) dates[i];
			final Date date3 = (Date) dates[i + 1];
			final int previousSize = datesSet.size();
			if (timeDistanceBoolean(date1, date2, tMax)
					&& distanceInMetersBoolean(map.get(date1).getLocation(),
							map.get(date2).getLocation(), maxMeters)) {
				datesSet.add(date1);
				datesSet.add(date2);
			}
			if (timeDistanceBoolean(date2, date3, tMax)
					&& distanceInMetersBoolean(map.get(date2).getLocation(),
							map.get(date3).getLocation(), maxMeters)) {
				datesSet.add(date2);
				datesSet.add(date3);
			}
			if (previousSize == datesSet.size()) {
				emptyDatesSet.add(date2);
			}
		}

		for (final Date date : emptyDatesSet) {
			datesSet.add(date);
		}

		/* Add beans to cleared list. */
		list.clear();
		for (final Date date : datesSet) {

			if (!emptyDatesSet.contains(date)) {
				list.add(map.get(date));
			} else if (list.size() > 0 && (list.get(list.size() - 1) != null)) {
				list.add(null);
			}
		}
		return list;
	}

	/**
	 * Check if time distance between 2 dates is less than tMax minutes.
	 *
	 * @param date1
	 *            the date1
	 * @param date2
	 *            the date2
	 * @param tMax
	 *            the t max
	 * @return true, if successful
	 */
	private static long timeDistance(final Date date1, final Date date2) {

		final long diffInMilliseconds = date1.getTime() - date2.getTime();
		return Math.abs(diffInMilliseconds);
	}

	private static boolean timeDistanceBoolean(final Date date1,
			final Date date2, final int tMax) {

		final long diffInMilliseconds = date1.getTime() - date2.getTime();
		return (Math.abs(diffInMilliseconds) < 60 * 1000 * tMax);
	}

	// /**
	// * Calculate centroids.
	// *
	// * @param list
	// * the list
	// * @return the array list of centroids' locations
	// */
	// private static ArrayList<Location> calculateCentroids(
	// final ArrayList<Object> list) {
	//
	// final ArrayList<LocationBean> currentList = new
	// ArrayList<LocationBean>();
	// final ArrayList<Location> centroids = new ArrayList<Location>();
	// for (int i = 0; i < list.size(); i++) {
	// if (list.get(i) != null) {
	// final LocationBean locationBean = (LocationBean) list.get(i);
	// currentList.add(locationBean);
	// } else {
	// if (currentList.size() > 0) {
	// centroids.add(calculateCentroid(currentList));
	// currentList.clear();
	// }
	// }
	// }
	// return centroids;
	// }

	/**
	 * Calculate centroid.
	 *
	 * @param list
	 *            the list
	 * @return the list's centroid location
	 */
	private static Location calculateCentroid(final ArrayList<LocationBean> list) {

		double lons = 0.0;
		double lats = 0.0;
		for (final LocationBean locationBean : list) {
			lons += locationBean.getLocation().getLongtitude();
			lats += locationBean.getLocation().getLatitude();
		}
		return new Location(String.valueOf(lats / list.size()),
				String.valueOf(lons / list.size()));
	}

	/**
	 * Distance in meters.
	 *
	 * @param location1
	 *            the location1
	 * @param location2
	 *            the location2
	 * @param metersLimit
	 *            the meters limit
	 * @return true, if successful
	 */
	private static boolean distanceInMetersBoolean(final Location location1,
			final Location location2, final int metersLimit) {

		final double lat1 = location1.getLatitude();
		final double lon1 = location1.getLongtitude();
		final double lat2 = location2.getLatitude();
		final double lon2 = location2.getLongtitude();

		/* Radius of the earth. */
		final int R = 6371;

		final Double latDistance = Math.toRadians(lat2 - lat1);
		final Double lonDistance = Math.toRadians(lon2 - lon1);
		final Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2)
				* Math.sin(lonDistance / 2);
		final Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		/* Convert to meters. */
		double distance = R * c * 1000;

		distance = Math.pow(distance, 2);

		return Math.sqrt(distance) < metersLimit;
	}

	private static double distanceInMeters(final Location location1,
			final Location location2) {

		final double lat1 = location1.getLatitude();
		final double lon1 = location1.getLongtitude();
		final double lat2 = location2.getLatitude();
		final double lon2 = location2.getLongtitude();

		/* Radius of the earth. */
		final int R = 6371;

		final Double latDistance = Math.toRadians(lat2 - lat1);
		final Double lonDistance = Math.toRadians(lon2 - lon1);
		final Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2)
				* Math.sin(lonDistance / 2);
		final Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		/* Convert to meters. */
		double distance = R * c * 1000;

		distance = Math.pow(distance, 2);

		return Math.sqrt(distance);
	}
}
