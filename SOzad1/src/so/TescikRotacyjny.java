package so;

import java.util.ArrayList;

public class TescikRotacyjny {

	public static void main(String[] args){
		
		Rotacyjny r= new Rotacyjny(10);
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
	
		
		r.rotacyjnyAlgorithm(proc);
		System.out.println();
		
		
	}
}
