package cw3;
import java.util.Random;

public class Zadanie3 {
	static Random generator = new Random();
	Odwolanie[] ciagOdwolan;
	int ileOdwolan;
	Odwolanie[] ramka;
	int ileRamek;
	int takt = 0;
	int iloscBledow = 0;

	public Zadanie3(int ileOdwolan, int ileRamek, int zakres) {
		ciagOdwolan = new Odwolanie[ileOdwolan];
			for(int i = 0; i < ileOdwolan; i++)  {
				ciagOdwolan[i] = new Odwolanie(generator.nextInt(zakres) + 1);
			}

		ramka = new Odwolanie[ileRamek];
			for(int i = 0; i < ileRamek; i++)
				ramka[i] = new Odwolanie(0);

		this.ileOdwolan = ileOdwolan;
		this.ileRamek = ileRamek;
	}

	public void drukujCiagOdwolan() {
		for(int i = 0; i < ileOdwolan; i++)
			System.out.print("- " + ciagOdwolan[i].wartosc + " -");
	}

	public void drukujRamke(int a) {
		for(int i = 0; i < ileRamek; i++)
			if(ramka[i] == null)
				System.out.print("- NULL -");
			else {
				if(a == 1)
					System.out.print("- " + ramka[i].wartosc + " -");
				else
					System.out.print("- " + ramka[i].wartosc + "(" + ramka[i].bitOdniesienia + ")" + " -");
			}
		System.out.println();
	}

	public boolean czyJestJuzWRamce(Odwolanie o) { //sprawdza czy dana strona z ciagu Odwolan jest juz w ramce, jesli tak to nie bedziemy jej dodawac, jesli nie to mozemy wstawic nowa strone do ramki
		boolean jest = false;
		for(int i = 0; i < ileRamek && jest==false; i++) {
			if(ramka[i].wartosc != 0 && ramka[i].wartosc == o.wartosc)
				jest = true;
		}
		return jest;
	}

	public boolean czyRamkaJestPelna() { 	//sprawdzamy, czy wszystkie ramki sa zajete (sa w nich strony), przeszukujemy tablice ramek (domyslnie sa w niej zera)
		boolean jest = true;
		for(int i = 0; i < ileRamek && jest == true; i++)
			if(ramka[i].wartosc == 0)
				jest = false;
		return jest;
	}

	public int ktoryNajdluzejWRamce() { 	//sprawdzamy po czasach przebywania w ramce kto najdluzej siedzi w swojej ramce
		int najdluzej = 0;
		int indeks = -1;
		for (int i = 0; i < ileRamek; i++)
			if(ramka[i].czasPrzebywaniaWRamce > najdluzej) {
				najdluzej = ramka[i].czasPrzebywaniaWRamce;
				indeks = i;
			}
		return indeks;
	}

	public int ktoryBedzieNajpozniej(int OdwTeraz) { //OdwTeraz przyjmie w metodzie OPT wartosc i, czyli od miejsca w ktorym teraz jestesmy (np. wyciagamy 5 strone z ciagu Odwolan), bedziemy szukac kandydata z tych, ktore sa za nia
		int indeksRamki = 0;						 //szukamy tej ramki, do ktorej wstawimy nowa strone
		int najpozniej = 0;
			for(int i = 0; i < ileRamek; i++) {		 //przykladowo bierzemy pierwsza ramke - jest tam strona o wartosci 1, szukamy, na ktorej pozycji w ciagu Odwolan znowu sie pojawi
				if(naKtorejPozycjiPowtorzenie(OdwTeraz, ramka[i].wartosc) == -1) { //jesli w ogole nie ma powtorzenia to znaczy, ze musimy wybrac ta strone - w koncu nie wystapi nigdy - a wiec najpozniej
					indeksRamki = i;				//wezmiemy ten indeks i przerwiemy petle, skoro ta wartosc sie nie powtorzy, to za nia wstawimy
					break;
				}
				if(naKtorejPozycjiPowtorzenie(OdwTeraz, ramka[i].wartosc) > najpozniej) { 		//jesli pozycja powtorzenia bedzie wieksza od znalezionej to zapamietamy ten indeks, teraz to wartosc z tej ramki wystapi najpozniej - a wiec jest ta szukana
					najpozniej = naKtorejPozycjiPowtorzenie(OdwTeraz, ramka[i].wartosc);
					indeksRamki = i;
				}
			}
			return indeksRamki;
	}

