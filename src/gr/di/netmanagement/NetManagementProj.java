package gr.di.netmanagement;

import java.io.File;

public class NetManagementProj {

	public static void main(String[] args) {

		DataReader dr = new DataReader();
		try {
			dr.readFile(new File(""), FileType.GPS);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
