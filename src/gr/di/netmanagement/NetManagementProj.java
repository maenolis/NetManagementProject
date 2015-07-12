package gr.di.netmanagement;

public class NetManagementProj {

	public static void main(String[] args) {

		try {
			ProcessData a = new ProcessData();
			a.computeAccessPointsLocation();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
