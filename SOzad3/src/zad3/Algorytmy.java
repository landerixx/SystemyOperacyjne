package zad3;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.Random;




public class Algorytmy {

ArrayList<Odwolanie> tablicaRamek;
ArrayList<Odwolanie> ciagOdwolan;

static Random rand=new Random();
int ileRamek;
int ileOdwolan;
int zakresOdwolania;
int iloscBledow;

Algorytmy(int ileRamek, int ileOdwolan, int zakresOdwolania){
	
	tablicaRamek=new ArrayList<Odwolanie>();
	ciagOdwolan=new ArrayList<Odwolanie>();
	for(int i=0; i<ileOdwolan;i++)
		ciagOdwolan.add(new Odwolanie(rand.nextInt(zakresOdwolania)));
	
	this.ileOdwolan=ileOdwolan;
	this.ileRamek=ileRamek;
	this.zakresOdwolania=zakresOdwolania;
	iloscBledow=0;
	
}
Algorytmy(int ileRamek, ArrayList<Odwolanie> ciag){
	tablicaRamek=new ArrayList<Odwolanie>();
	ciagOdwolan=ciag;
	ileOdwolan=ciag.size();
	iloscBledow=0;
	this.ileRamek=ileRamek;
}



public  void clearTablicaRamek(){
	tablicaRamek=new  ArrayList<Odwolanie>();
	iloscBledow=0;
	for(Odwolanie od: ciagOdwolan)
		od.czasSprowadzenia=0;
}


public  void pokazTabliceRamek(){
	System.out.print("tablica ramek:");
	for(int i=0;i<ileRamek;i++){	
	if(i<tablicaRamek.size())
			System.out.print("  " + tablicaRamek.get(i) + "  ");
	else
		System.out.print(" pusta  ");
	}
	//System.out.println();
}

public boolean wypelnionaTablicaRamek(){
	return tablicaRamek.size()==ileRamek;
}

public boolean czyJestJuzWTablicy(Odwolanie o){
	boolean znalazlem=false;
	for(int i=0;i< tablicaRamek.size()&&!znalazlem;i++){
		if(o.wartosc==tablicaRamek.get(i).wartosc)
			znalazlem=true;
	}
	return znalazlem;
}

public void dodajCzasWRamkach(){
	
	for(Odwolanie od: tablicaRamek)
		od.czasSprowadzenia+=1;
}

public static  Odwolanie RemoveLastVictim(ArrayList<Odwolanie> arraylista){
	Odwolanie od=arraylista.get(arraylista.size()-1);
	arraylista.remove(arraylista.size()-1);
	return od;
}
public static void addFirst(ArrayList<Odwolanie> arraylista,Odwolanie odwolanie){
	
	arraylista.add(0,odwolanie);
}
public static void addLast(ArrayList<Odwolanie> arraylista, Odwolanie odwolanie){
	arraylista.add(odwolanie);	
}
public static void removeLast(ArrayList<Odwolanie> arraylista){
	
	arraylista.remove(arraylista.size()-1);
}
public static void removefirst(ArrayList<Odwolanie> arraylista){
	
	arraylista.remove(0);
}
public static void removeRandom(ArrayList<Odwolanie> arraylista){
	int ind= rand.nextInt(arraylista.size());
	arraylista.remove(ind);
}

public static void removeOdwolanie(ArrayList<Odwolanie> arraylista, Odwolanie od){
	Iterator<Odwolanie> iter= arraylista.iterator();
	while(iter.hasNext())
		if(iter.next().wartosc==od.wartosc)
			iter.remove();	
}

public static  Odwolanie findRandomVictim(ArrayList<Odwolanie> arraylista){
	int ind= rand.nextInt(arraylista.size());
	Odwolanie od= arraylista.get(ind);
	return od;	
}

public  Odwolanie theOldestVictim(){
	
	Odwolanie theOldest=new Odwolanie(0);
	for(Odwolanie od: tablicaRamek){
		if(od.czasSprowadzenia>theOldest.czasSprowadzenia)
			theOldest=od;
	}
	return theOldest;
}
/*
int findIndexOfOptimalVictimCheck(int indexBledu){
	//lista, ktora bedzie wielkosci tablicy ramek, indeks jej wskazuje na ramke w tablicy a jej zawartosc to dlugosc czasu do nastepnego wystapienia
		 ArrayList<Integer> odleglosci=new ArrayList<Integer>();
		 int max=-1;
			int index=-1;
		for(int i=0; i< tablicaRamek.size();i++){
			
			boolean znalazlem=false;
			int j=indexBledu;
			for(;j<ciagOdwolan.size()-1&&!znalazlem;j++){ //sprawdzamy dla poszczegolnej strony z ramki kiedy(czy) sie powtorzy w naszym ciaguOdwolan
				if(tablicaRamek.get(i).wartosc==ciagOdwolan.get(j).wartosc)
					znalazlem=true;
			}
			int odleglosc=Integer.MAX_VALUE; //jezeli sie nie powtorzy
			if(znalazlem) // jezeli sie powtorzy
				odleglosc=j-indexBledu;
			odleglosci.add(i,odleglosc);	
		}
		//odleglosci juz sa
		// znajdujemy index ofiary ( z listy bierzerzemy wartosc maksymalna)
		
		System.out.println("odleglosci " +odleglosci);
		for(int k=0; k<odleglosci.size();k++){
			if(odleglosci.get(k)>max){
				max=odleglosci.get(k);
				index=k;
			}
		}
		 return index;
	}
*/


int findIndexOfOptimalVictim(int indexBledu){

		int max=-1;
		int index=-1;
		for(int i=0; i< tablicaRamek.size();i++){			
			boolean znalazlem=false;
			int j=indexBledu;
			for(;j<ciagOdwolan.size()-1&&!znalazlem;j++) //sprawdzamy dla poszczegolnej strony z ramki kiedy(czy) sie powtorzy w naszym ciaguOdwolan
				if(tablicaRamek.get(i).wartosc==ciagOdwolan.get(j).wartosc)
					znalazlem=true;
			
			int odleglosc=-1; 
			if(znalazlem){ // jezeli sie powtorzy strona
				odleglosc=j-indexBledu;
				if(odleglosc>max){
					index=i;
					max=odleglosc;
				}
			}//if znalazlem
			else{ // jezeli sie nie powtorzy
				index=i;
				break;
			}
		}
		
		 return index;
	}















