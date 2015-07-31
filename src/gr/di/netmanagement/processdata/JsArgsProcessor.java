package gr.di.netmanagement.processdata;

import com.orsoncharts.util.json.JSONArray;

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
			jsonArray.add(obj);
		}
		return jsonArray.toJSONString();
	}

}
