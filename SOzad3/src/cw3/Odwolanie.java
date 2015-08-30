package cw3;
public class Odwolanie {
	int czasPrzebywaniaWRamce;
	int wartosc;
	int bitOdniesienia;

	public Odwolanie(int wartosc) {
		czasPrzebywaniaWRamce = 0;
		this.wartosc = wartosc;
		if(wartosc == 0)
			bitOdniesienia = 0;
		else
			bitOdniesienia = 1;

	}
}
