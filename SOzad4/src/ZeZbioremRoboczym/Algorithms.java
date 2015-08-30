package ZeZbioremRoboczym;







import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;



public class Algorithms {

	int iloscProcesow;
	ArrayList<Proces> procesy;
	int promienLokalnosci;
	int minIloscOdwolan;
	int maxIloscOdwolan;
	
	int iloscRamek;
	int pulaWolnychRamek;
	int deltaZbioruRoboczego;
	
	
	public Algorithms(int iloscProcesow, int promienLokalnosci,
			int minIloscOdwolan, int maxIloscOdwolan, int iloscRamek,int deltaZbioruRoboczego) {
		super();
		this.iloscProcesow=iloscProcesow;
		this.promienLokalnosci = promienLokalnosci;
		this.minIloscOdwolan = minIloscOdwolan;
		this.maxIloscOdwolan = maxIloscOdwolan;
		
		
		procesy=ReprezentacjaProcesow.generujRandCiagProcesowR(iloscProcesow,
				minIloscOdwolan, maxIloscOdwolan, promienLokalnosci);
		
		this.iloscRamek=iloscRamek;
		pulaWolnychRamek=iloscRamek;
		
		this.deltaZbioruRoboczego=deltaZbioruRoboczego;
	}
	public Algorithms(ArrayList<Proces> procesy, int iloscRamek,int deltaZbioruRoboczego){
		this.procesy=procesy;
		this.iloscRamek=iloscRamek;
		iloscProcesow=procesy.size();
		promienLokalnosci=0;
		maxIloscOdwolan=0;
		minIloscOdwolan=0;
		pulaWolnychRamek=iloscRamek;
		this.deltaZbioruRoboczego=deltaZbioruRoboczego;
	}
	
	
	public void wyswietlProcesy(){
		int i=0;
		for(Proces p: procesy){
			System.out.println("Proces nr " +i+ ": "+ p);
		i++;
		}
	}
	
	public void clear(){
		for(Proces p:procesy)
			p.clear();
		pulaWolnychRamek=iloscRamek;
	}
	
	
	int wszystkieBledy(){
		int wszystkie=0;
		for(Proces p: procesy)
			wszystkie+=p.iloscBledowStron;
		return wszystkie;
	}
	
	
	public void equalAllocation(){
		
		clear();
		
		przydzielRamkiRowno();
		int i=0;
		System.out.println("equal Allocation!");
	
		for(Proces p: procesy){
			
			LRU.LRUalgorithm(p);
			System.out.println("Proces nr " +i+ ": "+ p);
			i++;
		}
		int wszystkieBledy=wszystkieBledy();
		System.out.println("Zsumowana ilosc bledow poszczegolnych procesow wynosi: " +wszystkieBledy);
		System.out.println("srednia ilosc bledow dla 1 procesu wynosi: " +wszystkieBledy/iloscProcesow);
		
		
	}
	

	//kazdy proces na start 1 ramka
	// potem rowno
	// jezeli cos zostanie to po kolei dla ciagu procesow ~rand
	void przydzielRamkiRowno()  throws IllegalArgumentException{
		if(iloscRamek<iloscProcesow)
			throw new IllegalArgumentException("za mala ilosc ramek, "
					+ "aby poprzdzielac wszystkim procesom po 1 ramce (rowny), nie badz zyd z ramkami");
		clear();
		int d= iloscRamek-iloscProcesow;
			int ilosc = d/iloscProcesow;			//ilosc ramek dla pojedynczego procesu
			int licznikRamek=0;
			for(Proces p: procesy){
				p.przydzielonaIloscRamek=1;
				p.przydzielonaIloscRamek+=ilosc;
				licznikRamek+=ilosc;
			}
		
			int i=0;
			while(licznikRamek!=d){ //pozostale ramki po kolei dla procesow, zeby wykorzystac cala pule
				procesy.get(i).przydzielonaIloscRamek++;
				i++;
				licznikRamek++;
			
			}
			pulaWolnychRamek=0;
		
	}
	
	
	
