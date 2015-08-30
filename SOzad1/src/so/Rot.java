package so;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Rot{

	int czasGlobalny = 0;
	int sumaOczekiwan = 0;
	int kwantczasu;
	Rot(int kwant){
		kwantczasu=kwant;
	}
	
	ArrayList<Proces> skonczoneProcesy = new ArrayList<Proces>();
	ArrayList<Proces> procesyGotowe = new ArrayList<Proces>();
	ArrayList<Proces> reprezentacjaWejsciowa = new ArrayList<Proces>();

	public void rotAlgorithm(ArrayList<Proces> ciagProcesow) {

	
		reprezentacjaWejsciowa = ciagProcesow;
		
		Proces aktualny=null;
		int licznikKwantu=kwantczasu;
		while (!reprezentacjaWejsciowa.isEmpty() || !procesyGotowe.isEmpty()) {
			
			szukajGotowychProcesow(czasGlobalny); // szukamy procesow gotowych
													// do wejscia do kolejki
				
	//	System.out.println("kolejka: " + procesyGotowe);	
			if (procesyGotowe.size() > 0) {	
				 aktualny = procesyGotowe.get(0); //pierwszy co wszedl
				 
				 if(licznikKwantu!=0 && aktualny.getCzasPozostaly()!=0){
				 aktualny.setCzasPozostaly(aktualny.getCzasPozostaly()-1); //czaspozostaly--
				 licznikKwantu--;
				 dodajCzasOczekiwan();// dla procesow gotowych dodaj czas oczekiwania (procz aktualnego)
				 }
				 
				 
				 			// kwant czasu skonczyl sie dla procesu, ktory jeszcze nie zostal obsluzony do konca
				 if(licznikKwantu==0 &&aktualny.getCzasPozostaly()!=0){
					 licznikKwantu=kwantczasu;  //kwant doszedl do 0 
	//	System.out.println("kwant sie skonczyl"); 
	                procesyGotowe.remove(aktualny); //skonczyl sie okres wykonywania na procesorze
	                procesyGotowe.add(aktualny); // wyrzuc go na koniec kolejki
				 }	 			
				 
			 }		//if kolejka niepusta
			
			czasGlobalny++;
	//	 System.out.println("czas globalny: " + czasGlobalny);
	//	 System.out.println();		
			
			 if(aktualny !=null &&aktualny.getCzasPozostaly()==0){               
			  skonczoneProcesy.add(aktualny);
                procesyGotowe.remove(aktualny);
           	 licznikKwantu=kwantczasu;  // aktualny sie skonczyl, trzeba nowy kwant    
           	 aktualny=null;
			 	}		 			 
		}  //while
			
			sumaOczekiwan = sumaOczekiwan();
			podsumowanko();	
			
		}	
			 
		
	


	
	
	
	public void dodajCzasOczekiwan(){
		for(int i=1;i<procesyGotowe.size();i++)
			procesyGotowe.get(i).czasOczekiwania++;
	}
	
	
	

	public void szukajGotowychProcesow(int czasGlobalny) {
		Iterator<Proces> iter = reprezentacjaWejsciowa.iterator();
		Proces p = null;

		while (iter.hasNext()) {
			p = iter.next();
			if (p.getMomentZgloszenia() == czasGlobalny) {
				procesyGotowe.add(p);
				iter.remove();
			}

		}

	}

	public int sumaOczekiwan() {
		int suma = 0;
		for (Proces p : skonczoneProcesy)
			suma += p.getCzasOczekiwania();
		return suma;
	}

	
	
	
	
	
	
	
	public  void podsumowanko() {
		
		java.text.DecimalFormat df=new java.text.DecimalFormat(); //tworzymy obiekt DecimalFormat
		df.setMaximumFractionDigits(2); //dla df ustawiamy najwiêksz¹ iloœæ miejsc po przecinku
		df.setMinimumFractionDigits(2); //dla df ustawiamy najmniejsz¹ iloœæ miejsc po przecinku
		double sredniCzasOczekiwan=0.0;
		double doWariancji=0;
		
		System.out.println("ROTACYJNY ROT z kwantem " + kwantczasu);
		
		sredniCzasOczekiwan= (sumaOczekiwan()/(double)skonczoneProcesy.size());
		
	//	System.out.println("Czas globalny: " +czasGlobalny);
		System.out.println("suma oczekiwan to: " + sumaOczekiwan);
		System.out.println("sredni czas oczekiwan to:"+ df.format(sredniCzasOczekiwan));
	//	System.out.println("Zakonczone procesy");
		
		for (Proces p : skonczoneProcesy) {
			
			doWariancji+=(p.getCzasOczekiwania()-sredniCzasOczekiwan)*(p.getCzasOczekiwania()-sredniCzasOczekiwan);
	//		System.out.println(p);


		}
		
		System.out.println("Wariancja wynosi: " + df.format(doWariancji/skonczoneProcesy.size()/1));
		System.out.println("Odchylenie standardowe wynosi: " + df.format(Math.sqrt(doWariancji/skonczoneProcesy.size())));

		
		// czas oczekiwania max 
		Collections.sort(skonczoneProcesy, new Comparator<Proces>() {
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