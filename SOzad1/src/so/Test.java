package so;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;



public class Test {
	static int iloscProcesowWReprezentacji = 10;
	static int maxDlugoscProcesu = 100;
	static int maxOpoznienie = 150;
	static int kwantCzasu=10;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		/*Przesylam w tym samym stanie jakie mialem na zajecia. Ogolnie rzecz biorac w testach walczylem w jaki sposob 
		 * pokopiowac reprezentacje do odpowiednich metod (z klasy Rot, Fcfs, sjfzw, zjfbw). Wychodzily mi te same wyniki
		 * w sjfw i rot dlatego zrobilem na szybko prowizoryczny algorytm w klasie Rotacyjny( duze uproszczenie: 
		 * nawet jak procesowi zostanie mniejsza dlugosc od kwantu czasu to i tak pozostale procesy czekaja 
		 * az ten kwant minie). Z metoda clone tez mialem problemy (dlatego jest zakomentarzowane).
		 * Postanowilem zapisac reprezentacje do pliku (save() i restore() Object Streamami) i "pojedynczo uruchamiac"
		 * symulacje. Wyniki byly* juz odpowiednio rozne. Przygotowalem 4 pliki z wygenerowanymi reprezentacjami
		 * oraz 4 pliki z wynikami  symulacji na tych reprezentacjach. 
		 * Najszybszym spostrzezeniem bylo to, ze algorytm rot przy wysokim kwancie czasu(wiekszym od maxdlugosciprocesu)
		 * daje identyczny wynik jak algorytm FCFS. Najmniejszy czas oczekiwania dla sjf z wywlaszczeniem, troche 
		 * wolniejszy sjf bez wywlaszczenia. 
		 * generujCiag generuje losowo procesy podane sa wartosci granicznej w Randomie
		 * generujCiag1 generuje ciag z wieksza iloscia procesow o krotszej fazie (przy tej metodzie krotki opis)
		 * wczytajPlik metoda przystosowana do mojej klasy Proces -> porownywalismy z kolega nasze wyniki
		 * ( przeslal mi po prostu jakas reprezentacje)
		 * 
		 * Ogolnie Proces nie jest podzielony na fazy, ma pole DlugoscFazyProcesora. 
		 * 
		 * 
		 */
		
		
		
		
		FCFS fcfs= new FCFS();
		SJFbw sjfbw=new SJFbw();
		SJFzw sjfzw= new SJFzw();
		Rot rot=new Rot(kwantCzasu);
		Rotacyjny rotacyjny=new Rotacyjny(kwantCzasu);
		
		
		
			// save("testing.txt");  //tworzy  reprezentacje w pliku testing
	
			 
			 ArrayList<Proces> zpliku=new ArrayList<Proces>();
			 zpliku=restore("testing.txt");
			 System.out.println("Reprezentacja wejsciowa");
			 for(Proces p:zpliku)
				System.out.println(p);
				
			 
/* tutaj pojedynczo */
			 
	//	fcfs.fcfsAlgorithm(zpliku);
		//	sjfbw.sjfbwAlgorithm(zpliku);
	//	 sjfzw.sjfzwAlgorithm(zpliku);
		rot.rotAlgorithm(zpliku);
			 
			 
			 
			 
			 
			// rotacyjny.rotacyjnyAlgorithm(zpliku);			 
			 
			 
			 
			 
			 
			 
			 
			
			 
			 /*
				ArrayList<Proces> list = generujCiag(); 
				ArrayList<Proces> list = generujCiag1(); 
				ArrayList<Proces> list = wczytajPlik("linijka.txt");
					 System.out.println("Reprezentacja wejsciowa");
					 for(Proces p:list){
								System.out.println(p);
					 }
					
				
				fcfs.fcfsAlgorithm(kopiujTablice(list));
				sjfbw.sjfbwAlgorithm(kopiujTablice(list));
				sjfzw.sjfzwAlgorithm(kopiujTablice(list));
				//rot.rotAlgorithm(kopiujTablice(list));
				rotacyjny.rotacyjnyAlgorithm(kopiujTablice(list));

				*/

/*
//metoda clone

ArrayList<Proces> listaProcesow1 = new ArrayList<Proces>();
ArrayList<Proces> listaProcesow2 = new ArrayList<Proces>();
ArrayList<Proces> listaProcesow3 = new ArrayList<Proces>();
//ArrayList<Proces> listaProcesow4 = new ArrayList<Proces>();
	 
	listaProcesow1 = (ArrayList<Proces>)list.clone();
	listaProcesow2 = (ArrayList<Proces>)list.clone();
	listaProcesow3 = (ArrayList<Proces>)list.clone();
	//listaProcesow4 = (ArrayList<Proces>)list.clone();

System.out.println();

	fcfs.fcfsAlgorithm(list);
	sjfbw.sjfbwAlgorithm(listaProcesow1);
	sjfzw.sjfzwAlgorithm(listaProcesow2);
	rot.rotAlgorithm(listaProcesow3);
	
	*/	



	}
		
	
	
	
	
	
	
	
	
	// metody do przygotowania reprezentacji procesow dla kazdego algorytmu

	
	static ArrayList<Proces> generujCiag() // tworze reprezentacje  procesow do symulacji												
	{
		ArrayList<Proces> lista = new ArrayList<Proces>();
		Random r = new Random();	
		for (int i = 0; i <iloscProcesowWReprezentacji; i++) {	
			lista.add(new Proces(i, r.nextInt(maxOpoznienie),
			r.nextInt(maxDlugoscProcesu)+1));
		}											
		return lista;
	}
	
	
	
	
	