	public void proportionalAllocation(){
		clear();
		przydzielRamkiProporcjonalnie();		//przydziel ramki proporcjonalnie do dlg ciagu odwolan
		int i=0;
		System.out.println("propotional Allocation!");
		for(Proces p: procesy){
			
			LRU.LRUalgorithm(p);
			System.out.println("dla Procesu nr " +i+ ": "+ p);
			i++;
		}			
		int wszystkieBledy=wszystkieBledy();
		System.out.println("Zsumowana ilosc bledow poszczegolnych procesow wynosi: " +wszystkieBledy);
		System.out.println("srednia ilosc bledow dla 1 procesu wynosi: " +wszystkieBledy/iloscProcesow);
	}
	
	
	
	
	//kazdy proces na start po 1 ramce
	// potem proporcjonalnie do wielkosci procesu ( jego ciagu odwolan)
	void przydzielRamkiProporcjonalnie() throws IllegalArgumentException {
		if(iloscRamek<iloscProcesow)
			throw new IllegalArgumentException("za mala ilosc ramek, "
					+ "aby poprzdzielac wszystkim procesom po 1 ramce (proporcjonalny), nie badz zyd z ramkami");
		clear();
		
		int suma = calkowitaLiczbaOdwolan();
		int licznikRamek=0;
		
		
		for(Proces p: procesy){
			
			p.przydzielonaIloscRamek=1+ ((iloscRamek-iloscProcesow)*p.iloscOdwolan/suma); //ilosc odwolan/calkowita liczba odwolan* ilosc ramek
			licznikRamek+=p.przydzielonaIloscRamek;
		}
		//sortuje, na poczatku najdluzsze procesy
		Collections.sort(procesy, new Comparator<Proces>() {
			public int compare(Proces p1, Proces p2) {
			return  p1.iloscOdwolan>p2.iloscOdwolan?-1:1;	
			}
		});
		
		while(licznikRamek!=iloscRamek){ //pozostale ramki dla najwiekszych procesow, zeby wykorzystac cala pule
			int i=0;
			Proces proces=procesy.get(i);
			proces.przydzielonaIloscRamek++;
			licznikRamek++;
			i++;
		
		}
		pulaWolnychRamek=0;
	}
	
	
	int calkowitaLiczbaOdwolan() {			//wsystkie odwolania
		int licznik = 0;
		for(Proces p: procesy)
			licznik+=p.iloscOdwolan;
		return licznik;
	}
	
	
	
