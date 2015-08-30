package zad3;

import java.io.Serializable;

public class Odwolanie implements Serializable {
	
@Override
	public String toString() {
		return "[" + wartosc + "]"; // "+ "bit: "+ bit2szansy  "("+czasSprowadzenia+")";
	}
int wartosc;
int bit2szansy;
int czasSprowadzenia;



public int getCzasSprowadzenia() {
	return czasSprowadzenia;
}
public void setCzasSprowadzenia(int czasSprowadzenia) {
	this.czasSprowadzenia = czasSprowadzenia;
}
public Odwolanie(int wartosc) {
	super();
	this.wartosc = wartosc;
	czasSprowadzenia=0;
	bit2szansy=1;
}

public int getWartosc() {
	return wartosc;
}
public void setWartosc(int wartosc) {
	this.wartosc = wartosc;
}
public int getBitOdniesienia() {
	return bit2szansy;
}
public void setBitOdniesienia(int bitOdniesienia) {
	this.bit2szansy = bitOdniesienia;
}





}
