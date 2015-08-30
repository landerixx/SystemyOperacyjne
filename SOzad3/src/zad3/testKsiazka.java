package zad3;

import java.util.ArrayList;

public class testKsiazka {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		ArrayList<Odwolanie> lista= new ArrayList<Odwolanie>();
		Odwolanie od0=new Odwolanie(1); lista.add(od0);
		Odwolanie od1=new Odwolanie(2); lista.add(od1);
		Odwolanie od2=new Odwolanie(3); lista.add(od2);
		Odwolanie od3=new Odwolanie(4); lista.add(od3);
		Odwolanie od4=new Odwolanie(1); lista.add(od4);
		Odwolanie od5=new Odwolanie(2); lista.add(od5);
		Odwolanie od6=new Odwolanie(5); lista.add(od6);
		Odwolanie od7=new Odwolanie(1); lista.add(od7);
		Odwolanie od8=new Odwolanie(2); lista.add(od8);
		Odwolanie od9=new Odwolanie(3); lista.add(od9);
		Odwolanie od10=new Odwolanie(4); lista.add(od10);
		Odwolanie od11=new Odwolanie(5); lista.add(od11);
		
	System.out.println(lista);
	Algorytmy al= new Algorytmy(3, lista); //3 ramki
	al.Fifo();
	Algorytmy al1= new Algorytmy(4, lista); // 4 ramki
	al1.Fifo();
		System.out.println("anomalia beladyego!");
	*/
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
	System.out.println(ciagzKsiazki);
	Algorytmy al2= new Algorytmy(3, ciagzKsiazki); 
	
	System.out.println("algorytm optymalny");
	al2.Optimal();
	System.out.println();
	al2.clearTablicaRamek();
	
	
	System.out.println("algorytm lru");
	al2.LRU();
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
	System.out.println();
	al2.clearTablicaRamek();
	
	
	System.out.println("algorytm aproksymowany");
	al2.aproksymowany();
	System.out.println();
	al2.clearTablicaRamek();
	
	
}
}