package zad1;

import java.util.ArrayList;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Proces p1= new Proces(20, 0, 10);
		Proces p2= new Proces(10, 1, 10);
		Proces p3= new Proces(30, 6, 10);
		Proces p4= new Proces(9, 7, 10);
		ArrayList<Proces> procesy1= new ArrayList<Proces>();
		procesy1.add(p1);
		procesy1.add(p2);
		procesy1.add(p3);
		procesy1.add(p4);
		Procesor proc1= new Procesor(1, procesy1);
	
		
		
		ArrayList<Proces> procesy2= new ArrayList<Proces>();
		procesy2.add(new Proces(40, 2, 14));
		procesy2.add(new Proces(20, 8, 9));
		procesy2.add(new Proces(30, 2, 3));
		procesy2.add(new Proces(10, 4, 5));
		procesy2.add(new Proces(4, 7, 14));
		Procesor proc2= new Procesor(2, procesy2);
	
		
		
		ArrayList<Procesor> procesory= new ArrayList<Procesor>();
		procesory.add(proc1);
		procesory.add(proc2);
		
		int ileProcesorow=2;
		int progR=20;
		int progP=20;
		int iloscZapytanZ=10;
		int okresPomiaruObciazen=1;
		
		Algorithms al= new Algorithms(procesory, progP, iloscZapytanZ, progR);
		al.wyswietlProcesory();
		al.symulacjaPierwsza(okresPomiaruObciazen);
		al.symulacjaDruga(okresPomiaruObciazen);
		al.symulacjaTrzecia(okresPomiaruObciazen);
		
		
		
	}

}
