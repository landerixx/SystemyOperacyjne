package so;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Rotacyjny {

	int czasGlobalny = 0;
	int sumaOczekiwan = 0;
	int kwantczasu;
	int sumaCzasow;
	Rotacyjny(int kwant){
		kwantczasu=kwant;
	}
	
	ArrayList<Proces> skonczone= new ArrayList<Proces>();
	ArrayList<Proces> gotowe = new ArrayList<Proces>();
	ArrayList<Proces> Wejsciowa = new ArrayList<Proces>();
	
	void rotacyjnyAlgorithm(ArrayList<Proces> ciagProcesow){
		
		
		Wejsciowa=ciagProcesow;
		Proces aktualny=null;
		
		while(!Wejsciowa.isEmpty() || !gotowe.isEmpty()){
			
			szukajGotowych(aktualny); //szukamy gotowych i sortujemy po czasie zgloszenia (aktualny leci na koniec
										// bo byl przed chwila obslugiwany (jesli nie byl nullem)
			
			if(!gotowe.isEmpty()){
	//System.out.println("kolejka" + gotowe);
				aktualny=gotowe.get(0);
				aktualny.czasPozostaly-=kwantczasu;
				czasGlobalny+=kwantczasu;
				dodajCzasOczekiwania(aktualny);
				gotowe.remove(aktualny);
				if(aktualny.czasPozostaly<=0){
					aktualny.czasPozostaly=0;
					skonczone.add(aktualny);
				}
				else
					gotowe.add(aktualny);
				
			}
			else
			czasGlobalny++;
	//System.out.println("czas " +czasGlobalny);
		}
		sumaCzasow=sumaCzasu();
		/*
		System.out.println(sumaCzasow);
		System.out.println(sumaCzasow/skonczone.size());
			*/
		podsumowanko();
				
			
		
			
		
	}
	
	public  void szukajGotowych(Proces aktualny){
		Iterator<Proces> iter = Wejsciowa.iterator();
		Proces p = null;

		while (iter.hasNext()) {
			p = iter.next();
			if (p.getMomentZgloszenia() <= czasGlobalny) {
				gotowe.add(p);
				iter.remove();
			}
			if(aktualny!=null&&aktualny.czasPozostaly>0){
				gotowe.remove(aktualny);
				Collections.sort(gotowe);
				gotowe.add(aktualny);
			}

	}
	}
	
		void dodajCzasOczekiwania(Proces aktualny){
			for(Proces p:gotowe){
				if(p!=aktualny){
					if(p.czasPozostaly==p.dlugoscFazyProcesora)
						p.czasOczekiwania=czasGlobalny-p.momentZgloszenia;
					else
						p.czasOczekiwania+=kwantczasu;		
				}	
			}
			
		}
		
		int sumaCzasu(){
			int suma=0;
			for(Proces p:skonczone)
				suma+=p.czasOczekiwania;
			return suma;
			
			
		}
		
		
		
		public  void podsumowanko() {
			
			java.text.DecimalFormat df=new java.text.DecimalFormat(); //tworzymy obiekt DecimalFormat
			df.setMaximumFractionDigits(2); //dla df ustawiamy najwiêksz¹ iloœæ miejsc po przecinku
			df.setMinimumFractionDigits(2); //dla df ustawiamy najmniejsz¹ iloœæ miejsc po przecinku
			double sredniCzasOczekiwan=0.0;
			double doWariancji=0;
			
			System.out.println("ROTACYJNY  z kwantem " + kwantczasu);
			
			sredniCzasOczekiwan= (sumaCzasu()/(double)skonczone.size());
			
		//	System.out.println("Czas globalny: " +czasGlobalny);
			System.out.println("suma oczekiwan to: " + sumaCzasow);
			System.out.println("sredni czas oczekiwan to:"+ df.format(sredniCzasOczekiwan));
			System.out.println("Zakonczone procesy");
			
			for (Proces p : skonczone) {
				
				doWariancji+=(p.getCzasOczekiwania()-sredniCzasOczekiwan)*(p.getCzasOczekiwania()-sredniCzasOczekiwan);
		//	System.out.println(p);


			}
			
			System.out.println("Wariancja wynosi: " + df.format(doWariancji/skonczone.size()/1));
			System.out.println("Odchylenie standardowe wynosi: " + df.format(Math.sqrt(doWariancji/skonczone.size())));

			
			// czas oczekiwania max 
			Collections.sort(skonczone, new Comparator<Proces>() {
				public int compare(Proces os1, Proces os2) {
					return (int)( os2.getCzasOczekiwania() - os1.getCzasOczekiwania());
					
					
				}
			});
			
		//	System.out.println("Proces z najdluzszym czasem oczekiania: " + skonczoneProcesy.get(0));
		//	System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			
			
		}
		
	
	
	
	
	
	
	
	
	
	
}
