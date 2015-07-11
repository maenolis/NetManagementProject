package gr.di.netmanagement;


public class NetManagementProj {

	public static void main(String[] args) {
		
		DataReader dr = new DataReader();
		try {
			dr.readFile(FileType.BASE_STATION);
			dr.readFile(FileType.BATTERY);
			dr.readFile(FileType.GPS);
			dr.readFile(FileType.WIFI);
			EditData a=new EditData();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
