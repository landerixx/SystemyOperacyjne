package so;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;



public class SJFzw {

	int czasGlobalny = 0;
	int sumaOczekiwan = 0;
	ArrayList<Proces> skonczoneProcesy = new ArrayList<Proces>();
	ArrayList<Proces> procesyGotowe = new ArrayList<Proces>();
	ArrayList<Proces> reprezentacjaWejsciowa = new ArrayList<Proces>();

	public void sjfzwAlgorithm(ArrayList<Proces> ciagProcesow) {

		reprezentacjaWejsciowa = ciagProcesow;
		Proces aktualny=null;
		
		while (!reprezentacjaWejsciowa.isEmpty() || !procesyGotowe.isEmpty()) {
			
			
			szukajGotowychProcesow(czasGlobalny);
			
			if(!procesyGotowe.isEmpty()){
			//sortuje po czasie jaki im pozostal do wykonania
			Collections.sort(procesyGotowe, new Comparator<Proces>() {
				public int compare(Proces os1, Proces os2) {
					return ( os1.getCzasPozostaly() - os2.getCzasPozostaly());	
							
				}
			});
			
			aktualny=procesyGotowe.get(0); //najkrotszy
			
			
			if (aktualny.getDlugoscFazyProcesora() == aktualny.getCzasPozostaly()) //jezeli pierwszy raz go obsluguje
				aktualny.czasOczekiwania = czasGlobalny - aktualny.getMomentZgloszenia(); //dodaje czas oczekiwania
			
			
			
			aktualny.czasPozostaly--;
			dodajCzasOczekiwan(aktualny); // czas oczekiwania dla procesow w kolejce procz aktualnego
			procesyGotowe.remove(aktualny); //usuwamy go z 1 miejsca
			
			if (aktualny.czasPozostaly <= 0) 
				skonczoneProcesy.add(aktualny); // jezeli sie wykonal to dodajemy do skonczonych
				
			 else 
				procesyGotowe.add(aktualny); //jezeli nie to na koniec 
						
			}//if czy kolejka jest pusta
			
/*
			System.out.println("kolejka " + procesyGotowe);
			System.out.println("czas globalny " + czasGlobalny);
	*/		
				czasGlobalny++;
			//	System.out.println(aktualny);
				
					
			

		}
		sumaOczekiwan = sumaOczekiwan();
		podsumowanie();

	}
/*
	Proces wybierzNajkrotszy(){
	
	Iterator<Proces> iter=procesyGotowe.iterator();
	Proces p=null;
	Proces najkrotszy=null;
	int min=Integer.MAX_VALUE;
	while(iter.hasNext()){
	p=iter.next();	
		if(p.czasPozostaly<min){
			min=p.czasPozostaly;
			najkrotszy=p;
	}
	}
	
	return p;
	
	}

*/
	
	public Proces wybierzNajkrotszy() {
		Proces p = procesyGotowe.get(0);
		for(int i = 0; i < procesyGotowe.size(); i++)
			if(procesyGotowe.get(i).getCzasPozostaly() < p.getCzasPozostaly())
				p = procesyGotowe.get(i);
		
		return p;
	}
	public void dodajCzasOczekiwan(Proces p) {
		for (int i = 0; i < procesyGotowe.size(); i++) {
			if (procesyGotowe.get(i) != p)
				procesyGotowe.get(i).czasOczekiwania++;
						
						

		}
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
		double sredniCzasOczekiwan = 0.0;
		double doWariancji = 0;

		sredniCzasOczekiwan = (sumaOczekiwan / (double) skonczoneProcesy.size());
		System.out.println("SJF z wywlaszczeniem");
		// System.out.println("Czas globalny: " +czasGlobalny);
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
				return (int) (os2.getCzasOczekiwania() - os1
						.getCzasOczekiwania());

			}
		});

	//	System.out.println("Proces z najdluzszym czasem oczekiania: "
	//			+ skonczoneProcesy.get(0));

		System.out.println();
		System.out.println();
		

	}

}