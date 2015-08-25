package gr.di.netmanagement.processdata;

import gr.di.netmanagement.beans.BaseStation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

/**
 * The Class BaseStationProcessor.
 */
public class BaseStationProcessor {

	/**
	 * Companies map.
	 *
	 * @param baseStationMap
	 *            the base station map
	 * @return the tree map
	 */
	public static TreeMap<String, Integer> companiesMap(
			final HashMap<String, ArrayList<Object>> baseStationMap) {

		TreeMap<String, Integer> retMap = new TreeMap<String, Integer>();
		Set<String> keysSet = baseStationMap.keySet();
		for (String tmp : keysSet) {
			BaseStation bs = (BaseStation) baseStationMap.get(tmp).get(0);
			if (retMap.containsKey(bs.getOperator())) {
				retMap.put(bs.getOperator(), retMap.get(bs.getOperator()) + 1);
			} else {
				retMap.put(bs.getOperator(), 1);
			}
		}

		return retMap;
	}

	/**
	 * Object array to string.
	 *
	 * @param objArray
	 *            the obj array
	 * @return the string[]
	 */
	public static String[] objectArrayToString(final Object[] objArray) {

		String[] retArray = new String[objArray.length];
		for (int i = 0; i < objArray.length; i++) {
			retArray[i] = (String) objArray[i];
		}
		return retArray;
	}

	/**
	 * Object array to int.
	 *
	 * @param objArray
	 *            the obj array
	 * @return the integer[]
	 */
	public static Integer[] objectArrayToInt(final Object[] objArray) {

		Integer[] retArray = new Integer[objArray.length];
		for (int i = 0; i < objArray.length; i++) {
			retArray[i] = (Integer) objArray[i];
		}
		return retArray;
	}

}
