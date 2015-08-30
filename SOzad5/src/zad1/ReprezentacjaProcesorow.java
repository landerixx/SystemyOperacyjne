package zad1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class ReprezentacjaProcesorow {

	
	
	public static ArrayList<Procesor> generujProcesory(int ileSztuk){
		

		ArrayList<Procesor> procesory= new ArrayList<Procesor>();
		
		for(int i=1; i<=ileSztuk;i++)
			procesory.add(new Procesor(i));
		
		return procesory;
		
	}
	

	public static ArrayList<Procesor> generujProcesory(int ileSztuk,int maxIloscProcesow){
		

		ArrayList<Procesor> procesory= new ArrayList<Procesor>();
		
		for(int i=1; i<=ileSztuk;i++)
			procesory.add(new Procesor(i,maxIloscProcesow));
		
		return procesory;
		
	}
	
	
	
	public static void save(String plik, ArrayList<Procesor> ciag) throws IOException{
		ArrayList<Procesor> listek= new ArrayList<Procesor>();
		listek=ciag;
		
		ObjectOutputStream wyj= new ObjectOutputStream(new FileOutputStream(plik));
		wyj.writeObject(listek);
		wyj.close();
	}

	
	public static void save(String plik, int ileProcesorow, int maxIloscProcesow) throws IOException{
		ArrayList<Procesor> listek= new ArrayList<Procesor>();
		listek=generujProcesory(ileProcesorow, maxIloscProcesow);
		
		ObjectOutputStream wyj= new ObjectOutputStream(new FileOutputStream(plik));
		wyj.writeObject(listek);
		wyj.close();
	}
	public static ArrayList<Procesor>  restore(String plik) throws IOException, ClassNotFoundException{
		ArrayList<Procesor> listek= new ArrayList<Procesor>();
		ObjectInputStream wej=new ObjectInputStream(new FileInputStream(plik));
		listek=(ArrayList<Procesor>)wej.readObject();
		wej.close();
		return listek;
	}
	
	
}
