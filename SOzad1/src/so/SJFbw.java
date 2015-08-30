package so;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;



public class SJFbw{

	int czasGlobalny = 0;
	int sumaOczekiwan = 0;
	ArrayList<Proces> skonczoneProcesy = new ArrayList<Proces>();
	ArrayList<Proces> procesyGotowe = new ArrayList<Proces>();
	ArrayList<Proces> reprezentacjaWejsciowa = new ArrayList<Proces>();

	public void sjfbwAlgorithm(ArrayList<Proces> ciagProcesow) {

	
		reprezentacjaWejsciowa = ciagProcesow;
	

		while (!reprezentacjaWejsciowa.isEmpty() || !procesyGotowe.isEmpty()) {
			szukajGotowychProcesow(czasGlobalny); // szukamy procesow gotowych
													// do wejscia do kolejki

			if (procesyGotowe.size() > 0) { // jezeli cos jest w kolejce
				 // sortujemy procesy Gotowe po wielkosci 
				Collections.sort(procesyGotowe, new Comparator<Proces>() {
					public int compare(Proces os1, Proces os2) {
						return (int)( os1.getDlugoscFazyProcesora() - os2.getDlugoscFazyProcesora());	
						
						
					}
				});
				
				Proces aktualny = procesyGotowe.get(0);

				aktualny.SetCzasOczekiwania(czasGlobalny
						- aktualny.getMomentZgloszenia());
				czasGlobalny += aktualny.getDlugoscFazyProcesora();
				
/*
				System.out.println("kolejka: " + procesyGotowe);
				System.out.println("czas globalny: " + czasGlobalny);
				System.out.println();
	*/			
				
				skonczoneProcesy.add(aktualny);
				procesyGotowe.remove(aktualny);

			} else
				czasGlobalny++;

		}
	

		
		
		
		sumaOczekiwan = sumaOczekiwan();
		podsumowanie();

	}
	
	
	
	

	public void szukajGotowychProcesow(int czasGlobalny) {
		Iterator<Proces> iter = reprezentacjaWejsciowa.iterator();
		Proces p = null;

		while (iter.hasNext()) {
			p = iter.next();
			if (p.getMomentZgloszenia() <= czasGlobalny) {
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

	
	
	
	
	
	
	
	public void podsumowanie() {
		
		java.text.DecimalFormat df=new java.text.DecimalFormat(); //tworzymy obiekt DecimalFormat
		df.setMaximumFractionDigits(2); //dla df ustawiamy najwiêksz¹ iloœæ miejsc po przecinku
		df.setMinimumFractionDigits(2); //dla df ustawiamy najmniejsz¹ iloœæ miejsc po przecinku
	
		
		double sredniCzasOczekiwan=0.0;
		double doWariancji=0;
		
		
		
		sredniCzasOczekiwan= (sumaOczekiwan/(double)skonczoneProcesy.size());
		System.out.println("SJF bez wywlaszczenia");
		//System.out.println("Czas globalny: " +czasGlobalny);
		System.out.println("suma oczekiwan to: " + sumaOczekiwan);
		System.out.println("sredni czas oczekiwan to:"+ df.format(sredniCzasOczekiwan));
	//	System.out.println("Zakonczone procesy");
		
		for (Proces p : skonczoneProcesy) {
			
			doWariancji+=(p.getCzasOczekiwania()-sredniCzasOczekiwan)*(p.getCzasOczekiwania()-sredniCzasOczekiwan);
		//	System.out.println(p);


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
		
	System.out.println();
		System.out.println();
	
		
	}

}