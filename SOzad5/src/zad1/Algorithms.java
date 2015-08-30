package zad1;

import java.util.ArrayList;
import java.util.Random;

public class Algorithms {
	
	
	ArrayList<Procesor> procesory;
	int globalTimer;
	
	
	
	int progP;
	int progR;
	int iloscZapytanZ;
	Random rand;
	
	int iloscZapytanoObciazenie;
	int iloscMigracji;

	public Algorithms(int ileProcesorow, int progP, int iloscZapytanZ, int progR) {
		procesory= new ArrayList<Procesor>();
		for(int i=1;i<=ileProcesorow;i++)
			procesory.add(new Procesor(i));
		globalTimer=0;
		
		this.progP=progP;
		this.progR=progR;
		this.iloscZapytanZ=iloscZapytanZ;
		rand= new Random();
		
		 iloscZapytanoObciazenie=0;
		 iloscMigracji=0;

		
	}
	
	public Algorithms(int ileProcesorow, int maxIloscProcesow,  int progP, int iloscZapytanZ, int progR) {
		procesory= new ArrayList<Procesor>();
		for(int i=1;i<=ileProcesorow;i++)
			procesory.add(new Procesor(i,maxIloscProcesow));
		globalTimer=0;
		
		this.progP=progP;
		this.progR=progR;
		this.iloscZapytanZ=iloscZapytanZ;
		rand= new Random();

		 iloscZapytanoObciazenie=0;
		 iloscMigracji=0;

	}
	
	
	public Algorithms(ArrayList<Procesor> Procesory,  int progP, int iloscZapytanZ, int progR){
		procesory= Procesory;
		globalTimer=0;
		
		this.progP=progP;
		this.progR=progR;
		this.iloscZapytanZ=iloscZapytanZ;
		rand= new Random();

		 iloscZapytanoObciazenie=0;
		 iloscMigracji=0;

	}
	
	
	
	
	void wyswietlProcesory(){
		for(Procesor pr:procesory)
			System.out.println(pr);
	}

	
	void clear(){
		
		for(Procesor pr: procesory)
		pr.clear();

		 iloscZapytanoObciazenie=0;
		 iloscMigracji=0;
		 globalTimer=0;

	}

	
	private boolean doWykonania(){
		boolean bool=false;
		for(Procesor pr:procesory){
			if(pr.czySaDoWykonania())
				return true;
		}
			return bool;
	
	}
	
	
	private boolean saWKolejkach(){
		boolean bool=false;
		for(Procesor pr:procesory){
			if(pr.czyCosWKolejce())
				return true;
		}
			return bool;
	
	}
	
	private boolean saDzialajaceProcesory(){
		boolean bool=false;
		for(Procesor pr:procesory){
			if(pr.czyProcesorDziala())
				return true;
		}
			return bool;
	
	}
	
	int sredniaObciazenProcesorow(){
		
		int srednia=0;
		for(Procesor p: procesory)
			srednia+=p.srednieObciazenie();
		
		srednia/=procesory.size();
		
		return srednia;
		
	}
	
