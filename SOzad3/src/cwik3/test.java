package cwik3;

import java.io.IOException;
import java.util.ArrayList;


public class test {

	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		
		/*
		ArrayList<Odwolanie> ciagOdwolan= new ArrayList<Odwolanie>();
		ciagOdwolan=ReprezentacjaOdwolan.generujCiag(10, 4);
		System.out.println(ciagOdwolan);
		String fileName="plik";
	//	ReprezentacjaOdwolan.save(fileName, 10,4); //zapisalo sie do pliku
		ArrayList<Odwolanie> zPliku= new ArrayList<Odwolanie>();
		zPliku=ReprezentacjaOdwolan.restore(fileName);
		System.out.println("z pliku");
		System.out.println(zPliku);
		*/
		
		  
		 /*
		Algorytmy al= new Algorytmy(10, 600, 11);
		//int i=0;
		System.out.println(al.ciagOdwolan);
		System.out.println();
		al.Fifo();
		*/
		
		
		
		
		
		int iloscOdwolan=100;
		int iloscRamek=3;
		int zakresOdwolan=15;
		
		String fileName="pliczek";
	//ReprezentacjaOdwolan.save(fileName, iloscOdwolan,zakresOdwolan); //zapisalo sie do pliku
		ArrayList<Odwolanie> zPliku= new ArrayList<Odwolanie>();
		zPliku=ReprezentacjaOdwolan.restore(fileName);
	
	//	System.out.println(zPliku);
		for(Odwolanie od:zPliku)
			System.out.print(od.wartosc +" ");
		System.out.println();
		
		Algorytmy al2= new Algorytmy(iloscRamek,zPliku);
		
		System.out.println("z pliku");
		System.out.println("ilosc ramek: " + iloscRamek + "  ilosc odwolan: " + iloscOdwolan + "  zakres odwolan: " + zakresOdwolan);
		
		System.out.println("algorytm optymalny");
		al2.Optimal();
		System.out.println();
		al2.clearTablicaRamek();
		
		
		System.out.println("algorytm lru");
		al2.LRU();
		System.out.println();
		al2.clearTablicaRamek();
		
		System.out.println("algorytm aproksymowany");
		al2.aproksymowany();
		System.out.println();
		al2.clearTablicaRamek();
		
		System.out.println("algorytm fifo");
		al2.Fifo();
		System.out.println();
		al2.clearTablicaRamek();
		
		
		System.out.println("algorytm fifowithtimer");
		al2.FifoWithTimer();
		al2.clearTablicaRamek();
		
		System.out.println("algorytm rand");
		al2.Rand();
		
		
	}
}
