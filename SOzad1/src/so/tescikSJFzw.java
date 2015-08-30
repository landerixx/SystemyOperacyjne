package so;

import java.util.ArrayList;

public class tescikSJFzw {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
	
		SJFzw s= new SJFzw();
		s.sjfzwAlgorithm(proc);
		System.out.println();
		
	}

}
