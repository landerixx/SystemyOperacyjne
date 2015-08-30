package SOzad4;

import java.util.ArrayList;
import java.util.Iterator;

public class LRU {

	
private static  Odwolanie RemoveLastVictim(ArrayList<Odwolanie> arraylista){
		Odwolanie od=arraylista.get(arraylista.size()-1);
		arraylista.remove(arraylista.size()-1);
		return od;
	}

private static  void  RemoveLast(ArrayList<Odwolanie> arraylista){
	
	arraylista.remove(arraylista.size()-1);
	
}
	
private static void addFirst(ArrayList<Odwolanie> arraylista,Odwolanie odwolanie){
		
		arraylista.add(0,odwolanie);
	}
	
	


 	public static void oneLoopLRU(Proces proces, int nrOdwolania){
 		
 		if(proces.przydzielonaIloscRamek==0){
			System.out.print("Proces ten nie dostal zadnej ramki     ");
			return;
		}
 		
 			Odwolanie daneOdwolanie = proces.ciagOdwolanProcesu.get(nrOdwolania);
			boolean usunal=false;
			Iterator<Odwolanie> iter= proces.tablicaRamekProcesu.iterator();
			
			
			
			if(!proces.wypelnionaTablicaRamek()){						//jezeli tablica nie jest wypelniona	
					while(iter.hasNext()&&!usunal) 						//szukam czy element znajduje sie w tablicy
						if(iter.next().wartosc==daneOdwolanie.wartosc){
						iter.remove();	
						usunal=true;
						}
						if(usunal) { //element wystepuje, trzeba go usunac(juz usuniety) i dodac na poczatek			
							addFirst(proces.tablicaRamekProcesu, daneOdwolanie);	
							proces.dodajBlad(0);
						}
				
					else{ //tablica nie jest pelna wiec dodajemy na poczatek nowy element
							addFirst(proces.tablicaRamekProcesu, daneOdwolanie);
							proces.iloscBledowStron++;
							proces.dodajBlad(1);
					}		
		} //if niewypelniona
			
			else{ //jezeli jest wypelniona
					while(iter.hasNext()&&!usunal)  //szukam czy element znajduje sie w tablicy
						if(iter.next().wartosc==daneOdwolanie.wartosc){
							iter.remove();	
							usunal=true;
						}
					if(usunal)  {//element wystepuje, trzeba go usunac(juz usuniety) i dodac na poczatek					
						addFirst(proces.tablicaRamekProcesu, daneOdwolanie);
						proces.dodajBlad(0);
					}
					else{ // tablica jest pelna, element nie wystepuje,  
						RemoveLast(proces.tablicaRamekProcesu); //usuniecie ofiary( ostatni element)
						addFirst(proces.tablicaRamekProcesu, daneOdwolanie); // i element dodac na poczatek
						proces.iloscBledowStron++;		
						proces.dodajBlad(1);
					}	
			}//else wypelniona	
			
			
		
 	}
 	
 	
	public static  void LRUalgorithm(Proces proces){
		if(proces.przydzielonaIloscRamek==0){
			System.out.print("Proces ten nie dostal zadnej ramki     ");
			return;
		}
		Odwolanie victim=null;
		for(Odwolanie od: proces.ciagOdwolanProcesu){
			boolean usunal=false;
			Iterator<Odwolanie> iter= proces.tablicaRamekProcesu.iterator();
			
			if(!proces.wypelnionaTablicaRamek()){//jezeli tablica nie jest wypelniona	
					while(iter.hasNext()&&!usunal) //szukam czy element znajduje sie w tablicy
						if(iter.next().wartosc==od.wartosc){
						iter.remove();	
						usunal=true;
						}
					if(usunal)  //element wystepuje, trzeba go usunac(juz usuniety) i dodac na poczatek			
							addFirst(proces.tablicaRamekProcesu, od);	
				
					else{ //tablica nie jest pelna wiec dodajemy na poczatek nowy element
							addFirst(proces.tablicaRamekProcesu, od);
							proces.iloscBledowStron++;
					}		
		} //if niewypelniona
			
			else{ //jezeli jest wypelniona
					while(iter.hasNext()&&!usunal)  //szukam czy element znajduje sie w tablicy
						if(iter.next().wartosc==od.wartosc){
							iter.remove();	
							usunal=true;
						}
					if(usunal)  //element wystepuje, trzeba go usunac(juz usuniety) i dodac na poczatek					
						addFirst(proces.tablicaRamekProcesu, od);
					
					else{ // tablica jest pelna, element nie wystepuje, trzeba znalezc ofiare, usunac ja, i element dodac na poczatek
						victim=RemoveLastVictim(proces.tablicaRamekProcesu);
						addFirst(proces.tablicaRamekProcesu, od);
						proces.iloscBledowStron++;		
					}	
			}//else wypelniona	
		}// for
		proces.skonczony=true;
		System.out.print("Bledy stron: --> " + proces.iloscBledowStron + "   ");
	}
	
		
	
	
}