	double odchylenieStandardoweObciazen(int srednia){
		double odchylenie =0;
		double doWariancji=0;
		for(Procesor p: procesory)
			doWariancji+=(p.srednieObciazenie()-srednia)*(p.srednieObciazenie()-srednia);
		
		double wariancja=doWariancji/procesory.size();
		odchylenie= Math.sqrt(wariancja);
		
		return odchylenie;
		
	}


public 	void symulacjaPierwsza(int okresPomiaruObciazen){
		
		clear();
		
		while(saDzialajaceProcesory() ||doWykonania() || saWKolejkach() ){
	
				for(Procesor procesor: procesory){	
					procesor.dodajDoKolejkiProcesy(globalTimer);
					
				if(procesor.czyCosWKolejce()){
					pytajOProgP(procesor);
				}
				
				if(procesor.czyProcesorDziala()){
					procesor.wykonajProcesy();
				}
					procesor.zegar++;
					procesor.pobierzObciazenie(okresPomiaruObciazen);
					
				}//for each procesorow
			
				globalTimer++;
			
	
	}//while 

		for(Procesor procesor: procesory)
			procesor.podsumowanie();

		
		System.out.println("PIERWSZA SYMULACJA");
		System.out.println("Czas symulacji to: " + globalTimer);
		int srednia=sredniaObciazenProcesorow();
		double odch=odchylenieStandardoweObciazen(srednia);
		System.out.print("Poszczegolne obciazenia precesorow: ");
		for(Procesor p: procesory)
			System.out.print(p.srednieObciazenie() +", ");
		System.out.println();
		System.out.println("Srednia obciazen procesorow wynosi: " +srednia);
		System.out.println("Odchylenie standardowe obciazen procesorow wynosi: " +odch);
		System.out.println("Ilosc zapytan o obciazenie dla symulacji wynosi: "+ iloscZapytanoObciazenie);
		System.out.println("Ilosc migracji dla symulacji wynosi: "+ iloscMigracji);
		
}








		void pytajOProgP(Procesor pytajacy){
			int i=0;
			int index=0;
			Procesor wylosowany=null;
			for(;i<iloscZapytanZ;i++){
				 index=wylosujZProcesorow();
				 wylosowany=procesory.get(index);
				while(wylosowany==pytajacy){
					index=wylosujZProcesorow();
					 wylosowany=procesory.get(index);
				}
				iloscZapytanoObciazenie++;
				if(wylosowany.stanObciazenia()<progP &&
		((wylosowany.stanObciazenia()+pytajacy.kolejkaProcesow.peek().obciazenie)<99)){
					iloscMigracji++;
					wyslij(pytajacy,wylosowany);
				return;
				}
			}
			//jezeli zaden procesor nie dostal procesu
			// sprawdzam czy moge dodac proces do procesora pytajacego
			// czy jego obciazenie nie bedzie powyzej 100 razem z tym nowym procesem
			if(i==iloscZapytanZ){
				if((pytajacy.aktualneObciazenieProcesora+pytajacy.kolejkaProcesow.peek().obciazenie)<99)
				wyslij(pytajacy,pytajacy);
			}
			
			
			
		}
		
		void wyslij(Procesor pytajacy, Procesor wylosowany){
			
			Proces wysylany=pytajacy.kolejkaProcesow.poll();
			wylosowany.procesyWykonywane.add(wysylany);
			wylosowany.aktualneObciazenieProcesora+=wysylany.obciazenie;
			
			
			
			
		}

		private int wylosujZProcesorow(){
			
			int index=rand.nextInt(procesory.size());		
			return index;
		}

		
		
