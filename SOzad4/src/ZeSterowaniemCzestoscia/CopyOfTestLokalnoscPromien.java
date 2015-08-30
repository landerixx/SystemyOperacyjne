package ZeSterowaniemCzestoscia;

import java.util.ArrayList;

public class CopyOfTestLokalnoscPromien {

	
	public static void main ( String[] args){
		
		int iloscProcesow=5;
		int minIloscOdwolan=5;
		int maxIloscOdwolan=1000;
		int iloscRamek=31;
		int PromienLokalnosci=10;
		
		ArrayList<Proces> procesy= ReprezentacjaProcesow.generujRandCiagProcesowR
				(iloscProcesow, minIloscOdwolan, maxIloscOdwolan, PromienLokalnosci);
		System.out.println("lokalnosc z promieniem lokalnosci procesow");
	
		
		Algorithms instance = new Algorithms(procesy, iloscRamek);
	
		
		
		
		
	//	instance.wyswietlProcesy();
		
		System.out.println();
		System.out.println("Parallel equal allocation!");
		instance.parallelEqualAllocation();
		
	

		System.out.println();
		System.out.println("equal allocation");
		instance.equalAllocation();
		
		
		
		System.out.println();
		System.out.println("Parallel proportional allocation!");
		instance.parallelProportionalAllocation();
		
		System.out.println("proportional allocation!");
		instance.proportionalAllocation();
		
		
		
		System.out.println();
		System.out.println("Controllin the frequency!");
		instance.controllingTheFrequency();
	
	}
}
