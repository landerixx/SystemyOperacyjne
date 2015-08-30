package ZeZbioremRoboczym;

import java.io.IOException;
import java.util.ArrayList;

public class CopyOfTestLokalnoscPromien {

	
	public static void main ( String[] args) throws IOException, ClassNotFoundException{
		
		int iloscProcesow=3;
		int minIloscOdwolan=5;
		int maxIloscOdwolan=1000;
		int iloscRamek=31;
		int PromienLokalnosci=10;
		int deltaZbioruRoboczego=10;
		System.out.println("lokalnosc z promieniem lokalnosci procesow");
		
		
		
		String nazwaPliku="reprezentacjaProcPromien";
		/*
		ArrayList<Proces> procesy= ReprezentacjaProcesow.generujRandCiagProcesowR
				(iloscProcesow, minIloscOdwolan, maxIloscOdwolan, PromienLokalnosci);
		
		ReprezentacjaProcesow.save(nazwaPliku, procesy);
	*/
		
		ArrayList<Proces> procesy=ReprezentacjaProcesow.restore(nazwaPliku);
		
		
		
		Algorithms instance = new Algorithms(procesy, iloscRamek,deltaZbioruRoboczego);
	
		
		
		
		
	//	instance.wyswietlProcesy();
		
		
		System.out.println();
		System.out.println("Parallel equal allocation!");
		instance.parallelEqualAllocation();
		
	

		System.out.println();
		System.out.println("equal allocation");
		instance.equalAllocation();
	
		
		
		
		
		
		
		System.out.println();
		System.out.println("Controllin the frequency!");
		instance.controllingTheFrequency();
	
	/*
		System.out.println();
		System.out.println("Model strefowy");
		instance.modelStrefowy();
		*/
		
		
		System.out.println();
		System.out.println("Zbior roboczy");
		instance.modelStrefowy();
		
		System.out.println();
		System.out.println("Parallel proportional allocation!");
		instance.parallelProportionalAllocation();
		
		
		System.out.println("proportional allocation!");
		instance.proportionalAllocation();
		
		
	
		
	}
	
}
