package gr.di.netmanagement.processdata;

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

}
