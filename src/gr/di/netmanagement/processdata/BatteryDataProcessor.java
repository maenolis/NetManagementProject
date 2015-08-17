package gr.di.netmanagement.processdata;

import gr.di.netmanagement.beans.Battery;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.time.DateUtils;

/**
 * The Class BatteryDataProcessor.
 */
public class BatteryDataProcessor {

	/**
	 * Gets the low levels.
	 *
	 * @param batteryMap
	 *            the battery map
	 * @param size
	 *            the size
	 * @return the low levels
	 */
	public static TreeMap<String, Float> getLowLevels(
			final HashMap<String, ArrayList<Object>> batteryMap, final int size) {

		/* map: date as key, number of users found as value */
		TreeMap<String, Float> dateMap = new TreeMap<String, Float>();
		/* map: date as key, unique users as value */
		HashMap<String, HashSet<String>> dateUserMap = new HashMap<String, HashSet<String>>();

		for (ArrayList<Object> batteries : batteryMap.values()) {
			for (Object batteryObj : batteries) {
				Battery battery = (Battery) batteryObj;
				/* if date has not recorded yet */
				if (!dateMap.containsKey((battery.getTimestampShortString()))) {
					dateMap.put((battery.getTimestampShortString()), 0.0f);
					dateUserMap.put((battery.getTimestampShortString()),
							new HashSet<String>());
				}
				/* if battery was low */
				if (battery.getLevel() <= 15) {
					/* if user was not found in that date */
					if (!dateUserMap.get((battery.getTimestampShortString()))
							.contains(battery.getUser())) {
						dateUserMap.get((battery.getTimestampShortString()))
								.add(battery.getUser());
						dateMap.put(
								(battery.getTimestampShortString()),
								new Float(dateUserMap.get(
										(battery.getTimestampShortString()))
										.size()));
					}
				}
			}

		}
		return convertToPercentages(dateMap, size);
	}

	/**
	 * Convert user numbers to percentages.
	 *
	 * @param dateMap
	 *            the date map
	 * @param size
	 *            the size
	 * @return the float[]
	 */
	public static TreeMap<String, Float> convertToPercentages(
			final TreeMap<String, Float> dateMap, final int size) {

		Set<String> set = dateMap.keySet();
		for (String key : set) {
			float tmp = dateMap.get(key) / size * 100.0f;
			dateMap.put(key, round(tmp, 2).floatValue());
		}
		return dateMap;
	}

	/**
	 * Round.
	 *
	 * @param d
	 *            the d
	 * @param decimalPlace
	 *            the decimal place
	 * @return the big decimal
	 */
	public static BigDecimal round(final float d, final int decimalPlace) {

		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd;
	}

	/**
	 * Gets the user levels within dates.
	 *
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @param list
	 *            the list
	 * @return the user levels within dates
	 */
	public static TreeMap<String, Integer> getUserLevelsWithinDates(
			final Date from, final Date to, final ArrayList<Object> list) {

		if (to.before(from)) {
			return null;
		}
		TreeMap<String, Integer> retMap = new TreeMap<String, Integer>();

		Date previous = null;

		for (Object batteryObj : list) {
			Battery battery = (Battery) batteryObj;
			/* if battery is within dates given */
			if (battery.getTimestamp().after(from)
					&& battery.getTimestamp().before(to)) {
				if (previous != null
						&& !areTimesClose(battery.getTimestamp(), previous)) {
					feelGapsWithZeros(previous, battery.getTimestamp(), retMap);
				}
				retMap.put(battery.getTimeStampLongString(), battery.getLevel());
			}
			previous = battery.getTimestamp();
		}

		return retMap;

	}

	/**
	 * Are times close.
	 *
	 * @param date1
	 *            the date1
	 * @param date2
	 *            the date2
	 * @return true, if successful
	 */
	private static boolean areTimesClose(final Date date1, final Date date2) {

		return Math.abs(date1.getTime() - date2.getTime()) < 4 * 60 * 60 * 1000;
	}

	/**
	 * Feel gaps with zeros.
	 *
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @param map
	 *            the map
	 */
	private static void feelGapsWithZeros(final Date from, final Date to,
			final TreeMap<String, Integer> map) {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date iterDate = from;
		while (iterDate.before(to)) {
			map.put(sf.format(iterDate), 0);
			iterDate = DateUtils.addMinutes(iterDate, 4 * 60);
		}
	}
}
