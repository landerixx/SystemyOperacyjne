package zad;

import java.util.ArrayList;



public class testReprezentacji {
	
	
	public static void testPrzydzielRamkiProporcjonalnie(){
		
		int iloscProcesow=5;
		int minIloscOdwolan=50;
		int maxIloscOdwolan=300;
		int iloscRamek=30;
		ArrayList<Proces> procesy= ReprezentacjaProcesow.generujRandCiagProcesowLokalnoscPrzylegla
				(iloscProcesow, minIloscOdwolan, maxIloscOdwolan);
		
		Algorithms instance= new Algorithms(procesy, iloscRamek);
		instance.wyswietlProcesy();
		instance.przydzielRamkiProporcjonalnie();
		instance.wyswietlProcesy();
		
		
	}

	
	public static void testLruKsiazka(){
		ArrayList<Odwolanie> ciagzKsiazki= new ArrayList<Odwolanie>();
		Odwolanie d1=new Odwolanie(7);  ciagzKsiazki.add(d1);
		Odwolanie d2=new Odwolanie(0); ciagzKsiazki.add(d2);
		Odwolanie d3=new Odwolanie(1); ciagzKsiazki.add(d3);
		Odwolanie d4=new Odwolanie(2); ciagzKsiazki.add(d4);
		Odwolanie d5=new Odwolanie(0); ciagzKsiazki.add(d5);
		Odwolanie d6=new Odwolanie(3); ciagzKsiazki.add(d6);
		Odwolanie d7=new Odwolanie(0); ciagzKsiazki.add(d7);
		Odwolanie d8=new Odwolanie(4); ciagzKsiazki.add(d8);
		Odwolanie d9=new Odwolanie(2); ciagzKsiazki.add(d9);
		Odwolanie d10=new Odwolanie(3); ciagzKsiazki.add(d10);
		Odwolanie d11=new Odwolanie(0); ciagzKsiazki.add(d11);
		Odwolanie d12=new Odwolanie(3); ciagzKsiazki.add(d12);
		Odwolanie d13=new Odwolanie(2); ciagzKsiazki.add(d13);
		Odwolanie d14=new Odwolanie(1); ciagzKsiazki.add(d14);
		Odwolanie d15=new Odwolanie(2); ciagzKsiazki.add(d15);
		Odwolanie d16=new Odwolanie(0); ciagzKsiazki.add(d16);
		Odwolanie d17=new Odwolanie(1); ciagzKsiazki.add(d17);
		Odwolanie d18=new Odwolanie(7); ciagzKsiazki.add(d18);
		Odwolanie d19=new Odwolanie(0); ciagzKsiazki.add(d19);
		Odwolanie d20=new Odwolanie(1); ciagzKsiazki.add(d20);
		//System.out.println(ciagzKsiazki);
		
		Proces panProces = new Proces(ciagzKsiazki);
		System.out.println(panProces);
		
		ArrayList<Proces> procesy=new ArrayList<Proces>();
		procesy.add(panProces);
		
		Algorithms instance= new Algorithms(procesy, 3);
		instance.wyswietlProcesy();
		instance.przydzielRamkiRowno();
		instance.wyswietlProcesy();
		instance.equalAllocation();
		
	}
	
	
	
	
	
	public static void testOdwolanLokalonscPromien(){
		
		int ileOdwolan=1000;
		int zakresOdwolania=1000;
		int promienLokalnosci=10;
		
		ArrayList<Odwolanie> ciag= new ArrayList<Odwolanie>();
		ciag=ReprezentacjaOdwolan.lokalnoscPromien(ileOdwolan, zakresOdwolania, promienLokalnosci);
		int i=0;
		for(Odwolanie od: ciag){
			i++;
		System.out.println(i + "  " +od);
		}
		
		}
	
	
	public static void testLRUdlaKilkuProcesow(){
		int iloscProcesow=10;
		int minIloscOdwolan=30;
		int maxIloscOdwolan=40;
		int PromienLokalnosci=10;
		int iloscRamekdla1Procesu=5;
		
		ArrayList<Proces> procesy= new ArrayList<Proces>();
		procesy=ReprezentacjaProcesow.generujRandCiagProcesowR
				(iloscProcesow, minIloscOdwolan, maxIloscOdwolan, PromienLokalnosci);
		
		ReprezentacjaProcesow.wyswietl(procesy);
		ReprezentacjaProcesow.przydzielZadanaIloscRamek(procesy, iloscRamekdla1Procesu);
		
		for(Proces p: procesy){
			LRU.LRUalgorithm(p);
			System.out.println("   dla Procesu:" + p);
		
		}
		}
	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	/*
		Proces p1= new Proces(1000, 1000, 10);
		System.out.println(p1);
		*/
		
	
	//testLRUdlaKilkuProcesow();
		//testOdwolanLokalonscPromien();
	//	testLruKsiazka();
		
		testPrzydzielRamkiProporcjonalnie();
		
		
		
	
		}
	

}
