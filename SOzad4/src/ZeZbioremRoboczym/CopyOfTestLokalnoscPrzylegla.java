package ZeZbioremRoboczym;

import java.io.IOException;
import java.util.ArrayList;

public class CopyOfTestLokalnoscPrzylegla {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		int iloscProcesow=10;
		int minIloscOdwolan=5;
		int maxIloscOdwolan=1000;
		int iloscRamek=31;
		int PromienLokalnosci=10;
		int deltaZbioruRoboczego=10;
		System.out.println("lokalnosc przylegla procesow");
		
		String nazwaPliku="reprezentacjaProcPrzylegla";
		/*
		ArrayList<Proces> procesy= ReprezentacjaProcesow.generujRandCiagProcesowLokalnoscPrzylegla
				(iloscProcesow, minIloscOdwolan, maxIloscOdwolan);
		
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
