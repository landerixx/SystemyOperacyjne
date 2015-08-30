package zad1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Procesor implements Serializable {

	int id;
	int aktualneObciazenieProcesora; // domyslnie w % max 100
	ArrayList<Proces> procesyWykonywane;
	int zegar;
	
	ArrayList<Proces> doWykonania;
	int iloscProcesow;
	
	ArrayList<Integer> obciazenia;
	private ArrayList<Proces> doSymulacji;
	Queue<Proces> kolejkaProcesow;
	
	int srednieObciazenie(){
		int srednia=0;
		for(int ob: obciazenia)
			srednia+=ob;
		srednia/=obciazenia.size();
		return srednia;
		
	}
	
	void clear(){
		zegar=0;
		aktualneObciazenieProcesora=0;
		procesyWykonywane=new ArrayList<Proces>();
		doWykonania=cloneList(doSymulacji);
		kolejkaProcesow=new LinkedList<Proces>();
		obciazenia=new ArrayList<Integer>();
	}
	
	
	public static ArrayList<Proces> cloneList(ArrayList<Proces> list) {
	    ArrayList<Proces> clone = new ArrayList<Proces>(list.size());
	    for(Proces item: list) clone.add(item.clone());
	    return clone;
	}
	
	
	public Procesor(int id) {
		aktualneObciazenieProcesora=0;
		procesyWykonywane= new ArrayList<Proces>();
		zegar=0;
		this.id=id;
		doWykonania= ReprezentacjaProcesow.generujProcesy(20);
		iloscProcesow=doWykonania.size();
		
		obciazenia=new ArrayList<Integer>();
		kolejkaProcesow=new LinkedList<Proces>();

		doSymulacji=cloneList(doWykonania);
		
		
	}
	
	public Procesor(int id, ArrayList<Proces> procesy) {
		aktualneObciazenieProcesora=0;
		procesyWykonywane= new ArrayList<Proces>();
		zegar=0;
		this.id=id;
		doWykonania= procesy;
		iloscProcesow=doWykonania.size();
		
		obciazenia=new ArrayList<Integer>();
		kolejkaProcesow=new LinkedList<Proces>();

		doSymulacji=cloneList(doWykonania);
		
		
	}
	
	
	
	public Procesor(int id, int maxIloscProcesow) {
		Random rand= new Random();
		aktualneObciazenieProcesora=0;
		procesyWykonywane= new ArrayList<Proces>();
		zegar=0;
		this.id=id;
		doWykonania= ReprezentacjaProcesow.generujProcesy(1+rand.nextInt(maxIloscProcesow));
		iloscProcesow=doWykonania.size();
		
		
		obciazenia=new ArrayList<Integer>();
		kolejkaProcesow=new LinkedList<Proces>();

		doSymulacji=cloneList(doWykonania);
	}

	

	boolean czyProcesorDziala(){
		return !procesyWykonywane.isEmpty();
	}
	
	boolean czyCosWKolejce(){
		return !kolejkaProcesow.isEmpty();
	}
	
	boolean czySaDoWykonania(){
		return !doWykonania.isEmpty();
	}
	
	
	
	void dodajDoKolejkiProcesy(int time){
		
		Iterator<Proces> iter= doWykonania.iterator();
		Proces p=null;
		while(iter.hasNext()){
			p=iter.next();
			if ( p.czasPojawienia==time){
				kolejkaProcesow.add(p);
				iter.remove();
			
			}	
		}
		
	}
		
		public int stanObciazenia(){	
			return aktualneObciazenieProcesora;
		}
		
		
		void wykonajProcesy(){
			
			Iterator<Proces> iter= procesyWykonywane.iterator();
			Proces p=null;
			while(iter.hasNext()){
				p=iter.next();
				p.pozostalo--;
				if ( p.pozostalo==0){
				this.aktualneObciazenieProcesora-=p.obciazenie;	//odejmij obciazenie
					iter.remove();
				
				}
			}
		}
		
		
		void pobierzObciazenie(int okres) throws RuntimeException{
			
			if(zegar%okres==0){
				if(this.aktualneObciazenieProcesora!=0){
					
				if(aktualneObciazenieProcesora>99)
					throw new RuntimeException("w procesorze" + this.toString() + "obciazenie powyzej 100, pozdrawiam");
				
					obciazenia.add(aktualneObciazenieProcesora);
			
				}
			}
			
		}
		
		// znajdz proces +-5 od wyliczonego parametru i usun go z wykonywanych
		
		Proces findOptimalProcess(int parametr){
			
			Proces optimal=null;
			Iterator<Proces> iter= procesyWykonywane.iterator();
			Proces p=null;
			while(iter.hasNext()){
				p=iter.next();
				if((p.obciazenie<(parametr+5)) && (p.obciazenie> (parametr-5))){
				optimal=p;
				this.aktualneObciazenieProcesora-=p.obciazenie;
				iter.remove();		
				}
			}
			
			
			return optimal;
			
		}
	
	
	
	
	
		
		
		void podsumowanie(){
			
			System.out.print("zebralem nastepujace wartosci obciazen dla procesora nr " + this.id + "   : ");
		
			for(Integer ob: obciazenia)
				System.out.print(ob+ ",  " );
			System.out.println();
			
			
			
		}
	
	
	
	
	
	
	
	

	@Override
	public String toString() {
		return "Procesor [id=" + id +", iloscProcesow=" + iloscProcesow
				+   ", doWykonania=" + doWykonania+ "]";
	}
	
	




	
	
	
	
	
	
	
	
	
}