/*
 * Zalozenie
 *  3/4 procesow (z calego ciagu)  o niewielkiej dlugosci( od 1 jednostki czasu do 1/4 max wielkosci)  
 *  1/4 procesow o wiekszej dlugosci (od 1/4 maxa do maxa)
 * Chodzilo mi o to, zeby w reprezentacji znalazlo sie duzo procesow krotkich oraz  mniejsza ilosc dlugich
 */
	
	
	static ArrayList<Proces> generujCiag1() // tworze reprezentacje  procesow do symulacji												
	{
		ArrayList<Proces> lista = new ArrayList<Proces>();
		Random r = new Random();		
				//krotkie
		for (int i = 0; i <iloscProcesowWReprezentacji; i++) {
			if(i<(iloscProcesowWReprezentacji*3/4))
			lista.add(new Proces(i, r.nextInt(maxOpoznienie),
			r.nextInt(maxDlugoscProcesu/4)+1));
			else //dlugie
				lista.add(new Proces(i, r.nextInt(maxOpoznienie),
						r.nextInt(maxDlugoscProcesu*3/4)+maxDlugoscProcesu/4+1));
								}		
		return lista;
	}
	
	
	
	
	static ArrayList<Proces> kopiujTablice(ArrayList<Proces> procesy) {//kopiuje reprezentacje procesow do algorytmow
		ArrayList<Proces> wyn = new ArrayList<Proces>();
		for (Proces p : procesy) {
			wyn.add(p);
		}
		return wyn;
	}
	

	
	
	
	
	 static ArrayList<Proces> kopiuj(ArrayList<Proces> procesy) {
		 ArrayList<Proces> wyn = new ArrayList<Proces>();
		 Proces proc=null;
		 for (Proces p : procesy) {
			 proc= p.clone();
		}
		return wyn;
	}
	
	
	
	
	public static ArrayList<Proces> wczytajPlik(String nazwa) {
		File plik = new File(nazwa);
		ArrayList<Proces> kolejka = new ArrayList<Proces>();
		try {
			BufferedReader odczyt = new BufferedReader(new FileReader(plik));
			String s = odczyt.readLine();
			StringTokenizer tok = new StringTokenizer(s, ",;", true);
			while (tok.hasMoreTokens()) {
				int pid = Integer.parseInt(tok.nextToken());
				tok.nextToken();
				int ready = Integer.parseInt(tok.nextToken());
				tok.nextToken();
				int time = Integer.parseInt(tok.nextToken());
				tok.nextToken();
				kolejka.add(new Proces(pid, ready, time));
odczyt.close();
			}
		} catch (IOException e) {
			System.out.println("blad");
		}
		return kolejka;
		
		
		
		
	}
	
	public static void save(String plik) throws IOException{
		ArrayList<Proces> listek= new ArrayList<Proces>();
		listek=generujCiag1();
		
		ObjectOutputStream wyj= new ObjectOutputStream(new FileOutputStream(plik));
		wyj.writeObject(listek);
		wyj.close();
	}
	public static ArrayList<Proces> restore(String plik) throws IOException, ClassNotFoundException{
		ArrayList<Proces> listek= new ArrayList<Proces>();
		ObjectInputStream wej=new ObjectInputStream(new FileInputStream(plik));
		listek=(ArrayList<Proces>)wej.readObject();
		wej.close();
		return listek;
	}
	
	
	
	
	
	
	
}
