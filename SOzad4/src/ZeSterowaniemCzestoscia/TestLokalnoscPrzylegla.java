package ZeSterowaniemCzestoscia;

import java.util.ArrayList;

public class TestLokalnoscPrzylegla {

	
	public static void main(String[] args){
		int iloscProcesow=10;
		int minIloscOdwolan=5;
		int maxIloscOdwolan=50;
		int iloscRamek=30;
		
		ArrayList<Proces> procesy= ReprezentacjaProcesow.generujRandCiagProcesowLokalnoscPrzylegla
				(iloscProcesow, minIloscOdwolan, maxIloscOdwolan);
		
		Algorithms instance = new Algorithms(procesy, iloscRamek);
		System.out.println("lokalnosc przylegla procesow");
		instance.wyswietlProcesy();
		
		
		System.out.println();
		System.out.println("proportional allocation");
		instance.proportionalAllocation();
		
		
		System.out.println();
		System.out.println("equal allocation");
		instance.equalAllocation();
	
		
	
	
	}
}
