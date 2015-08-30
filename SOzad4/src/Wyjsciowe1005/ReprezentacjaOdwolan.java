package Wyjsciowe1005;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;





public class ReprezentacjaOdwolan {

	
	public static ArrayList<Odwolanie> generujCiag(int ileOdwolan, int zakresOdwolania){
		Random rand=new Random();
		ArrayList<Odwolanie> ciagOdwolan= new ArrayList<Odwolanie>();
		for(int i=0; i<ileOdwolan;i++)
			ciagOdwolan.add(new Odwolanie(rand.nextInt(zakresOdwolania)));
		return ciagOdwolan;
		
	}
	
	
	
	public static ArrayList<Odwolanie> lokalnosc(int ileOdwolan, int zakresOdwolania){
		Random rand=new Random();
		ArrayList<Odwolanie> ciagOdwolan= new ArrayList<Odwolanie>();
		int wartosc= rand.nextInt(zakresOdwolania);
		int prawdopodobienstwo=9;
		for(int i=0; i<ileOdwolan;i++){	
			int doPrawdop= rand.nextInt(10)+1;
			
			if(doPrawdop<=prawdopodobienstwo){ // pr=9 =>90% na lokalnosc odwolan -> wygeneruje odwolanie przylegle +-1 od aktualnej wartosci
				
				if(wartosc==0)//warunek brzegowy jak wartosc=0 to odwolanie bedzie 0 lub 1
					ciagOdwolan.add(new Odwolanie(rand.nextInt(2)));	
				
				else //odwolania przylegle, czyli +-1: wartosc-1 lub wartosc lub wartosc+1
					ciagOdwolan.add(new Odwolanie((wartosc-1)+rand.nextInt(3)));	
						
			} //if prawdop
			else{
				wartosc= rand.nextInt(zakresOdwolania);
			ciagOdwolan.add(new Odwolanie(wartosc));
			}	
		}//for		
		return ciagOdwolan;	
	}
	
	
	public static ArrayList<Odwolanie> lokalnoscPromien(int ileOdwolan, int zakresOdwolania, int promienLokalnosci){
		Random rand=new Random();
		ArrayList<Odwolanie> ciagOdwolan= new ArrayList<Odwolanie>();
		int wartosc= rand.nextInt(zakresOdwolania);
		int prawdopodobienstwo=9;
		
		for(int i=0; i<ileOdwolan;i++){	
			int doPrawdop= rand.nextInt(10)+1;
			
			if(doPrawdop<=prawdopodobienstwo){ // pr=9 =>90% na lokalnosc odwolan -> wygeneruje odwolanie z  +- promien od aktualnej wartosci
				
				if(wartosc<promienLokalnosci)//warunek brzegowy jak wartosc< promienia to odwolanie bedzie tylko + promien
					ciagOdwolan.add(new Odwolanie( wartosc + rand.nextInt(promienLokalnosci) 	));	
				
				else //odwolania przylegle, czyli +-promien: wartosc-promien + rand z promien + rand(inny) z promien
					ciagOdwolan.add(new Odwolanie( (wartosc-promienLokalnosci)+rand.nextInt(promienLokalnosci) +rand.nextInt(promienLokalnosci) ));	
						
			} //if prawdop
			else{
				
				wartosc= rand.nextInt(zakresOdwolania);
			ciagOdwolan.add(new Odwolanie(wartosc));
			}	
		
		}//for		
		
		return ciagOdwolan;	
	}
	
	
	
	
	
	
	
	public static void save(String plik, ArrayList<Odwolanie> ciag) throws IOException{
		ArrayList<Odwolanie> listek= new ArrayList<Odwolanie>();
		listek=ciag;
		
		ObjectOutputStream wyj= new ObjectOutputStream(new FileOutputStream(plik));
		wyj.writeObject(listek);
		wyj.close();
	}

	
	public static void save(String plik, int ileOdwolan, int zakresOdwolania) throws IOException{
		ArrayList<Odwolanie> listek= new ArrayList<Odwolanie>();
		listek=lokalnosc(ileOdwolan, zakresOdwolania);
		
		ObjectOutputStream wyj= new ObjectOutputStream(new FileOutputStream(plik));
		wyj.writeObject(listek);
		wyj.close();
	}
	public static ArrayList<Odwolanie> restore(String plik) throws IOException, ClassNotFoundException{
		ArrayList<Odwolanie> listek= new ArrayList<Odwolanie>();
		ObjectInputStream wej=new ObjectInputStream(new FileInputStream(plik));
		listek=(ArrayList<Odwolanie>)wej.readObject();
		wej.close();
		return listek;
	}
	
}
