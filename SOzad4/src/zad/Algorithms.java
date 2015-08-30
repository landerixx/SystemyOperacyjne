package zad;





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
	
	
	public Algorithms(int iloscProcesow, int promienLokalnosci,
			int minIloscOdwolan, int maxIloscOdwolan, int iloscRamek) {
		super();
		this.iloscProcesow=iloscProcesow;
		this.promienLokalnosci = promienLokalnosci;
		this.minIloscOdwolan = minIloscOdwolan;
		this.maxIloscOdwolan = maxIloscOdwolan;
		
		procesy=ReprezentacjaProcesow.generujRandCiagProcesowR(iloscProcesow,
				minIloscOdwolan, maxIloscOdwolan, promienLokalnosci);
		
		this.iloscRamek=iloscRamek;
	}
	public Algorithms(ArrayList<Proces> procesy, int iloscRamek){
		this.procesy=procesy;
		this.iloscRamek=iloscRamek;
		iloscProcesow=procesy.size();
		promienLokalnosci=0;
		maxIloscOdwolan=0;
		minIloscOdwolan=0;
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
	}
	
	
	
	
	public void equalAllocation(){
		
		clear();
		
		przydzielRamkiRowno();
		int i=0;
		for(Proces p: procesy){
			
			LRU.LRUalgorithm(p);
			System.out.println("dla Procesu nr " +i+ ": "+ p);
			i++;
		}
	}
	

	void przydzielRamkiRowno(){
		clear();
			int ilosc = iloscRamek/iloscProcesow;			//ilosc ramek dla pojedynczego procesu
			int licznikRamek=0;
			for(Proces p: procesy){
				p.maxLokalnailoscRamek+=ilosc;
				licznikRamek+=ilosc;
			}
		
			Random rand= new Random();
			while(licznikRamek!=iloscRamek){ //pozostale ramki dla randomowych procesow, zeby wykorzystac cala pule
				int random= rand.nextInt(procesy.size());
				Proces proces=procesy.get(random);
				proces.maxLokalnailoscRamek++;
				licznikRamek++;
			
			}
		
	}
	
	
	
	void proportionalAllocation(){
		clear();
		przydzielRamkiProporcjonalnie();		//przydziel ramki proporcjonalnie do dlg ciagu odwolan
		int i=0;
		for(Proces p: procesy){
			
			LRU.LRUalgorithm(p);
			System.out.println("dla Procesu nr " +i+ ": "+ p);
			i++;
		}							
	}
	
	
	void przydzielRamkiProporcjonalnie() {
		clear();
		int suma = calkowitaLiczbaOdwolan();
		int licznikRamek=0;
		for(Proces p: procesy){
			p.maxLokalnailoscRamek=iloscRamek*p.iloscOdwolan/suma; //ilosc odwolan/calkowita liczba odwolan* ilosc ramek
			licznikRamek+=p.maxLokalnailoscRamek;
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
			proces.maxLokalnailoscRamek++;
			licznikRamek++;
			i++;
		
		}
	}
	
	
	int calkowitaLiczbaOdwolan() {			//do proporcjonalnego przydzialu ramek
		int licznik = 0;
		for(Proces p: procesy)
			licznik+=p.iloscOdwolan;
		return licznik;
	}
	
	
	
	
	
	
}
