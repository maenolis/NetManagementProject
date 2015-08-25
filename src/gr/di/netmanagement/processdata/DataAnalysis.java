package gr.di.netmanagement.processdata;

import gr.di.netmanagement.beans.Bean;
import gr.di.netmanagement.beans.CLusteredPointOfInterest;
import gr.di.netmanagement.beans.Location;
import gr.di.netmanagement.beans.LocationBean;
import gr.di.netmanagement.beans.PointOfInterest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;

/**
 * The Class DataAnalysis.
 */
public class DataAnalysis {

	/**
	 * Instantiates a new data analysis.
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
	 * @param tMin
	 *            the t min
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
			final float dMax, final int tMax) {

		ArrayList<Object> list = discardFromDateRange(from, to, user,
				dataProcessor);

		ArrayList<PointOfInterest> retList = new ArrayList<PointOfInterest>();

		int i = 0;
		int j = 0;
		long t;
		double distance;
		final int numOfLocations = list.size();
		LocationBean bean1;
		LocationBean bean2;
		ArrayList<LocationBean> tmpList = new ArrayList<LocationBean>();

		while (i < numOfLocations - 1) {
			System.out.println("i = " + i + " j = " + j + "num = "
					+ numOfLocations);
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
							if (!((LocationBean) list.get(k)).getLocation()
									.isEmpty()) {
								tmpList.add((LocationBean) list.get(k));
							}
						}
						PointOfInterest poi = new PointOfInterest(
								calculateCentroid(tmpList),
								((LocationBean) list.get(i)).getTimestamp(),
								((LocationBean) list.get(j - 1)).getTimestamp());
						tmpList.clear();
						retList.add(poi);
					}
					i = j;
					break;
				}
				j = j + 1;
			}
			if (j == numOfLocations) {
				break;
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
		final ArrayList<Object> wifiList = dataProcessor.getWifiPerUserMap()
				.get(user);
		System.out.println(wifiList);
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
	 * Check if time distance between 2 dates is less than tMax minutes.
	 *
	 * @param date1
	 *            the date1
	 * @param date2
	 *            the date2
	 * @return true, if successful
	 */
	private static long timeDistance(final Date date1, final Date date2) {

		final long diffInMilliseconds = date1.getTime() - date2.getTime();
		return Math.abs(diffInMilliseconds) / (60 * 1000);
	}

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
	 * @return the double
	 */
	private static double distanceInMeters(final Location location1,
			final Location location2) {

		final SpaceDistance spaceDistance = new SpaceDistance();
		return spaceDistance
				.compute(location1.getPoint(), location2.getPoint());
	}

	public static ArrayList<CLusteredPointOfInterest> clusteredPointsOfInterest(
			final Date from, final Date to, final DataProcessor dataProcessor,
			final float tMin, final float dMax, final int tMax,
			final double eps, final int minPts) {

		final ArrayList<CLusteredPointOfInterest> retList = new ArrayList<CLusteredPointOfInterest>();
		final ArrayList<Location> locationList = new ArrayList<Location>();

		final Set<String> users = dataProcessor.getUsersSet();

		for (String user : users) {
			final ArrayList<PointOfInterest> tmpList = analizeLocations(from,
					to, user, dataProcessor, tMin, dMax, tMax);
			for (PointOfInterest poi : tmpList) {
				locationList.add(poi.getLocation());
			}
		}

		final DBSCANClusterer<Location> clusterer = new DBSCANClusterer<Location>(
				eps, minPts, new SpaceDistance());

		final List<Cluster<Location>> clusteredList = clusterer
				.cluster(locationList);

		return analizeClusters(retList, clusteredList);

	}

	private static ArrayList<CLusteredPointOfInterest> analizeClusters(
			final ArrayList<CLusteredPointOfInterest> retList,
			final List<Cluster<Location>> clusteredList) {

		// TODO: analizeClusters
		// median points + bounds

		return retList;
	}
}