	void lruRownolegle(){
		clear();
		przydzielRamkiRowno();
		
		int nrOdwolania=0;
		
				while(!wszystkieSkonczone()){ // rob dopoki nie sa wszystkie skonczone 
					int nrProcesu=0;
					for(;nrProcesu<iloscProcesow;nrProcesu++){ // przechodz 1 odwolanie 1 proc, 1 odwolanie 2 proc ...
						
						Proces danyProces=procesy.get(nrProcesu);
						if(!danyProces.skonczony){ // jezeli proces jest nieskonczony to one loop lru
							LRU.oneLoopLRU(danyProces, nrOdwolania);
								if(nrOdwolania==(danyProces.iloscOdwolan-1)) //jezeli to jego ostatnie odwolanie ,skoncz go
									danyProces.skonczony=true;		
						}//if skonczony
					} //for procesow
					
					nrOdwolania++;
				}//while
				
				int i=0;
				for(Proces p: procesy){
					System.out.println("Bledy stron: --> " + p.iloscBledowStron + "   Proces nr: "+ i  +" "+  p);
				i++;
				}
		}
		
		
	
	
	
	
	//na poczatku przydzielamy ramki proporcjonalnie, pula wolnych ramek =0
		// jezeli jakis krotszy proces sie skonczyl w naszej symulacji 
		//to przeciez jego ramki sie rowniez zwalaniaja, pula wolnych ramek wzrasta
		//wiec mozna te ramki jakos wykorzystac, zaczynam rozdawac je dla procesow najdluzszych
		//(na poczatku listy, sa najdluzsze, lista posortowana) 
		// po jakims czasie dla dluzszych procesow wiecej ramek, z puli
		public void parallelProportionalAllocation(){
			clear();
			przydzielRamkiProporcjonalnie();
			wyswietlProcesy();
			System.out.println();
			
			int nrOdwolania=0;
			
					while(!wszystkieSkonczone()){ // rob dopoki nie sa wszystkie skonczone 
						int nrProcesu=0;
						
						for(;nrProcesu<iloscProcesow;nrProcesu++){ // przechodz 1 odwolanie 1 proc, 1 odwolanie 2 proc ...
							
							Proces danyProces=procesy.get(nrProcesu);
							if(!danyProces.skonczony){ // jezeli proces jest nieskonczony to one loop lru
								LRU.oneLoopLRU(danyProces, nrOdwolania);
									if(nrOdwolania==(danyProces.iloscOdwolan-1)){ //jezeli to jego ostatnie odwolanie ,skoncz go
										danyProces.skonczony=true;		
									pulaWolnychRamek+=danyProces.przydzielonaIloscRamek;
									//danyProces.maxLokalnaIloscRamek=0;
									przydzielDostepneRamki();
									
									}//if jezeli sie skonczyl
							}//if skonczony
						} //for procesow
						
						nrOdwolania++;
					}//while
					
					int i=0;
					for(Proces p: procesy){
						System.out.println("Bledy stron: --> " + p.iloscBledowStron + "   Proces nr: "+ i  +" "+  p);
					i++;
					}
			}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//na poczatku przydzielamy ramki rowno, pula wolnych ramek =0
	// jezeli jakis krotszy proces sie skonczyl w naszej symulacji 
	//to przeciez jego ramki sie rowniez zwalaniaja, pula wolnych ramek wzrasta
	//wiec moze te ramki jakos wykorzystac (randomowo, te ktore sa jeszcze nie ukonczone)
	// po jakims czasie dla dluzszych procesow wiecej ramek, z puli
	public void parallelEqualAllocation(){
		clear();
		przydzielRamkiRowno();
		wyswietlProcesy();
		System.out.println();
		
		int nrOdwolania=0;
		
				while(!wszystkieSkonczone()){ // rob dopoki nie sa wszystkie skonczone 
					int nrProcesu=0;
					
					for(;nrProcesu<iloscProcesow;nrProcesu++){ // przechodz 1 odwolanie 1 proc, 1 odwolanie 2 proc ...
						
						Proces danyProces=procesy.get(nrProcesu);
						if(!danyProces.skonczony){ // jezeli proces jest nieskonczony to one loop lru
							LRU.oneLoopLRU(danyProces, nrOdwolania);
								if(nrOdwolania==(danyProces.iloscOdwolan-1)){ //jezeli to jego ostatnie odwolanie ,skoncz go
									danyProces.skonczony=true;		
								pulaWolnychRamek+=danyProces.przydzielonaIloscRamek;
								//danyProces.maxLokalnaIloscRamek=0;
								przydzielDostepneRamki();
								
								}//if jezeli sie skonczyl
						}//if skonczony
					} //for procesow
					
					nrOdwolania++;
				}//while
				
				int i=0;
				for(Proces p: procesy){
					System.out.println("Bledy stron: --> " + p.iloscBledowStron + "   Proces nr: "+ i  +" "+  p);
				i++;
				}
		}
		
		
		void przydzielDostepneRamki(){ //przydzielaj po kolei po indeksie
			//dopoki sa jeszcze jakies procesy do zrobienia i jest cos w puli
			//jezeli przejdzie caly ciag procesow po 1 ramce, i cos zostalo, to przejdz od nowa
			
			int index=0;
			while(!wszystkieSkonczone()&&pulaWolnychRamek>0){
				
				Proces danyProces= procesy.get(index);
				if(!danyProces.skonczony){
					danyProces.przydzielonaIloscRamek++;
					pulaWolnychRamek--;
				}
				index++;
				if(index==(iloscProcesow-1))
					index=0;
			
			}
			
		}
	
	

