package gr.di.netmanagement;

import gr.di.netmanagement.processdata.DataReader;

public class NetManagementProj {

	public static void main(String[] args) {

		try {
			DataReader dataReader = new DataReader();
			dataReader.computeAccessPointsLocation();
			System.out.println(dataReader.getUsersSet().size());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
