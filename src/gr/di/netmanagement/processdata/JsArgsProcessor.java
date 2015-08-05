package gr.di.netmanagement.processdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TreeMap;

import org.json.JSONArray;

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

}