		public 	void symulacjaDruga(int okresPomiaruObciazen){
			
			clear();
			
			while(saDzialajaceProcesory() ||doWykonania() || saWKolejkach() ){
		
					for(Procesor procesor: procesory){	
						procesor.dodajDoKolejkiProcesy(globalTimer);
						
						
					if(procesor.czyCosWKolejce()){
						
						if(procesor.aktualneObciazenieProcesora<progP)
							wyslij(procesor, procesor);
					
						else
							pytajOProgP(procesor);
					
							
							
					
					}
						
					
					if(procesor.czyProcesorDziala()){
						procesor.wykonajProcesy();
					}
						procesor.zegar++;
						procesor.pobierzObciazenie(okresPomiaruObciazen);
						
					}//for each procesorow
				
					globalTimer++;
				
		
		}//while 

			for(Procesor procesor: procesory)
				procesor.podsumowanie();

			System.out.println();
			System.out.println("DRUGA SYMULACJA");
			System.out.println("Czas symulacji to: " + globalTimer);
			int srednia=sredniaObciazenProcesorow();
			double odch=odchylenieStandardoweObciazen(srednia);
			System.out.print("Poszczegolne obciazenia precesorow: ");
			for(Procesor p: procesory)
				System.out.print(p.srednieObciazenie() +", ");
			System.out.println();
			System.out.println("Srednia obciazen procesorow wynosi: " +srednia);
			System.out.println("Odchylenie standardowe obciazen procesorow wynosi: " +odch);
			System.out.println("Ilosc zapytan o obciazenie dla symulacji wynosi: "+ iloscZapytanoObciazenie);
			System.out.println("Ilosc migracji dla symulacji wynosi: "+ iloscMigracji);
			
	}


		
	public 	void symulacjaTrzecia(int okresPomiaruObciazen){
			
			clear();
			
			while(saDzialajaceProcesory() ||doWykonania() || saWKolejkach() ){
		
					for(Procesor procesor: procesory){	
						procesor.dodajDoKolejkiProcesy(globalTimer);
						
						
						
					if(procesor.aktualneObciazenieProcesora<progR){
						
						pomozInnemu(procesor);
					}
						
						
						
						
					if(procesor.czyCosWKolejce()){
						
						if(procesor.aktualneObciazenieProcesora<progP)
							wyslij(procesor, procesor);
					
						else
							pytajOProgP(procesor);
					
							
							
					
					}
						
					
					if(procesor.czyProcesorDziala()){
						procesor.wykonajProcesy();
					}
						procesor.zegar++;
						procesor.pobierzObciazenie(okresPomiaruObciazen);
						
					}//for each procesorow
				
					globalTimer++;
				
		
		}//while 

			for(Procesor procesor: procesory)
				procesor.podsumowanie();

			System.out.println();
			System.out.println("TRZECIA SYMULACJA");
			System.out.println("Czas symulacji to: " + globalTimer);
			int srednia=sredniaObciazenProcesorow();
			double odch=odchylenieStandardoweObciazen(srednia);
			System.out.print("Poszczegolne obciazenia precesorow: ");
			for(Procesor p: procesory)
				System.out.print(p.srednieObciazenie() +", ");
			System.out.println();
			System.out.println("Srednia obciazen procesorow wynosi: " +srednia);
			System.out.println("Odchylenie standardowe obciazen procesorow wynosi: " +odch);
			System.out.println("Ilosc zapytan o obciazenie dla symulacji wynosi: "+ iloscZapytanoObciazenie);
			System.out.println("Ilosc migracji dla symulacji wynosi: "+ iloscMigracji);
			
	}

		
		
		void pomozInnemu(Procesor helper){
			int i=0;
			int index=0;
			Procesor wylosowany=null;
			for(;i<iloscZapytanZ;i++){
				 index=wylosujZProcesorow();
				 wylosowany=procesory.get(index);
				while(wylosowany==helper){
					index=wylosujZProcesorow();
					 wylosowany=procesory.get(index);
				}
				iloscZapytanoObciazenie++;
				if(wylosowany.stanObciazenia()>progP){
				
			przejmij(helper, wylosowany);
				return;
				}
			}
				
		}
		
		void przejmij(Procesor helper, Procesor wylosowany){
			//paramter wynosi 20% od aktualne obiazenia procesora
			int parametr=wylosowany.aktualneObciazenieProcesora;
			parametr/=10;
			parametr*=2; //20 %
			Proces optimal=wylosowany.findOptimalProcess(parametr);
			//jezeli znaleziono to od razu go usunieto z wylosowanego
			
			
			//nie znaleziono +-5% od paramteru (optymalnego procesu)
			//przejmij pierwszy z listy wykonywanych
			if(optimal ==null){
				optimal=wylosowany.procesyWykonywane.get(0);
				wylosowany.aktualneObciazenieProcesora-=optimal.obciazenie;
				wylosowany.procesyWykonywane.remove(optimal);
			}
			helper.procesyWykonywane.add(optimal);
			iloscMigracji++;
			helper.aktualneObciazenieProcesora+=optimal.obciazenie;
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}






