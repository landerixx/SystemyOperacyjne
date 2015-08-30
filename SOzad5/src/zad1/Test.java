package zad1;

public class Test {

	
	public static void main(String[] args){
		
		int ileProcesorow=5;
		int maxIloscProcesow=100;
		int progP=60;
		int iloscZapytanZ=10;
		
		int okresPomiaruObciazen=20;
		
		Algorithms al= new Algorithms(ileProcesorow, maxIloscProcesow, progP, iloscZapytanZ);
		al.wyswietlProcesory();
		al.symulacjaPierwsza(okresPomiaruObciazen);
		
		
		
		
		
	}
}
