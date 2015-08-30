package zad1;

import java.io.IOException;
import java.util.ArrayList;

public class Test3 {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		
		int ileProcesorow=5;
		int maxIloscProcesow=100;
		int progP=60;
		int progR=20;
		int iloscZapytanZ=10;
		
		int okresPomiaruObciazen=20;
		
		
		String plik="pliczek";
	//	ReprezentacjaProcesorow.save(plik, ileProcesorow, maxIloscProcesow);
		
		
		ArrayList<Procesor> procesory;
	procesory=	ReprezentacjaProcesorow.restore(plik);
		
		Algorithms al= new Algorithms(procesory, progP, iloscZapytanZ, progR);
		al.wyswietlProcesory();
		al.symulacjaPierwsza(okresPomiaruObciazen);
		al.symulacjaDruga(okresPomiaruObciazen);
		al.symulacjaTrzecia(okresPomiaruObciazen);
		
		
		
		
		
	}
}
