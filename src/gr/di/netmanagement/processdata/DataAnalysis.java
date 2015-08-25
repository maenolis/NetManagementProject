package gr.di.netmanagement.processdata;

import gr.di.netmanagement.beans.Bean;
import gr.di.netmanagement.beans.ClusteredPointOfInterest;
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
	 * @return the array list
	 */
	public static ArrayList<PointOfInterest> analyzeLocations(final Date from,
			final Date to, final String user,
			final DataProcessor dataProcessor, final float tMin,
			final float dMax, final int tMax) {

		/* Discard entries from date range. */
		ArrayList<Object> list = discardFromDateRange(from, to, user,
				dataProcessor);

		ArrayList<PointOfInterest> retList = new ArrayList<PointOfInterest>();

		/* The following section implements the algorithm given. */
		int i = 0;
		int j = 0;
		long t;
		double distance;
		final int numOfLocations = list.size();
		LocationBean bean1;
		LocationBean bean2;
		ArrayList<LocationBean> tmpList = new ArrayList<LocationBean>();

		while (i < numOfLocations - 1) {
			j = i + 1;
			while (j < numOfLocations) {

				/* Current beans. */
				bean1 = (LocationBean) list.get(j);
				bean2 = (LocationBean) list.get(j - 1);
				t = timeDistance(bean1.getTimestamp(), bean2.getTimestamp());

				if (t > tMax) {
					/* If time distance exceeds threshold skip. */
					i = j;
					break;
				}

				distance = distanceInMeters(bean1.getLocation(),
						bean2.getLocation());
				if (distance > dMax) {
					/*
					 * If distance exceeds threshold compute current point of
					 * interest.
					 */
					bean1 = (LocationBean) list.get(i);
					bean2 = (LocationBean) list.get(j - 1);
					t = timeDistance(bean1.getTimestamp(), bean2.getTimestamp());
					if (t > tMin) {
						/* If time in stay point exceeds the minimum threshold. */
						for (int k = i; k < j; k++) {
							if (!((LocationBean) list.get(k)).getLocation()
									.isEmpty()) {
								tmpList.add((LocationBean) list.get(k));
							}
						}

						/* Calculated point of interest. */
						PointOfInterest poi = new PointOfInterest(
								calculateCentroid(tmpList),
								((LocationBean) list.get(i)).getTimestamp(),
								((LocationBean) list.get(j - 1)).getTimestamp());

						/* Temporary list emptying. */
						tmpList.clear();
						retList.add(poi);
					}
					i = j;
					break;
				}
				j = j + 1;
			}
			if (j == numOfLocations) {
				/* End of calculations. */
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

		/* Return new location with median points. */
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

	/**
	 * Clustered points of interest.
	 *
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @param dataProcessor
	 *            the data processor
	 * @param tMin
	 *            the t min
	 * @param dMax
	 *            the d max
	 * @param tMax
	 *            the t max
	 * @param eps
	 *            the eps
	 * @param minPts
	 *            the min pts
	 * @return the array list
	 */
	public static ArrayList<ClusteredPointOfInterest> clusteredPointsOfInterest(
			final Date from, final Date to, final DataProcessor dataProcessor,
			final float tMin, final float dMax, final int tMax,
			final double eps, final int minPts) {

		/* Second level clustering locations. */
		final ArrayList<ClusteredPointOfInterest> retList = new ArrayList<ClusteredPointOfInterest>();

		/* First level clustering locations. */
		final ArrayList<Location> locationList = new ArrayList<Location>();

		final Set<String> users = dataProcessor.getUsersSet();

		/* Collect data for all users. */
		for (String user : users) {
			final ArrayList<PointOfInterest> tmpList = analyzeLocations(from,
					to, user, dataProcessor, tMin, dMax, tMax);
			for (PointOfInterest poi : tmpList) {
				locationList.add(poi.getLocation());
			}
		}

		/* Clusterer with space distance. */
		final DBSCANClusterer<Location> clusterer = new DBSCANClusterer<Location>(
				eps, minPts, new SpaceDistance());

		final List<Cluster<Location>> clusteredList = clusterer
				.cluster(locationList);

		/* Return analyzed cluster list. */
		return analyzeClusters(retList, clusteredList);

	}

	/**
	 * Analize clusters.
	 *
	 * @param retList
	 *            the ret list
	 * @param clusteredList
	 *            the clustered list
	 * @return the array list
	 */
	private static ArrayList<ClusteredPointOfInterest> analyzeClusters(
			final ArrayList<ClusteredPointOfInterest> retList,
			final List<Cluster<Location>> clusteredList) {

		for (Cluster<Location> cluster : clusteredList) {

			/* Analyze each cluster individually. */
			ClusteredPointOfInterest cPoi = analyzeCluster(cluster);
			retList.add(cPoi);
		}

		return retList;
	}

	/**
	 * Analize cluster.
	 *
	 * @param cluster
	 *            the cluster
	 * @return the clustered point of interest
	 */
	private static ClusteredPointOfInterest analyzeCluster(
			final Cluster<Location> cluster) {

		/* Cluster's points. */
		List<Location> locations = cluster.getPoints();
		if (locations.size() == 0) {
			System.out.println("Empty cluster.");
			return null;
		}

		/* Median points. */
		double midLat = 0.0;
		double midLon = 0.0;

		/* Cluster's bounds. */
		double west = locations.get(0).getLatitude();
		double east = locations.get(0).getLatitude();
		double north = locations.get(0).getLongtitude();
		double south = locations.get(0).getLongtitude();

		for (Location location : locations) {
			midLat += location.getLatitude();
			midLon += location.getLongtitude();
			if (west > location.getLongtitude()) {
				west = location.getLongtitude();
			}
			if (east < location.getLongtitude()) {
				east = location.getLongtitude();
			}
			if (north < location.getLatitude()) {
				north = location.getLatitude();
			}
			if (south > location.getLatitude()) {
				south = location.getLatitude();
			}
		}

		/* Compute and create the ClusteredPointOfInterest. */
		midLat = midLat / locations.size();
		midLon = midLon / locations.size();
		return new ClusteredPointOfInterest(new Location(
				String.valueOf(midLat), String.valueOf(midLon)), new Location(
				String.valueOf(north), String.valueOf(west)), new Location(
				String.valueOf(south), String.valueOf(east)));
	}
}
