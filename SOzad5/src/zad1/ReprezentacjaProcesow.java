package zad1;

import java.util.ArrayList;

public class ReprezentacjaProcesow {

	
	
	
	
	public static ArrayList<Proces> generujProcesy(int ileSztuk){
		

		ArrayList<Proces> procesy= new ArrayList<Proces>();
		
		for(int i=1; i<=ileSztuk;i++)
			procesy.add(new Proces());
		
		return procesy;
		
	}
	
	
	
	
}