	private boolean wszystkieSkonczone() {		//sprawdza czy skonczylismy przetwarzanie procesow
		boolean skonczone = true;
		for (int i =0;i<iloscProcesow&&skonczone;i++)
			if(!procesy.get(i).skonczony) 
				skonczone = false;
		return skonczone;
	}
	
	
	
	private void balansuj(Proces p) {
		
		if(p.procentBledow()>=0.6&&pulaWolnychRamek>0){ //jezeli powyzej 60% to proces potrzebuje ramki, jezeli taka jest
			p.przydzielonaIloscRamek++;
			pulaWolnychRamek--;
		}
		else if(p.procentBledow()<=0.1 &&p.przydzielonaIloscRamek>1){//jezeli generuje za malo bledow i ma wiecej niz 1 ramke, trzeba zabrac ramke
			if(p.wypelnionaTablicaRamek()){
				p.tablicaRamekProcesu.remove(p.tablicaRamekProcesu.size()-1);
			}
			p.przydzielonaIloscRamek--;
			pulaWolnychRamek++;
			
		}
	}
	

	//na poczatku przydzielamy ramki rowno, pula wolnych ramek =0
	//w miare potrzeb usuwamy/zwiekszamy ilosc ramek, jezeli pula na to pozwala
	// jezeli jakis krotszy proces sie skonczyl w naszej symulacji 
	//to przeciez jego ramki sie rowniez zwalaniaja, pula wolnych ramek wzrasta
	//wiec moze te ramki jakis proces  wykorzystac 
	
	public void controllingTheFrequency(){
		clear();
		przydzielRamkiRowno();
		wyswietlProcesy();
		System.out.println();
		
		int nrOdwolania=0;
		
				while(!wszystkieSkonczone()){ // rob dopoki nie sa wszystkie skonczone 
					int nrProcesu=0;
					
					for(;nrProcesu<iloscProcesow;nrProcesu++){ // przechodz 1 odwolanie 1 proc, 1 odwolanie 2 proc ...
						
						Proces danyProces=procesy.get(nrProcesu);
						if(!danyProces.skonczony){ // jezeli proces jest nieskonczony to one loop lru
							LRU.oneLoopLRU(danyProces, nrOdwolania);
							balansuj(danyProces); //jezeli potrzeba to zwieksz/zmniejsz ramki
								if(nrOdwolania==(danyProces.iloscOdwolan-1)){ //jezeli to jego ostatnie odwolanie ,skoncz go
									danyProces.skonczony=true;		
								pulaWolnychRamek+=danyProces.przydzielonaIloscRamek;
								
								
								}//if jezeli sie skonczyl
						}//if skonczony
					} //for procesow
					
					nrOdwolania++;
				}//while
				
				int i=0;
				for(Proces p: procesy){
					System.out.println("Bledy stron: --> " + p.iloscBledowStron + "   Proces nr: "+ i  +" "+  p);
				i++;
				}
				System.out.println("zostalo " + pulaWolnychRamek + " ramek");
		}
	
	
	
	
	
