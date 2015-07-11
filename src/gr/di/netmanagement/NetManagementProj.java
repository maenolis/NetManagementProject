package gr.di.netmanagement;

public class NetManagementProj {

	public static void main(String[] args) {

		try {
			EditData a = new EditData();
			a.computeAccessPointsLocation();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
