package SOzad4;

import java.io.Serializable;

public class Odwolanie implements Serializable {
	
@Override
	public String toString() {
		return "[" + wartosc + "]"; 
	}
int wartosc;





public Odwolanie(int wartosc) {
	super();
	this.wartosc = wartosc;

}

public int getWartosc() {
	return wartosc;
}
public void setWartosc(int wartosc) {
	this.wartosc = wartosc;
}






}
