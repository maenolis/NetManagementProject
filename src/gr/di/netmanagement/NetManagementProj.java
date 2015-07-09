package gr.di.netmanagement;

import java.io.File;

public class NetManagementProj {

	public static void main(String[] args) {

		System.out.println((new File("data/base_station.csv")).exists());
	}
}
