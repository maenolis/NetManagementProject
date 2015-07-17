package gr.di.netmanagement;

import gr.di.netmanagement.processdata.ProcessData;

public class NetManagementProj {

	public static void main(String[] args) {

		try {
			ProcessData a = new ProcessData();
			a.computeAccessPointsLocation();
			System.out.println(a.getUsersSet());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