	public void Fifo(){
		
		for(Odwolanie od: ciagOdwolan){
			if(!czyJestJuzWTablicy(od)){
				
			if(!wypelnionaTablicaRamek()){  //jezeli tablica ramek nie jest jeszcze zapelniona
				addLast(tablicaRamek, od);
			}
			else{		
			 removefirst(tablicaRamek);
			 addLast(tablicaRamek, od);
				}
			iloscBledow++;
			} //czy jest juz w tablicy	
		}//for
		System.out.println("liczba bledow stron " + iloscBledow);
		}
	
	public void FifoWithTimer(){
		
		Odwolanie victim=null;
		for(Odwolanie od: ciagOdwolan){
			if(!czyJestJuzWTablicy(od)){
				
				if(!wypelnionaTablicaRamek()){  //jezeli tablica ramek nie jest jeszcze zapelniona
				addLast(tablicaRamek, od);
				}
				else{ //zastepowanie ramek!
				victim=theOldestVictim();
				int index=tablicaRamek.indexOf(victim);
				tablicaRamek.remove(victim);
				tablicaRamek.add(index,od);
				}
				iloscBledow++;
			} // czy jest juz w tablicy
			dodajCzasWRamkach();
		} // for
		
		System.out.println("liczba bledow stron " + iloscBledow);
		}
	
	public void Rand(){
		Odwolanie victim=null;
		
		for(Odwolanie od: ciagOdwolan){
			if(!czyJestJuzWTablicy(od)){
				if(!wypelnionaTablicaRamek()){  //jezeli tablica ramek nie jest jeszcze zapelniona
					addLast(tablicaRamek, od);
				}
				else{
					int ind=rand.nextInt(tablicaRamek.size());
					victim=tablicaRamek.get(ind);
					tablicaRamek.remove(victim);
					tablicaRamek.add(ind,od);
				}
				iloscBledow++;
			}// czy jest juz w tablicy
		}//for 
		
		System.out.println("liczba bledow stron " + iloscBledow);	
		}
	
	public void Optimal(){
		int zegar=0;
		Odwolanie victim=null;
		for(Odwolanie od: ciagOdwolan){
			
			if(!czyJestJuzWTablicy(od)){
				if(!wypelnionaTablicaRamek()){  //jezeli tablica ramek nie jest jeszcze zapelniona
					addLast(tablicaRamek, od);
				}
				else{
					int indexOfVictim=findIndexOfOptimalVictim(zegar); //zegar jest potrzebny do znalezenia dlugosci do nastepnego wyst. od danej chwili
					victim=tablicaRamek.get(indexOfVictim);
					tablicaRamek.remove(indexOfVictim);
					tablicaRamek.add(indexOfVictim,od);	
				}
				iloscBledow++;	
			}// czy jest juz w tablicy
			zegar++;
		} //for
		
		System.out.println("liczba bledow stron " + iloscBledow);
		
		}
	
	
	