	void modelStrefowy(){
		clear();
		przydzielRamkiRowno();
		
		
		int nrOdwolania=0;

		while(!wszystkieSkonczone()){
			
			//na poczatku nie wejdzie w ifa
			//procesy musza wykonac delteZbioru odwolan, aby zapelnic zbior i, zeby moc obliczyc optymalna ilosc ramek
			if(nrOdwolania>=deltaZbioruRoboczego){
			int sumaZbiorowRoboczych=ustalSumeZbiorowRoboczych(nrOdwolania);
			przydzielOptymalnieRamki(sumaZbiorowRoboczych);
			}
			//dostalismy informacje o zbiorach, nastepnie usunelismy go, 
			//zeby znowu mogl  sie zapelnic o delte odwolan
			//w miare mozliwosci pozamieniane (oczywiscie jezeli byly wolne ramki), badz dodalismy ramki do puli
			
			int poprzedniNrOdwolania=nrOdwolania;
			for(;!wszystkieSkonczone()&&nrOdwolania<poprzedniNrOdwolania+deltaZbioruRoboczego;nrOdwolania++){
			int nrProcesu=0;
			for(;nrProcesu<iloscProcesow;nrProcesu++){ // przechodz 1 odwolanie 1 proc, 1 odwolanie 2 proc ...
				Proces danyProces=procesy.get(nrProcesu);
				if(!danyProces.skonczony){ // jezeli proces jest nieskonczony to one loop lru
					LRU.oneLoopLRU(danyProces, nrOdwolania);
					
						if(nrOdwolania==(danyProces.iloscOdwolan-1)){ //jezeli to jego ostatnie odwolanie ,skoncz go
							danyProces.skonczony=true;		
						pulaWolnychRamek+=danyProces.przydzielonaIloscRamek;
						
						
						}//if jezeli sie skonczyl
				}//if skonczony
			} //for procesow
			
		}//for na delte odwolan
		
		}	//while czy wszystkie skonczone
		
				int i=0;
				for(Proces p: procesy){
					System.out.println("Bledy stron: --> " + p.iloscBledowStron + "   Proces nr: "+ i  +" "+  p);
				i++;
				}
		}
		
	
	
	
	
	
	
		
		private int ustalSumeZbiorowRoboczych(int nrOdwolania){
			int suma=0;
			for(Proces proces:procesy){
				if(!proces.skonczony){
					
					for(int i=(nrOdwolania-1);i>=(nrOdwolania-deltaZbioruRoboczego);i--)
					proces.zbiorRoboczy.add(proces.ciagOdwolanProcesu.get(i).wartosc);
				
				suma+=proces.zbiorRoboczy.size();
			
				}//if
				
			}//for each
			return suma;
		}
	
		void przydzielOptymalnieRamki(int sumaZbiorowRoboczych){
			
			for(Proces proces:procesy){ //dla kazdego procesu oblicz jego optymalna ilosc ramek po zbiorze roboczym
				
				if(!proces.skonczony){ //ktory nie jest skonczony
					
					int optimalQuantity=iloscRamek*proces.zbiorRoboczy.size()/sumaZbiorowRoboczych;
						
					if( optimalQuantity>proces.przydzielonaIloscRamek){ //jezeli mozna dodaj ramki
						dodajOptymalnieRamki(proces,optimalQuantity);
					}
					
					else if(optimalQuantity<proces.przydzielonaIloscRamek){ //odejmij ramki
						odejmijOptymalnieRamki(proces,optimalQuantity);
					}
					proces.zbiorRoboczy.removeAll(proces.zbiorRoboczy); //wyzeruj zbior roboczy, zapelni sie o kolejne delte
				}
			} //for each
			
		}
		
			private void dodajOptymalnieRamki(Proces proces, int optimalQuantity){
				while(pulaWolnychRamek>0 && proces.przydzielonaIloscRamek<optimalQuantity) {
					proces.przydzielonaIloscRamek++;
					pulaWolnychRamek--;
				}
				
			}
			
			
			private void odejmijOptymalnieRamki(Proces proces, int optimalQuantity){
				while(proces.przydzielonaIloscRamek>optimalQuantity &&proces.przydzielonaIloscRamek>0){
					if(proces.wypelnionaTablicaRamek()){
						proces.tablicaRamekProcesu.remove(proces.tablicaRamekProcesu.size()-1);
					}
					proces.przydzielonaIloscRamek--;
					pulaWolnychRamek++;
				}
				
			}
	
	
	
	
}