	public int naKtorejPozycjiPowtorzenie(int pozycja, int wartosc) { //odkad szukamy i jakiej wartosci
		boolean pierwsze = false;
		int k = -1;													  //kiedy nie ma powtorzenia za szukana wartoscia to bedzie wartosc -1, jak bedzie to zwrocimy indeks z ciagu odwolan
		for(int i = pozycja; i < ileOdwolan && pierwsze == false; i++)
			if(ciagOdwolan[i].wartosc == wartosc){
				pierwsze = true;
				k = i;
			}
			return k;
	}

	public int szukajWRamceWartosci(int x) {	//metoda szukajaca danej wartosci w ramkach, mamy tablice ramek, wiec da nam indeks
		int gdzieWRamce = 0;
		boolean znaleziono = false;
			for(int i = 0; i < ileRamek && znaleziono == false; i++) {
				if(ramka[i].wartosc == x) {
					gdzieWRamce = i;
					znaleziono = true;
				}
			}
		return gdzieWRamce;
	}

	public boolean czyWszystkieBityNaJeden() { //dla ALRU, jak wszystkie bity to jedynki, to wstawiamy za pierwszy, jesli nie, to wstawiamy za bit zerowy
		boolean flaga = true;
			for(int i = 0; i < ileRamek  && flaga == true; i++)
				if(ramka[i].bitOdniesienia == 0)
					flaga = false;
			return flaga;
	}

	public int najblizszyBitZerowy() { //szukamy ramki, w ktorej mamy strone z bitem odniesienia rownym 0, tam bedzie nowe odwolanie
		int gdzieWRamce = 0;
		boolean znaleziono = false;
			for(int i = 0; i < ileRamek & znaleziono == false; i++) {
				if(ramka[i].bitOdniesienia == 0) {
					gdzieWRamce = i;
					znaleziono = true;
				}
			}
		return gdzieWRamce;
	}

	public void FIFO() { 											//usuwamy strone najdluzej przebywajaca w pamieci fizycznej
		for(int i = 0; i < ileOdwolan; i++) {						//wyciagamy kolejne strony z naszego ciagu odwolan
			if(!czyJestJuzWRamce(ciagOdwolan[i])) {					//bierzemy tylko te, ktorych nie ma w ramkach
				if(czyRamkaJestPelna()) {							//jesli ramka jest juz pelna, to musimy zamieniac za odpowiednia strone
					ramka[ktoryNajdluzejWRamce()] = ciagOdwolan[i];	//wstawiamy za tego, ktory jest najdluzej w ramce, mamy specjalna metede, ktora nam wyszukuje odpwiednia ramke, w ktorej strona siedzi nadluzej (za nia wstawimy nowa)
					iloscBledow++;									//przy odwolaniu nastepuje blad
				}
				if(!czyRamkaJestPelna()) {							//wazne dla poczatku odwolan (od tego ifa zaczynamy nasza zabawe, kiedy ramka jeszcze nie jest pelna
					ramka[takt] = ciagOdwolan[i];					//dodajemy do naszej ramki nowa strone (na wolne miejsce w ramce)
					takt++;
					iloscBledow++;
				}

				for(int j = 0; j < ileRamek; j++) {					//zwiekszamy czasy przebywania w ramce kazdej stronie, ktora jest w ramce
					if(ramka[j].wartosc != 0)
						ramka[j].czasPrzebywaniaWRamce++;
				}
				drukujRamke(1);									//chcesz zobaczyc jak to dziala krok po kroku (stan ramek), odblokuj ta metode (wywolanie z wartoscia 1, zobacz wyzej w metodzie - sa 2 mozliwosci drukowania ramki, jedna jest dla ALRU, zeby pokazywala tez wartosc bitu)
			}
		}
	}

