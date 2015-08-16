package gr.di.netmanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NetManagementProj {

	public static void main(final String[] args) {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		try {
			Date date = sf.parse("2013-12-30 23:18:33");
			cal.setTime(date);
			System.out.println("2013-12-30 23:18:33");
			System.out.println("day = " + cal.get(Calendar.DAY_OF_MONTH));
			System.out.println("month = " + cal.get(Calendar.MONTH));
			System.out.println("year = " + cal.get(Calendar.YEAR));

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
