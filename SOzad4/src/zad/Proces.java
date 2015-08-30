package zad;

import java.util.ArrayList;

public class Proces {

	
	ArrayList<Odwolanie> ciagOdwolanProcesu;
	ArrayList<Odwolanie> tablicaRamekProcesu;
	
	int promienLokalnosci;
	int iloscOdwolan;
	
	//int przydzielonaIloscRamek;
	
	
	int maxLokalnailoscRamek;
	
	
	boolean status= false; // false -> proces wstrzymany , true -> proces akrywny
	boolean skonczony = false; // czy proces zostal zakonczony (np krotszy)
	
	
	
	
	
	int iloscBledowStron;
	
	public Proces(ArrayList<Odwolanie> ciagOdwolanProcesu){
		this.ciagOdwolanProcesu=ciagOdwolanProcesu;
		iloscOdwolan=ciagOdwolanProcesu.size();
		iloscBledowStron=0;
		maxLokalnailoscRamek=0;
		promienLokalnosci=0;
		tablicaRamekProcesu=new ArrayList<Odwolanie>();
	}
	
	public Proces( int iloscOdwolan, int maxWartoscOdwolania,int promienLokalnosci) {
		
		this.promienLokalnosci = promienLokalnosci;
		this.iloscOdwolan = iloscOdwolan;
		ciagOdwolanProcesu=ReprezentacjaOdwolan.lokalnoscPromien(iloscOdwolan, maxWartoscOdwolania, promienLokalnosci);
		tablicaRamekProcesu=new ArrayList<Odwolanie>();
		iloscBledowStron=0;
	//	przydzielonaIloscRamek=5;
	}
	

	public Proces( int iloscOdwolan, int maxWartoscOdwolania) {
		
		this.promienLokalnosci = -1;
		this.iloscOdwolan = iloscOdwolan;
		ciagOdwolanProcesu=ReprezentacjaOdwolan.lokalnosc(iloscOdwolan, maxWartoscOdwolania);
		tablicaRamekProcesu=new ArrayList<Odwolanie>();
		iloscBledowStron=0;
	//	przydzielonaIloscRamek=5;
	}
	
	
	
	
	
	public boolean wypelnionaTablicaRamek(){
		return tablicaRamekProcesu.size()==maxLokalnailoscRamek;
	}
	
	
	public void clear(){
		iloscBledowStron=0;
		maxLokalnailoscRamek=0;
		skonczony=false;
	}
	
	
	
	
	
	
	public String toString(){
		
		
		return "ilosc Odwolan: " + iloscOdwolan + ", promien lokalnosci " + promienLokalnosci +
				", lokalna ilosc przydzielonych ramek " + maxLokalnailoscRamek +"  czy zakonczony? "+
				skonczony+ "     odwolania: " + ciagOdwolanProcesu;
	}
	
	
	
}
