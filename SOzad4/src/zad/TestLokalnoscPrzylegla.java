package zad;

import java.util.ArrayList;

public class TestLokalnoscPrzylegla {

	
	public static void main(String[] args){
		int iloscProcesow=5;
		int minIloscOdwolan=50;
		int maxIloscOdwolan=300;
		int iloscRamek=6;
		
		ArrayList<Proces> procesy= ReprezentacjaProcesow.generujRandCiagProcesowLokalnoscPrzylegla
				(iloscProcesow, minIloscOdwolan, maxIloscOdwolan);
		
		Algorithms instance = new Algorithms(procesy, iloscRamek);
		instance.wyswietlProcesy();
		
		/*
		System.out.println();
		instance.equalAllocation();
		*/
		
		System.out.println();
		instance.przydzielRamkiProporcjonalnie();
		instance.wyswietlProcesy();
		System.out.println();
		instance.proportionalAllocation();
		
	}
}
