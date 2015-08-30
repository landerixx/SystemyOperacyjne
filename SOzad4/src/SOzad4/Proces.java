package SOzad4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
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
	Set<Integer> zbiorRoboczy;
	int priorytet;
	int oknoBledow;
	
	
	
	
	//boolean status= false; // false -> proces wstrzymany , true -> proces akrywny
	
	
	public Proces(ArrayList<Odwolanie> ciagOdwolanProcesu){
		Random random= new Random();
		priorytet=random.nextInt(10);
		this.ciagOdwolanProcesu=ciagOdwolanProcesu;
		iloscOdwolan=ciagOdwolanProcesu.size();
		iloscBledowStron=0;
		przydzielonaIloscRamek=0;
		promienLokalnosci=0;
		tablicaRamekProcesu=new ArrayList<Odwolanie>();
		oknoBledow=10;
		ostatnieBledy =  new ArrayBlockingQueue<Integer>(oknoBledow);
		zbiorRoboczy= new TreeSet<Integer>();
	}
	
	public Proces( int iloscOdwolan, int maxWartoscOdwolania,int promienLokalnosci) {
		Random random= new Random();
		priorytet=random.nextInt(10);
		this.promienLokalnosci = promienLokalnosci;
		this.iloscOdwolan = iloscOdwolan;
		ciagOdwolanProcesu=ReprezentacjaOdwolan.lokalnoscPromien(iloscOdwolan, maxWartoscOdwolania, promienLokalnosci);
		tablicaRamekProcesu=new ArrayList<Odwolanie>();
		iloscBledowStron=0;
		oknoBledow=10;
		ostatnieBledy =  new ArrayBlockingQueue<Integer>(oknoBledow);
		zbiorRoboczy= new TreeSet<Integer>();
	}
	

	public Proces( int iloscOdwolan, int maxWartoscOdwolania) {
		Random random= new Random();
		priorytet=random.nextInt(10);
		this.promienLokalnosci = -1;
		this.iloscOdwolan = iloscOdwolan;
		ciagOdwolanProcesu=ReprezentacjaOdwolan.lokalnosc(iloscOdwolan, maxWartoscOdwolania);
		tablicaRamekProcesu=new ArrayList<Odwolanie>();
		iloscBledowStron=0;
		oknoBledow=10;
		ostatnieBledy =  new ArrayBlockingQueue<Integer>(oknoBledow);
		zbiorRoboczy= new TreeSet<Integer>();
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
		if(ostatnieBledy.size()<10)  	 	//niech kolekjka najpierw sie zapelni, przez pierwsze 10odwolan
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
		
		
		return "priotytet: " + priorytet + ", ilosc Odwolan: " + iloscOdwolan + ", promien: " + promienLokalnosci +
				",ostateczna przydzielona ilosc ramki: " + przydzielonaIloscRamek +"  czy zakonczony? "+
				skonczony;//+ ".     odwolania: " + ciagOdwolanProcesu;
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
