package zad3;

import java.util.ArrayList;

public class testReprezentacji {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Odwolanie> ciag= new ArrayList<Odwolanie>();
		ciag=ReprezentacjaOdwolan.lokalnosc(1000, 10);
		for(Odwolanie od: ciag)
		System.out.println(od);
	}

}
