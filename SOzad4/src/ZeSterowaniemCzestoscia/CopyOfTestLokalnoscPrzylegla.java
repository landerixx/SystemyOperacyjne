package ZeSterowaniemCzestoscia;

import java.util.ArrayList;

public class CopyOfTestLokalnoscPrzylegla {

	
	public static void main(String[] args){
		int iloscProcesow=5;
		int minIloscOdwolan=5;
		int maxIloscOdwolan=50;
		int iloscRamek=30;
		
		ArrayList<Proces> procesy= ReprezentacjaProcesow.generujRandCiagProcesowLokalnoscPrzylegla
				(iloscProcesow, minIloscOdwolan, maxIloscOdwolan);
		
		
		
		Algorithms instance = new Algorithms(procesy, iloscRamek);
		System.out.println("lokalnosc przylegla procesow");
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
