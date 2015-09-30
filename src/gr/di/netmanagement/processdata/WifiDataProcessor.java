package gr.di.netmanagement.processdata;

import gr.di.netmanagement.beans.Wifi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class WifiDataProcessor.
 */
public class WifiDataProcessor {

	/**
	 * Gets the user ap stickers.
	 *
	 * @param dataProcessor
	 *            the data processor
	 * @param user
	 *            the user
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @return the user ap stickers
	 */
	public static List<Wifi> getUserApStickers(
			final DataProcessor dataProcessor, final String user,
			final Date from, final Date to) {
		List<Wifi> retList = new ArrayList<Wifi>();

		for (String key : dataProcessor.getWifiMap().keySet()) {
			int counter = 0;
			int midLevel = 0;
			Wifi tmpWifi = null;
			if (dataProcessor.getWifiMap().get(key) == null) {
				continue;
			}
			for (Object obj : dataProcessor.getWifiMap().get(key)) {
				if (!((Wifi) obj).getUser().equals(user)
						|| ((Wifi) obj).getTimestamp().after(to)
						|| ((Wifi) obj).getTimestamp().before(from)) {
					continue;
				}
				tmpWifi = (Wifi) obj;
				midLevel += tmpWifi.getLevel();
				counter++;
			}
			if (tmpWifi != null) {
				midLevel = midLevel / counter;
				Wifi wifi = new Wifi(tmpWifi);
				wifi.setLevel(midLevel);
				retList.add(wifi);
			}
		}
		return retList;
	}

}
