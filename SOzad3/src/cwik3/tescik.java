package cwik3;

import java.util.ArrayList;
import java.util.Random;

public class tescik {

	public static void addFirst(ArrayList<Odwolanie> arraylista,Odwolanie odwolanie){
		
		arraylista.add(0,odwolanie);
	}
	public static void addLast(ArrayList<Odwolanie> arraylista, Odwolanie odwolanie){
		arraylista.add(odwolanie);
		
	}
	public static void removeLast(ArrayList<Odwolanie> arraylista){
		
		arraylista.remove(arraylista.size()-1);
	}
	public static void removefirst(ArrayList<Odwolanie> arraylista){
		
		arraylista.remove(0);
	}
	public static void removeRandom(ArrayList<Odwolanie> arraylista){
		Random rand= new Random();
		int ind= rand.nextInt(arraylista.size());
		arraylista.remove(ind);
	}

	public static Odwolanie OdwolanieRemoveRandom(ArrayList<Odwolanie> arraylista){
		Random rand= new Random();
		int ind= rand.nextInt(arraylista.size());
		Odwolanie od= arraylista.get(ind);
		arraylista.remove(ind);
		return od;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ArrayList<Odwolanie> lista=new ArrayList<Odwolanie>();
		Odwolanie o1= new Odwolanie(1);
		Odwolanie o2= new Odwolanie(2);
		Odwolanie o3= new Odwolanie(3);
		Odwolanie o4= new Odwolanie(4);
		
		lista.add(0,o1);
		lista.add(0,o2);
		lista.add(0,o3);
		lista.add(0,o4);
		
		System.out.println(lista);
		lista.remove(lista.size()-1);
		System.out.println(lista);
		
		
		System.out.println();
		
		ArrayList<Odwolanie> lista1=new ArrayList<Odwolanie>();
		addFirst(lista1, o1);
		addFirst(lista1, o2);
		addFirst(lista1, o3);
		addLast(lista1, o4);		
		System.out.println(lista1);
		removeLast(lista1);
		System.out.println(lista1);
		
		//removeRandom(lista1);
		System.out.println(lista1);

		
	}

}
