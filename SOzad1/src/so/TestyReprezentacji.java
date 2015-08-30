package so;

import java.util.ArrayList;

public class TestyReprezentacji {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ArrayList<Proces> array=Test.generujCiag();
		for(Proces a:array)
			System.out.println(a);
		
		System.out.println();
		System.out.println();
		System.out.println("wiecej krotszych");
		 array=Test.generujCiag1();
			for(Proces a:array)
				System.out.println(a);
		
	}

}
