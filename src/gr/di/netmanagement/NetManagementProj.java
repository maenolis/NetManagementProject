package gr.di.netmanagement;

import gr.di.netmanagement.processdata.DataReader;

public class NetManagementProj {

	public static void main(final String[] args) {

		try {
			DataReader dataReader = new DataReader();
			dataReader.readBaseStations();
			System.out.println(dataReader.getBaseStationMap());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
