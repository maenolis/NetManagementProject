package gr.di.netmanagement.processdata;

import gr.di.netmanagement.beans.BaseStation;
import gr.di.netmanagement.beans.ClusteredPointOfInterest;
import gr.di.netmanagement.beans.PointOfInterest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The Class JsArgsProcessor.
 */
public class JsArgsProcessor {

	/**
	 * Prepares a String array for javascript.
	 *
	 * @param items
	 *            the items
	 * @return the string array string
	 */
	public static String getArrayString(final Object[] items) {

		JSONArray jsonArray = new JSONArray();
		for (Object obj : items) {
			jsonArray.put(obj);
		}
		return jsonArray.toString();
	}

	/**
	 * Low levels js arg.
	 *
	 * @param lowLevels
	 *            the low levels
	 * @return the JSON array
	 */
	public static JSONArray lowLevelsJsArg(
			final TreeMap<String, Float> lowLevels) {

		JSONArray retArray = new JSONArray();
		Set<String> keys = lowLevels.keySet();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar gc = new GregorianCalendar();
		sf.setCalendar(gc);
		for (String key : keys) {
			try {
				sf.parse(key);
				JSONArray innerArray = new JSONArray();
				JSONArray outerArray = new JSONArray();
				innerArray.put(sf.getCalendar().get(Calendar.YEAR));
				innerArray.put(sf.getCalendar().get(Calendar.MONTH));
				innerArray.put(sf.getCalendar().get(Calendar.DAY_OF_MONTH));
				outerArray.put(innerArray);
				outerArray.put(lowLevels.get(key));
				retArray.put(outerArray);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return retArray;
	}

	/**
	 * Company users js arg.
	 *
	 * @param cMap
	 *            the c map
	 * @return the JSON array
	 */
	public static JSONArray companyUsersJsArg(
			final TreeMap<String, Integer> cMap) {

		JSONArray retArray = new JSONArray();
		Set<String> keys = cMap.keySet();
		for (String key : keys) {
			JSONArray array = new JSONArray();
			array.put(key);
			array.put(cMap.get(key));
			retArray.put(array);
		}
		return retArray;
	}

	/**
	 * Battery time level js arg.
	 *
	 * @param uMap
	 *            the u map
	 * @return the JSON array
	 */
	public static JSONArray batteryTimeLevelJsArg(
			final TreeMap<String, Integer> uMap) {
		JSONArray retArray = new JSONArray();

		Set<String> keys = uMap.keySet();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		GregorianCalendar gc = new GregorianCalendar();
		sf.setCalendar(gc);
		for (String key : keys) {
			try {
				sf.parse(key);
				JSONArray array = new JSONArray();
				array.put(sf.getCalendar().get(Calendar.YEAR));
				array.put(sf.getCalendar().get(Calendar.MONTH));
				array.put(sf.getCalendar().get(Calendar.DAY_OF_MONTH));
				array.put(sf.getCalendar().get(Calendar.HOUR_OF_DAY));
				array.put(sf.getCalendar().get(Calendar.MINUTE));
				array.put(sf.getCalendar().get(Calendar.SECOND));
				array.put(uMap.get(key));
				retArray.put(array);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return retArray;
	}

	/**
	 * Cells js arg.
	 *
	 * @param baseStations
	 *            the base stations
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @return the JSON array
	 */
	public static JSONArray cellsJsArg(final ArrayList<Object> baseStations,
			final Date from, final Date to) {

		JSONArray retArray = new JSONArray();

		for (Object baseStationObj : baseStations) {
			BaseStation baseStation = (BaseStation) baseStationObj;
			if (baseStation.getTimestamp().before(from)
					|| baseStation.getTimestamp().after(to)
					|| baseStation.getLocation().getLatitude() < 0.0f
					|| baseStation.getLocation().getLongtitude() < 0.0f) {
				continue;
			}
			JSONObject jsObject = new JSONObject();
			try {

				jsObject.put("id", baseStation.getId());
				jsObject.put("cid", baseStation.getCid());
				jsObject.put("lac", baseStation.getLac());
				jsObject.put("lat", baseStation.getLocation().getLatitude());
				jsObject.put("lon", baseStation.getLocation().getLongtitude());
				jsObject.put("mcc", baseStation.getMcc());
				jsObject.put("mnc", baseStation.getMnc());
				jsObject.put("operator", baseStation.getOperator());
				jsObject.put("timestamp", baseStation.getTimestamp());
				jsObject.put("user", baseStation.getUser());
			} catch (JSONException e) {

				e.printStackTrace();
			}
			retArray.put(jsObject);
		}

		return retArray;
	}

	/**
	 * Points of interest js arg.
	 *
	 * @param stayPoints
	 *            the stay points
	 * @return the JSON array
	 */
	public static JSONArray pointsOfInterestJsArg(
			final ArrayList<PointOfInterest> stayPoints) {
		JSONArray retArray = new JSONArray();

		if (stayPoints == null || stayPoints.size() == 0) {
			return retArray;
		}

		for (PointOfInterest poi : stayPoints) {
			JSONObject poiJs = new JSONObject();
			try {
				poiJs.put("lat", poi.getLocation().getLatitude());
				poiJs.put("lon", poi.getLocation().getLongtitude());
				poiJs.put("end", poi.getEnd().toString());
				poiJs.put("start", poi.getStart().toString());
				retArray.put(poiJs);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return retArray;
	}

	/**
	 * Clustered points of interest.
	 *
	 * @param clusters
	 *            the clusters
	 * @return the JSON array
	 */
	public static JSONArray clusteredPointsOfInterest(
			final ArrayList<ClusteredPointOfInterest> clusters) {

		JSONArray retArray = new JSONArray();

		if (clusters == null || clusters.size() == 0) {
			return retArray;
		}

		for (ClusteredPointOfInterest cluster : clusters) {
			JSONObject clusterJs = new JSONObject();
			try {
				clusterJs.put("northEastLat", cluster.getNorthEastBound()
						.getLatitude());
				clusterJs.put("northEastLon", cluster.getNorthEastBound()
						.getLongtitude());
				clusterJs.put("southEastLat", cluster.getSouthEastBound()
						.getLatitude());
				clusterJs.put("southEastLon", cluster.getSouthEastBound()
						.getLongtitude());
				clusterJs.put("northWestLat", cluster.getNorthWestBound()
						.getLatitude());
				clusterJs.put("northWestLon", cluster.getNorthWestBound()
						.getLongtitude());
				clusterJs.put("southWestLat", cluster.getSouthWestBound()
						.getLatitude());
				clusterJs.put("southWestLon", cluster.getSouthWestBound()
						.getLongtitude());
				clusterJs.put("midLat", cluster.getLocation().getLatitude());
				clusterJs.put("midLon", cluster.getLocation().getLongtitude());
				retArray.put(clusterJs);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return retArray;
	}
}
