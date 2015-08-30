package zad1;

import java.io.Serializable;
import java.util.Random;

public class Proces implements Cloneable, Serializable {

	
	int obciazenie; // max 100
	int czasPojawienia;
	int dlugosc;
	int pozostalo;
	
	


	boolean aktywny; //aktywny -true, oczekujacy- false
	
	void clear(){
		aktywny=false;
		pozostalo=dlugosc;
	}
	
	
	public Proces(int obciazenie, int czasPojawienia, int dlugosc) {
		super();
		this.obciazenie = obciazenie;
		this.czasPojawienia = czasPojawienia;
		this.dlugosc = dlugosc;
		pozostalo=dlugosc;
		aktywny=false;
	}

	
	
	Proces(){
		Random rand= new Random();
		czasPojawienia= rand.nextInt(100)+1; //od 1 do 100
		dlugosc= 100 + rand.nextInt(900); // 100-1000
		obciazenie = rand.nextInt(10)+1; // 1-10
		aktywny= false;
		pozostalo=dlugosc;
 		
	}
	  @Override
	    public Proces clone() {
	        Proces instance = new Proces();
	        
	        instance.obciazenie = this.obciazenie;
	        instance.czasPojawienia=this.czasPojawienia;
	        instance.dlugosc=this.dlugosc;
	        instance.pozostalo=this.pozostalo;
	        instance.aktywny=this.aktywny;
	        return instance;
	    }

	  
	  
	@Override
	public String toString() {
		return "Proces [obciazenie=" + obciazenie + ", czasPojawienia="
				+ czasPojawienia + ", dlugosc=" + dlugosc  + "]";
	}
	
	
}
