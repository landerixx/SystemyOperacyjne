package so;

import java.util.ArrayList;

public class tescikRot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rot r= new Rot(10);
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
	
		
		r.rotAlgorithm(proc);
		System.out.println();
		
		
		
		
		/*
		ArrayList<Proces> generuje=Test.generujCiag();
		System.out.println("Lista procesow na wejsciu");
		for(Proces p:generuje)
			System.out.println(p);
		System.out.println();
		r.rotAlgorithm(generuje);
		
		*/
		/*
		Proces z1=new Proces(1, 0, 21);
		Proces z2=new Proces(2, 0, 6);
		Proces z3=new Proces(3, 0, 3);
		ArrayList<Proces> proce= new ArrayList<Proces>();
		proce.add(z1);
		proce.add(z2);
		proce.add(z3);
		r.rotAlgorithm(proce);
		*/
	}

}
