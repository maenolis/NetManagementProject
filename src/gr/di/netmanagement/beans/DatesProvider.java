package gr.di.netmanagement.beans;

import java.util.ArrayList;

/**
 * The Class DatesProvider for servlets' forms.
 */
public class DatesProvider {

	/** The days. */
	private static ArrayList<String> days;

	/** The months. */
	private static ArrayList<String> months;

	/** The hours. */
	private static ArrayList<String> hours;

	/** The minutes. */
	private static ArrayList<String> minutes;

	public static ArrayList<String> getDays() {

		days = new ArrayList<String>();

		for (int i = 0; i < 31; i++) {
			days.add(String.valueOf(i + 1));
		}

		return days;
	}

	public static ArrayList<String> getMonths() {

		months = new ArrayList<String>();

		for (int i = 0; i < 12; i++) {
			months.add(String.valueOf(i + 1));
		}
		return months;
	}

	public static ArrayList<String> getHours() {

		hours = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			hours.add(String.valueOf(i));
		}

		return hours;
	}

	public static ArrayList<String> getMinutes() {

		minutes = new ArrayList<String>();

		for (int i = 0; i < 60; i++) {
			minutes.add(String.valueOf(i));
		}
		return minutes;
	}
}
