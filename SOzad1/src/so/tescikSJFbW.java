package so;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class tescikSJFbW {

	
	
	public static void main( String[] args){
		
		Proces e1=new Proces(1, 1, 5);
		Proces e2=new Proces(2, 5, 12);
		Proces e3=new Proces(3, 7, 15);
		Proces e4=new Proces(4, 5, 10);
		ArrayList<Proces> proc= new ArrayList<Proces>();
		proc.add(e1);
		proc.add(e2);
		proc.add(e3);
		proc.add(e4);
		System.out.println("Lista procesow na wejsciu");
		for(Proces p:proc)
			System.out.println(p);
		System.out.println();
	
		SJFbw s= new SJFbw();
		s.sjfbwAlgorithm(proc);
		System.out.println();
		
	
	
	Collections.sort(proc, new Comparator<Proces>() {
		public int compare(Proces os1, Proces os2) {
			return (int)( os1.getDlugoscFazyProcesora() - os2.getDlugoscFazyProcesora());		
		}
	});
	/*
	System.out.println("Lista procesow na wyjsciu");
	for(Proces p:proc)
		System.out.println(p);
	System.out.println();
	
	
	*/
	
	
	
	
	
	}
	
}