	public void LRU(){
		Odwolanie victim=null;
		for(Odwolanie od: ciagOdwolan){
			boolean usunal=false;
			Iterator<Odwolanie> iter= tablicaRamek.iterator();
			
			if(!wypelnionaTablicaRamek()){//jezeli tablica nie jest wypelniona	
					while(iter.hasNext()&&!usunal) //szukam czy element znajduje sie w tablicy
						if(iter.next().wartosc==od.wartosc){
						iter.remove();	
						usunal=true;
						}
					if(usunal)  //element wystepuje, trzeba go usunac(juz usuniety) i dodac na poczatek			
							addFirst(tablicaRamek, od);	
				
					else{ //tablica nie jest pelna wiec dodajemy na poczatek nowy element
							addFirst(tablicaRamek, od);
							iloscBledow++;
					}		
		} //if niewypelniona
			
			else{ //jezeli jest wypelniona
					while(iter.hasNext()&&!usunal)  //szukam czy element znajduje sie w tablicy
						if(iter.next().wartosc==od.wartosc){
							iter.remove();	
							usunal=true;
						}
					if(usunal)  //element wystepuje, trzeba go usunac(juz usuniety) i dodac na poczatek					
					addFirst(tablicaRamek, od);	
					
					else{ // tablica jest pelna, element nie wystepuje, trzeba znalezc ofiare, usunac ja, i element dodac na poczatek
						victim=RemoveLastVictim(tablicaRamek);
						addFirst(tablicaRamek,od);
						iloscBledow++;			
					}	
			}//else wypelniona	
		}// for
		System.out.println("liczba bledow stron " + iloscBledow);	
	}
	
	
	
	
	// http://edu.pjwstk.edu.pl/wyklady/sop/scb/wyklad8/wyklad.html
	public void aproksymowany(){
		Odwolanie victim=null;
		for(Odwolanie od: ciagOdwolan){
			boolean usunal=false;
			Iterator<Odwolanie> iter= tablicaRamek.iterator();
			
			if(!wypelnionaTablicaRamek()){//jezeli tablica nie jest wypelniona		
				while(iter.hasNext()&&!usunal) //szukam czy element znajduje sie w tablicy
					if(iter.next().wartosc==od.wartosc){
						iter.remove();	
						usunal=true;
					}
				if(usunal)  //element wystepuje, trzeba go usunac(juz usuniety) i dodac na koniec			
				addLast(tablicaRamek, od);	
				else{ //tablica nie jest pelna wiec dodajemy na koniec nowy element
					addLast(tablicaRamek, od);
					iloscBledow++;
				}
		}//nie jest wypelniona
		
		else{ // jezeli jest wypelniona
				
				while(iter.hasNext()&&!usunal)  //szukam czy element znajduje sie w tablicy
					if(iter.next().wartosc==od.wartosc){
						iter.remove();	
						usunal=true;
					}
				if(usunal)  //element wystepuje, trzeba go usunac(juz usuniety) i dodac na koniec				
				addLast(tablicaRamek, od);	
				else{ // tablica jest pelna, element nie wystepuje, trzeba znalezc ofiare, usunac ja, i element dodac na koniec
					iter=tablicaRamek.iterator(); //od nowa bo iterator przeszedl przed chwila
					victim=null;
					boolean czyJestBitNa0=false;
					Odwolanie odwol=null;
						while(iter.hasNext() &&!czyJestBitNa0){
							odwol=iter.next();
							if(odwol.bit2szansy==0)
							czyJestBitNa0=true;
						}
					
					if(!czyJestBitNa0){ //ustawWszystkimBitna0
						for(Odwolanie zm: tablicaRamek)
							zm.bit2szansy=0;
							victim=tablicaRamek.get(0); //ofiara jest pierwszy element
							removefirst(tablicaRamek); //usuwamy 1 element	
							addLast(tablicaRamek,od);
						}
					else{ //jezeli jest jakis bit na 0
						victim=odwol;	//pierwszy element z bitem na 0
						removeOdwolanie(tablicaRamek, victim);//usuwam ofiare
							addLast(tablicaRamek,od);
						}
						
			iloscBledow++;
				
					}//else znalezc ofiare	
			}//else wypelniona
			
		}// for
			
	System.out.println("liczba bledow stron " + iloscBledow);
			
	}
	
	
	
	
		
		
		
		
	
	



		
	
	/*
	int findOptimalVictim(int indexBledu){
		

		 HashMap<Integer,Integer> odleglosci = new HashMap<Integer,Integer>();
		 int index=-1;
	       

		for(int i=0; i< tablicaRamek.size();i++){
			
			boolean znalazlem=false;
			int j=indexBledu;
			for(;j<ciagOdwolan.size()-1&&!znalazlem;j++){ //sprawdzamy dla poszczegolnej strony z ramki kiedy sie powtorzy(czy w ogole)
				if(tablicaRamek.get(i).wartosc==ciagOdwolan.get(j).wartosc)
					znalazlem=true;
			}
			int odleglosc=Integer.MAX_VALUE;
			if(znalazlem)
				odleglosc=j-indexBledu;
			odleglosci.put(i, odleglosc);	
		}
		 ValueComparator bvc =  new ValueComparator(odleglosci);
		 TreeMap<Integer,Integer> sorted_map = new TreeMap<Integer,Integer>(bvc);
		 sorted_map.putAll(odleglosci);
		 index=sorted_map.firstKey(); //index ramki naszej ofiary
		 System.out.println("przed posortowaniem" +odleglosci);
		 return index;
	}
	*/

	
}
