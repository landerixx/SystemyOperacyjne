package zRownoleglym;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Proces implements Serializable {

	
	ArrayList<Odwolanie> ciagOdwolanProcesu;
	ArrayList<Odwolanie> tablicaRamekProcesu;
	
	int promienLokalnosci;
	int iloscOdwolan;
	
	//int przydzielonaIloscRamek;
	
	
	int maxLokalnaIloscRamek;
	
	
	boolean status= false; // false -> proces wstrzymany , true -> proces akrywny
	boolean skonczony = false; // czy proces zostal zakonczony (np krotszy)
	
	
	
	
	
	int iloscBledowStron;
	
	public Proces(ArrayList<Odwolanie> ciagOdwolanProcesu){
		this.ciagOdwolanProcesu=ciagOdwolanProcesu;
		iloscOdwolan=ciagOdwolanProcesu.size();
		iloscBledowStron=0;
		maxLokalnaIloscRamek=0;
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
		return tablicaRamekProcesu.size()==maxLokalnaIloscRamek;
	}
	
	
	public void clear(){
		iloscBledowStron=0;
		maxLokalnaIloscRamek=0;
		skonczony=false;
		tablicaRamekProcesu=new ArrayList<Odwolanie>();
	}
	
	
	
	
	
	
	public String toString(){
		
		
		return "ilosc Odwolan: " + iloscOdwolan + ", promien: " + promienLokalnosci +
				", przydzielone ramki: " + maxLokalnaIloscRamek +"  czy zakonczony? "+
				skonczony+ ".     odwolania: " + ciagOdwolanProcesu;
	}
	
	

	public static void save(String plik, Proces panProces) throws IOException{
		
		ObjectOutputStream wyj= new ObjectOutputStream(new FileOutputStream(plik));
		wyj.writeObject(panProces);
		wyj.close();
	}
	public static Proces restore(String plik) throws IOException, ClassNotFoundException{
		Proces panProces= null;
		ObjectInputStream wej=new ObjectInputStream(new FileInputStream(plik));
		panProces=(Proces)wej.readObject();
		wej.close();
		return panProces;
	}
	
}
