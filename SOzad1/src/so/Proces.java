package so;

import java.io.Serializable;



public class Proces implements Comparable<Proces>,Serializable {

	
	
	@Override
	public String toString() {
		return "Proces [Nr=" + PID + ", momentZgloszenia=" + momentZgloszenia
				+ ", czasOczekiwania=" + czasOczekiwania
				+ ", dlugoscFazyProcesora=" + dlugoscFazyProcesora
				+ ", czas pozostaly=" + czasPozostaly +"]" ;
	}

	int PID;
	int momentZgloszenia;
	int czasOczekiwania;
	int dlugoscFazyProcesora;
	public int czasPozostaly;
	
	
	

	public Proces(int PID, int momentZgloszenia, int dlugoscFazy) {
		this.PID = PID;
		this.momentZgloszenia = momentZgloszenia;
		this.dlugoscFazyProcesora = dlugoscFazy;
		czasOczekiwania = 0;
		czasPozostaly=dlugoscFazy;
	}
	protected Proces clone(){
		Proces r = new Proces(PID, momentZgloszenia, dlugoscFazyProcesora);
		r.czasOczekiwania = czasOczekiwania;
		return r;
	}
	public int getCzasPozostaly() {
		return czasPozostaly;
	}
	public void setCzasPozostaly(int czaspozostaly) {
		this.czasPozostaly = czaspozostaly;
	}

	public int getPID() {
		return PID;
	}

	public void setPID(int pID) {
		PID = pID;
	}

	public int getMomentZgloszenia() {
		return momentZgloszenia;
	}

	public void setMomentZgloszenia(int momentZgloszenia) {
		this.momentZgloszenia = momentZgloszenia;
	}

	public int getCzasOczekiwania() {
		return czasOczekiwania;
	}

	public void DodajCzasOczekiwania(int dodatkowy) {
		czasOczekiwania+=dodatkowy;
	}
	
	public void SetCzasOczekiwania(int d) {
		czasOczekiwania=d;
	}

	public int  getDlugoscFazyProcesora() {
		return dlugoscFazyProcesora;
	}

	public void setDlugoscFazyProcesora(int dlugoscFazyProcesora) {
		this.dlugoscFazyProcesora = dlugoscFazyProcesora;
	}

	public int compareTo(Proces p) {
		return getMomentZgloszenia()-p.getMomentZgloszenia();
}
}