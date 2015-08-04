package gr.di.netmanagement.processdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TreeMap;

import org.json.JSONArray;

public class JsArgsProcessor {

	/**
	 * Prepares a String array for javascript.
	 *
	 * @param items
	 *            the items
	 * @return the string array string
	 */
	@SuppressWarnings("unchecked")
	public static String getArrayString(final Object[] items) {

		JSONArray jsonArray = new JSONArray();
		for (Object obj : items) {
			jsonArray.put(obj);
		}
		return jsonArray.toString();
	}

	@SuppressWarnings("deprecation")
	public static JSONArray datesPercentagesJsArg(
			final TreeMap<String, Float> lowLevels) {

		// public static String format(GregorianCalendar calendar){
		// SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
		// fmt.setCalendar(calendar);
		// String dateFormatted = fmt.format(calendar.getTime());
		// return dateFormatted;
		// }

		JSONArray retArray = new JSONArray();
		Set<String> keys = lowLevels.keySet();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		for (String key : keys) {
			try {
				date = sf.parse(key);
				JSONArray innerArray = new JSONArray();
				JSONArray outerArray = new JSONArray();
				innerArray.put(date.getYear());
				innerArray.put(date.getMonth());
				innerArray.put(date.getDay());
				outerArray.put(innerArray);
				outerArray.put(lowLevels.get(key));
				retArray.put(outerArray);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return retArray;
	}

}