	public void RAND() {											//to samo co FIFO, tylko wybieramy losowo ramke, do ktorej wstawimy nowa strone
		for(int i = 0; i < ileOdwolan; i++) {
			if(!czyJestJuzWRamce(ciagOdwolan[i])) {
				if(czyRamkaJestPelna()) {
					ramka[generator.nextInt(ileRamek)] = ciagOdwolan[i];
					iloscBledow++;
				}
				if(!czyRamkaJestPelna()) {
					ramka[takt] = ciagOdwolan[i];
					takt++;
					iloscBledow++;
				}
				//drukujRamke(1);
			}
		}
	}

	public void OPT() {												//optymalny - usuwamy strone, ktora nie bedzie najdluzej uzywana
		for(int i = 0; i < ileOdwolan; i++) {
			if(!czyJestJuzWRamce(ciagOdwolan[i])) {
				if(czyRamkaJestPelna()) {
					ramka[ktoryBedzieNajpozniej(i + 1)] = ciagOdwolan[i];	//w miejsce tego, ktory wystapi najpozniej (szukamy przy pomocy dodatkowej metody) wstawimy nowa strone
					iloscBledow++;
				}
				if(!czyRamkaJestPelna()) {
					ramka[takt] = ciagOdwolan[i];
					takt++;
					iloscBledow++;
				}
				//drukujRamke(1);
			}
		}
	}

	public void LRU() {									//usuwamy strone, do ktorej najdluzej nie nastapilo odwolanie, dzialanie ukazane tutaj: http://www.mimuw.edu.pl/~janowska/so/cw11.html
		for(int i = 0; i < ileOdwolan; i++) {			//bierzemy po kolei strony, ta ktora jest najstarsza bedzie przesuwana na koniec (w tablicy ramek), a wiec ta najswiezsza bedzie na poczatku
			if(czyRamkaJestPelna()) {					//ramka jest juz pelna (wejdzie tutaj dopiero jak wypelni ramki na poczatku z nastepnego ifa)
				if(czyJestJuzWRamce(ciagOdwolan[i])) {	//jesli nowa strona jest juz w ramce
					for(int n = szukajWRamceWartosci(ciagOdwolan[i].wartosc); n > 0; n--)	//szukamy w ktorej ramce jest, teraz wazne, przesuwamy strony ktore byly przed nasza szukana wartoscia o 1 w prawo i wstawiamy na 1 pozycje ta ktora sie powtarza (dzieki temu bedziemy wiedziec, ze dopiero nastapilo do niej odwolanie, nie bedzie ona gotowa do wywalenia)
						ramka[n] = ramka[n - 1];
					ramka[0] = ciagOdwolan[i];
				}
				if(!czyJestJuzWRamce(ciagOdwolan[i]) ) {//jesli jeszcze nie ma tej strony to przesuwamy wszystkie o jeden w prawo i robimy miejsce dla nowej na 1 pozycji (ona wskazuje na ostatnie odwolanie, coraz starsze beda coraz dalej)
					for(int m = ileRamek - 1; m > 0; m--)
						ramka[m] = ramka[m - 1];
					ramka[0] = ciagOdwolan[i];
					iloscBledow++;
				}
			}
			if(!czyRamkaJestPelna()) { 					//poczatkowe wyelnianie ramek, kiedy sa jeszcze jakies puste
				if(!czyJestJuzWRamce(ciagOdwolan[i])) {	//jesi nie ma danego wyciagnietego z 1 fora odwolania w ramce to przesuwamy i robimy miejsce na 1 pozycji
					for(int k = ileRamek - 1; k > 0; k--) {
						ramka[k] = ramka[k - 1];
					}
					ramka[0] = ciagOdwolan[i];
					iloscBledow++;
				}
				if(czyJestJuzWRamce(ciagOdwolan[i])) {	//jesli w ramce jest to tak jak wczesniej, przestawiamy to co bylo na 1 pozycje
					for(int n = szukajWRamceWartosci(ciagOdwolan[i].wartosc); n > 0; n--)
							ramka[n] = ramka[n - 1];
					ramka[0] = ciagOdwolan[i];
				}
			}
			//drukujRamke(1);
			//System.out.println("Czy pelna: " + czyRamkaJestPelna());
		}
	}

