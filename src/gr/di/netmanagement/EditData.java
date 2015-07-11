package gr.di.netmanagement;

import java.util.ArrayList;

public class EditData {
	
	private ArrayList<Object> newList = new ArrayList<Object>();
	
	EditData(){
		
		try{
			DataReader dr = new DataReader();
			ArrayList<Object> APlist;
			APlist = dr.readFile(FileType.WIFI);
			Wifi ap; 
			for(int i=0;i!=APlist.size();i++){
				ap= (Wifi) APlist.get(i);
				System.out.println(ap.getBssid());
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	

}


