package gr.di.netmanagement;

import java.util.ArrayList;

import gr.di.netmanagement.beans.Battery;
import gr.di.netmanagement.processdata.DataProcessor;

public class NetManagementProj {

	public static void main(final String[] args) {

		try {
			DataProcessor dataProcessor = new DataProcessor();
			for (ArrayList<Object> batteries : dataProcessor.getBatteryMap().values()) {
				for (Object battery : batteries) {
					System.out.println(((Battery)battery).toShortString());
				}
				
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