	public void APROKSYMOWANY() { 							//inaczej algorytm drugiej szansy, opis i dzialanie na tej stronie: http://edu.pjwstk.edu.pl/wyklady/sop/scb/wyklad8/wyklad.html
		for(int i = 0; i < ileOdwolan; i++) {				//wyciagamy kolejne strony z naszej kolejki odwolan
			if(czyRamkaJestPelna()) {						//jesli wszystkie ramki sa zajete to trzeba wybrac odpowiednia, za ktora wstawimy nowa strone
				if(czyWszystkieBityNaJeden()) {				//w sytuacji, gdy wszystkie bity stron ramek sa na 1, to wstawimy nowa za ta pierwsza, tutaj nasz algorytm zadziala jak FIFO
					if(!czyJestJuzWRamce(ciagOdwolan[i])) { //jesli nowej strony nie ma jeszcze w ramce
						ramka[0] = ciagOdwolan[i];			//to wstawiamy ja do pierwszej ramki (jak w FIFO)
						for(int j = 1; j < ileRamek; j++)	//zmieniamy reszcie stron w ramkach bity odniesienia na 0 i zwiekszamy ilosc bledow (w koncu weszlo do ramki cos nowego)
						ramka[j].bitOdniesienia = 0;
						iloscBledow++;
					}
				}
				if(!czyWszystkieBityNaJeden()) {			//Jesli sa jakies odwolania w ramce, ktore maja bit ustawiony na 0
					if(czyJestJuzWRamce(ciagOdwolan[i])) {	//Jesli nasza strona jest juz w ramce, to szukamy w ktorym miejscu i aktualizujemy jej bit odniesienia na 1, jesli miala na 0, nie zwiekszamy bledu bo strona ta juz byla (my tylko dajemy do zrozumienia, ze strona znowu wystapila)
						if(ramka[szukajWRamceWartosci(ciagOdwolan[i].wartosc)].bitOdniesienia == 0) {
							ramka[szukajWRamceWartosci(ciagOdwolan[i].wartosc)].bitOdniesienia = 1;
						}
					}
					if(!czyJestJuzWRamce(ciagOdwolan[i])) {	//Jesli naszej strony nie ma jeszcze w ramce, to dodajemy ja w miejsce strony z ramki, ktora ma bit ustawiony na 0 (czyli godzi sie, aby cos za nia weszlo. Domyslnie strona ma wartosc bitu rowna 1 (wazne przy dodawaniu nowej strony)
						ramka[najblizszyBitZerowy()] = ciagOdwolan[i];
						iloscBledow++;
					}
				}

			}
			if(!czyRamkaJestPelna()) { 						//jesli sa wolne ramki to wstawiamy kolejne strony
				if(!czyJestJuzWRamce(ciagOdwolan[i])) { 	//odwolujemy sie tylko do tych stron, ktorych nie mam w ramkach
					ramka[takt] = ciagOdwolan[i]; 			//wstawiamy to odwolanie, na ktorym jestesmy
					takt++; 								//dzieki temu bedziemy wstawiac strony w kolejne ramki (moze byc cos nie tak, jesli strony beda sie powtarzac
					iloscBledow++; 							//popelniamy blad odwolujac sie do nowej strony
				}
			}
		//drukujRamke(2);
		}
	}
}



