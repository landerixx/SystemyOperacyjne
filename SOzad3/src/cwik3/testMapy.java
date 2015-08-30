package cwik3;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class testMapy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		 HashMap<Integer,Integer> odleglosci = new HashMap<Integer,Integer>();
	        ValueComparator bvc =  new ValueComparator(odleglosci);
	        TreeMap<Integer,Integer> sorted_map = new TreeMap<Integer,Integer>(bvc);

	     

	      


	       
		 
		 odleglosci.put(0, Integer.MAX_VALUE);
		 odleglosci.put(1, Integer.MAX_VALUE);
		 odleglosci.put(2, 10);
		 odleglosci.put(3, 13);
		 odleglosci.put(4, 11);
		   System.out.println("unsorted map: "+odleglosci);
		   sorted_map.putAll(odleglosci);
		   System.out.println("results: "+sorted_map);
		  
		 
		 
	}

}
