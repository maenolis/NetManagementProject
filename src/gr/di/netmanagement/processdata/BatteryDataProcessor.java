package gr.di.netmanagement.processdata;

import gr.di.netmanagement.beans.Battery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * The Class BatteryDataProcessor.
 */
public class BatteryDataProcessor {

	/**
	 * Gets the low levels for batteries under 15%.
	 *
	 * @param batteryMap
	 *            the battery map
	 * @return the low levels
	 */
	public static HashMap<String, Float> getLowLevels(
			final HashMap<String, ArrayList<Object>> batteryMap) {

		HashMap<String, Float> dateMap = new HashMap<String, Float>();
		HashMap<String, HashSet<String>> dateUserMap = new HashMap<String, HashSet<String>>();

		for (ArrayList<Object> batteries : batteryMap.values()) {
			for (Object battery : batteries) {
				if (!dateMap.containsKey((((Battery) battery).toShortString()))) {
					dateMap.put((((Battery) battery).toShortString()), 0.0f);
					dateUserMap.put((((Battery) battery).toShortString()),
							new HashSet<String>());
				}
				if (((Battery) battery).getLevel() <= 15) {
					if (!dateUserMap.get((((Battery) battery).toShortString()))
							.contains(((Battery) battery).getUser())) {
						dateUserMap.get((((Battery) battery).toShortString()))
								.add(((Battery) battery).getUser());
						dateMap.put((((Battery) battery).toShortString()),
								dateMap.get((((Battery) battery)
										.toShortString())) + 1.0f);
					}
				}
			}

		}
		return dateMap;
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
	public static Float[] convertToPercentages(
			final HashMap<String, Float> dateMap, final int size) {

		Set<String> set = dateMap.keySet();
		for (String key : set) {
			dateMap.put(key, dateMap.get(key) / size * 100.0f);
		}
		System.out.println("dateMap after " + dateMap);
		Float[] newArray = new Float[dateMap.values().size()];
		int i = 0;
		for (Object obj : dateMap.values()) {
			/* round up to 2 decimal digits */
			newArray[i++] = round(((Float) obj), 2).floatValue();
		}
		System.out.println("newArray = " + newArray);
		for (Float f : newArray) {
			System.out.println(f);
		}
		return newArray;
	}

	/**
	 * Prepares a String array for javascript.
	 *
	 * @param items
	 *            the items
	 * @return the string array string
	 */
	public static String getStringArrayString(final Object[] items) {

		String result = "[";
		for (int i = 0; i < items.length; i++) {
			String itemI = (String) items[i];
			result += "\"" + itemI + "\"";
			if (i < items.length - 1) {
				result += ", ";
			}
		}
		result += "]";

		return result;
	}

	public static BigDecimal round(final float d, final int decimalPlace) {

		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd;
	}
}
