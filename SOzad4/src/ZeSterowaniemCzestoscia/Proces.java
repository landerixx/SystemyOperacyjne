package ZeSterowaniemCzestoscia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Proces implements Serializable {

	
	ArrayList<Odwolanie> ciagOdwolanProcesu;
	ArrayList<Odwolanie> tablicaRamekProcesu;

	int promienLokalnosci;
	int iloscOdwolan;
	
	int przydzielonaIloscRamek;
	int iloscBledowStron;
	boolean skonczony = false; // czy proces zostal zakonczony (np krotszy)
	
	Queue<Integer> ostatnieBledy;	//do sterowania czestoscia bledow
	
	
	
	
	//boolean status= false; // false -> proces wstrzymany , true -> proces akrywny
	
	
	public Proces(ArrayList<Odwolanie> ciagOdwolanProcesu){
		this.ciagOdwolanProcesu=ciagOdwolanProcesu;
		iloscOdwolan=ciagOdwolanProcesu.size();
		iloscBledowStron=0;
		przydzielonaIloscRamek=0;
		promienLokalnosci=0;
		tablicaRamekProcesu=new ArrayList<Odwolanie>();
		ostatnieBledy =  new ArrayBlockingQueue<Integer>(10);
	}
	
	public Proces( int iloscOdwolan, int maxWartoscOdwolania,int promienLokalnosci) {
		
		this.promienLokalnosci = promienLokalnosci;
		this.iloscOdwolan = iloscOdwolan;
		ciagOdwolanProcesu=ReprezentacjaOdwolan.lokalnoscPromien(iloscOdwolan, maxWartoscOdwolania, promienLokalnosci);
		tablicaRamekProcesu=new ArrayList<Odwolanie>();
		iloscBledowStron=0;
		ostatnieBledy =  new ArrayBlockingQueue<Integer>(10);
	}
	

	public Proces( int iloscOdwolan, int maxWartoscOdwolania) {
		
		this.promienLokalnosci = -1;
		this.iloscOdwolan = iloscOdwolan;
		ciagOdwolanProcesu=ReprezentacjaOdwolan.lokalnosc(iloscOdwolan, maxWartoscOdwolania);
		tablicaRamekProcesu=new ArrayList<Odwolanie>();
		iloscBledowStron=0;
		ostatnieBledy =  new ArrayBlockingQueue<Integer>(10);
	}
	
	
	
	
	
	
	
	
	
	
	public void dodajBlad(int i) {	//dodaje 0 (brak bledu) 1(blad), 
									//jezeli przekroczyla size to usuwam heada i dodaje 0/1
		if(((ArrayBlockingQueue<Integer>) ostatnieBledy).remainingCapacity()!=0)
			ostatnieBledy.add(i);
		else{
			ostatnieBledy.poll();
			ostatnieBledy.add(i);
		}
	}
	
	double procentBledow(){					//procent  bledow w kolejce
		if(ostatnieBledy.size()<10)  	 	//niech kolekjka najpierw sie zapewni, przez pierwsze 10odwolan
			return 0.5;
		int licznik=0;
		for(int i =0;i<ostatnieBledy.size();i++){
			licznik+=ostatnieBledy.peek();
			ostatnieBledy.add(ostatnieBledy.poll());
		}
		return (double)licznik/ostatnieBledy.size();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public boolean wypelnionaTablicaRamek(){
		return tablicaRamekProcesu.size()==przydzielonaIloscRamek;
	}
	
	
	public void clear(){
		iloscBledowStron=0;
		przydzielonaIloscRamek=0;
		skonczony=false;
		tablicaRamekProcesu=new ArrayList<Odwolanie>();
	}
	
	
	
	
	
	
	public String toString(){
		
		
		return "ilosc Odwolan: " + iloscOdwolan + ", promien: " + promienLokalnosci +
				", przydzielone ramki: " + przydzielonaIloscRamek +"  czy zakonczony? "+
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
