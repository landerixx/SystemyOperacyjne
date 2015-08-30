package Wyjsciowe1005;





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
				p.maxLokalnaIloscRamek=1;
				p.maxLokalnaIloscRamek+=ilosc;
				licznikRamek+=ilosc;
			}
		
			int i=0;
			while(licznikRamek!=d){ //pozostale ramki po kolei dla procesow, zeby wykorzystac cala pule
				procesy.get(i).maxLokalnaIloscRamek++;
				i++;
				licznikRamek++;
			
			}
		
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
			
			p.maxLokalnaIloscRamek=1+ ((iloscRamek-iloscProcesow)*p.iloscOdwolan/suma); //ilosc odwolan/calkowita liczba odwolan* ilosc ramek
			licznikRamek+=p.maxLokalnaIloscRamek;
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
			proces.maxLokalnaIloscRamek++;
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
