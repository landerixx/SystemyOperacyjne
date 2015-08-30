package Wyjsciowe1005;

import java.util.ArrayList;

public class TestLokalnoscPromien {

	
	public static void main ( String[] args){
		
		int iloscProcesow=10;
		int minIloscOdwolan=5;
		int maxIloscOdwolan=50;
		int iloscRamek=30;
		int PromienLokalnosci=10;
		
		ArrayList<Proces> procesy= ReprezentacjaProcesow.generujRandCiagProcesowR
				(iloscProcesow, minIloscOdwolan, maxIloscOdwolan, PromienLokalnosci);
		System.out.println("lokalnosc z promieniem lokalnosci procesow");
		Algorithms instance = new Algorithms(procesy, iloscRamek);
		instance.wyswietlProcesy();
		
		
		System.out.println();
		System.out.println("proportional allocation");
		instance.proportionalAllocation();
		
		
		System.out.println();
		System.out.println("equal allocation");
		instance.equalAllocation();
	
		
		
	}
}
