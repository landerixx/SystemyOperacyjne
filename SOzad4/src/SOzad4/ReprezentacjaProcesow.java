package SOzad4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class ReprezentacjaProcesow {

	
	
	public static ArrayList<Proces> generujCiagProcesow(int iloscProcesow){

		ArrayList<Proces> procesy= new ArrayList<Proces>();
		for(int i=0; i<iloscProcesow;i++)
			procesy.add(new Proces(1000, 1000, 10));
		
		return procesy;
	}
	
	
	public static ArrayList<Proces> generujCiagProcesow(int iloscProcesow, int iloscOdwolan,int maxWartoscOdwolania, int promienLokalnosci){
		
		ArrayList<Proces> procesy= new ArrayList<Proces>();
		for(int i=0; i<iloscProcesow;i++)
		procesy.add(new Proces(iloscOdwolan, maxWartoscOdwolania, promienLokalnosci));
		
		return procesy;
		
	}
	

	public static ArrayList<Proces> generujRandCiagProcesow(int iloscProcesow, int maxIloscOdwolan,int maxPromienLokalnosci){
		Random rand= new Random();
		ArrayList<Proces> procesy= new ArrayList<Proces>();
		for(int i=0; i<iloscProcesow;i++)
		procesy.add(new Proces(rand.nextInt(maxIloscOdwolan)+1, 1000, rand.nextInt(maxPromienLokalnosci)+1));
		
		return procesy;
		
	}
	
	public static ArrayList<Proces> generujRandCiagProcesow(int iloscProcesow,int minIloscOdwolan, int maxIloscOdwolan,int maxPromienLokalnosci){
		Random rand= new Random();
		ArrayList<Proces> procesy= new ArrayList<Proces>();
		for(int i=0; i<iloscProcesow;i++)
		procesy.add(new Proces(rand.nextInt(maxIloscOdwolan-minIloscOdwolan)+minIloscOdwolan, 1000, rand.nextInt(maxPromienLokalnosci)+1));
		
		return procesy;
		
	}
	
	
	
	
	
	
	
	
	
	
	public static ArrayList<Proces> generujRandCiagProcesowR(int iloscProcesow,int minIloscOdwolan, int maxIloscOdwolan,int PromienLokalnosci){
		Random rand= new Random();
		ArrayList<Proces> procesy= new ArrayList<Proces>();
		for(int i=0; i<iloscProcesow;i++)
		procesy.add(new Proces(rand.nextInt(maxIloscOdwolan-minIloscOdwolan)+minIloscOdwolan, 1000, PromienLokalnosci));
		
		return procesy;
		
	}
	
	
	public static ArrayList<Proces> generujRandCiagProcesowLokalnoscPrzylegla(int iloscProcesow,int minIloscOdwolan, int maxIloscOdwolan){
		Random rand= new Random();
		ArrayList<Proces> procesy= new ArrayList<Proces>();
		for(int i=0; i<iloscProcesow;i++)
		procesy.add(new Proces(rand.nextInt(maxIloscOdwolan-minIloscOdwolan)+minIloscOdwolan, 1000));
		
		return procesy;
		
	}
	
	
	
	
	
	
	
	
	
	
	public static void wyswietl( ArrayList<Proces> procesy){
		int i=0;
		for(Proces p: procesy){
			System.out.println("Proces nr " +i+ ": "+ p);
		i++;
		}
	}
	
	public static void przydzielZadanaIloscRamek( ArrayList<Proces> procesy, int iloscRamek ){
		
		for(Proces p: procesy)
			p.przydzielonaIloscRamek=iloscRamek;
		
	}
	

	public static void save(String plik, ArrayList<Proces> procesy) throws IOException{
		
		
		ObjectOutputStream wyj= new ObjectOutputStream(new FileOutputStream(plik));
		wyj.writeObject(procesy);
		wyj.close();
	}
	
	
	public static ArrayList<Proces> restore(String plik) throws IOException, ClassNotFoundException{
		ArrayList<Proces> listek= new ArrayList<Proces>();
		ObjectInputStream wej=new ObjectInputStream(new FileInputStream(plik));
		listek=(ArrayList<Proces>)wej.readObject();
		wej.close();
		return listek;
	}
	
	
}

