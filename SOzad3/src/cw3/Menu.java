package cw3;
import java.util.Scanner;
public class Menu {
   static Scanner sc = new Scanner(System.in);
   static String[] tab;
   static int n;
   public Menu() {
        tab = new String[7];
        n = tab.length;
        tab[0] = "1. FIFO";
        tab[1] = "2. RAND";
        tab[2] = "3. OPT";
        tab[3] = "4. LRU";
        tab[4] = "5. LRU APROKSYMOWANY";
        tab[5] = "6. POROWNAJ ALGORYTMY";
        tab[6] = "7. Zakoncz program";
   }
   public static void wyswietlMenu(Menu m1) {
        System.out.println("MENU: \n");
        for(int i = 0; i < n; i++) {
            System.out.println(Menu.tab[i]);
        }
   }
   public static void dzialanie(Menu m1) {
        int opcja = 0;
        while(opcja != 3) {
            wyswietlMenu(m1);
            System.out.print(" \n***** \n\nKtora opcje chcesz wybrac? ");
            opcja = sc.nextInt();
            switch(opcja) {
                case 1:
                System.out.println("Wybrano algorytm FIFO.\n");

                System.out.println("Jaka ma byc ilosc stron (odwolan) ?");
        		int iloscO1 = sc.nextInt();
        		System.out.println("Ile ma byc ramek?");
        		int rozmiarR1 = sc.nextInt();
        		System.out.println("Jaki ma byc zakres wartosci dla stron?");
        		int zakres1 = sc.nextInt();
        		Zadanie3 zad3a = new Zadanie3(iloscO1, rozmiarR1, zakres1);
        		System.out.println();

        		System.out.println("Ciag odwolan dla FIFO: ");
        		zad3a.drukujCiagOdwolan();
        		System.out.println();
        		System.out.println("Obsluga odwolan: ");
        		zad3a.FIFO();
        		System.out.println();
        		System.out.println("Bledy braku strony: " + zad3a.iloscBledow);
        		System.out.println();
        		System.out.println("* * * * * * * * * * * * *");

        		System.out.println("Czy chcesz wrocic do MENU GLOWNEGO? Wcisnij 1, aby wrocic do MENU GLOWNEGO lub wcisnij 0, aby zakonczyc program.");
                int wybor = sc.nextInt();
                if(wybor == 1) {
                    break;
                }
                else {
                    System.out.println("Zakonczono dzialanie programu.\n");
                    opcja = 3;
                    break;
                }
                case 2:
                System.out.println("Wybrano algorytm RAND.\n");

                System.out.println("Jaka ma byc ilosc stron (odwolan) ?");
        		int iloscO2 = sc.nextInt();
        		System.out.println("Ile ma byc ramek?");
        		int rozmiarR2 = sc.nextInt();
        		System.out.println("Jaki ma byc zakres wartosci dla stron?");
        		int zakres2 = sc.nextInt();
        		Zadanie3 zad3b = new Zadanie3(iloscO2, rozmiarR2, zakres2);
        		System.out.println();

        		System.out.println("Ciag odwolan dla RAND: ");
        		zad3b.drukujCiagOdwolan();
        		System.out.println();
        		System.out.println("Obsluga odwolan: ");
        		zad3b.RAND();
        		System.out.println();
        		System.out.println("Bledy braku strony: " + zad3b.iloscBledow);
        		System.out.println();
        		System.out.println("* * * * * * * * * * * * *");

                System.out.println("Czy chcesz wrocic do MENU GLOWNEGO? Wcisnij 1, aby wrocic do MENU GLOWNEGO lub wcisnij 0, aby zakonczyc program.");
                wybor = sc.nextInt();
                 if(wybor == 1) {
                    break;
                }
                else {
                    System.out.println("Zakonczono dzialanie programu.\n");
                    opcja = 3;
                    break;
                }
                case 3:
                    System.out.println("Wybrano algorytm OPT.\n");

                    System.out.println("Jaka ma byc ilosc stron (odwolan) ?");
            		int iloscO3 = sc.nextInt();
            		System.out.println("Ile ma byc ramek?");
            		int rozmiarR3 = sc.nextInt();
            		System.out.println("Jaki ma byc zakres wartosci dla stron?");
            		int zakres3 = sc.nextInt();
            		Zadanie3 zad3c = new Zadanie3(iloscO3, rozmiarR3, zakres3);
            		System.out.println();

            		System.out.println("Ciag odwolan dla OPT: ");
            		zad3c.drukujCiagOdwolan();
            		System.out.println();
            		System.out.println("Obsluga odwolan: ");
            		zad3c.OPT();
            		System.out.println();
            		System.out.println("Bledy braku strony: " + zad3c.iloscBledow);
            		System.out.println();
            		System.out.println("* * * * * * * * * * * * *");

                    System.out.println("Czy chcesz wrocic do MENU GLOWNEGO? Wcisnij 1, aby wrocic do MENU GLOWNEGO lub wcisnij 0, aby zakonczyc program.");
                    wybor = sc.nextInt();
                     if(wybor == 1) {
                        break;
                }
                case 4:
                    System.out.println("Wybrano algorytm LRU.\n");

                    System.out.println("Jaka ma byc ilosc stron (odwolan) ?");
            		int iloscO5 = sc.nextInt();
            		System.out.println("Ile ma byc ramek?");
            		int rozmiarR5 = sc.nextInt();
            		System.out.println("Jaki ma byc zakres wartosci dla stron?");
            		int zakres5 = sc.nextInt();
            		Zadanie3 zad3e = new Zadanie3(iloscO5, rozmiarR5, zakres5);
            		System.out.println();

            		System.out.println("Ciag odwolan dla LRU: ");
            		zad3e.drukujCiagOdwolan();
            		System.out.println();
            		System.out.println("Obsluga odwolan: ");
            		zad3e.LRU();
            		System.out.println();
            		System.out.println("Bledy braku strony: " + zad3e.iloscBledow);
            		System.out.println();
            		System.out.println("* * * * * * * * * * * * *");

                    System.out.println("Czy chcesz wrocic do MENU GLOWNEGO? Wcisnij 1, aby wrocic do MENU GLOWNEGO lub wcisnij 0, aby zakonczyc program.");
                    wybor = sc.nextInt();
                     if(wybor == 1) {
                        break;
                }
                case 5:
                    System.out.println("Wybrano algorytm LRU APROKSYMOWANY.\n");

                    System.out.println("Jaka ma byc ilosc stron (odwolan) ?");
            		int iloscO7 = sc.nextInt();
            		System.out.println("Ile ma byc ramek?");
            		int rozmiarR7 = sc.nextInt();
            		System.out.println("Jaki ma byc zakres wartosci dla stron?");
            		int zakres7 = sc.nextInt();
            		Zadanie3 zad3p = new Zadanie3(iloscO7, rozmiarR7, zakres7);
            		System.out.println();

            		System.out.println("Ciag odwolan dla LRU: ");
            		zad3p.drukujCiagOdwolan();
            		System.out.println();
            		System.out.println("Obsluga odwolan: ");
            		zad3p.APROKSYMOWANY();
            		System.out.println();
            		System.out.println("Bledy braku strony: " + zad3p.iloscBledow);
            		System.out.println();
            		System.out.println("* * * * * * * * * * * * *");

                    System.out.println("Czy chcesz wrocic do MENU GLOWNEGO? Wcisnij 1, aby wrocic do MENU GLOWNEGO lub wcisnij 0, aby zakonczyc program.");
                    wybor = sc.nextInt();
                     if(wybor == 1) {
                        break;
                }
                case 6:
                	System.out.println("Porownanie: ");
                    System.out.println("Jaka ma byc ilosc stron (odwolan) ?");
            		int iloscO4 = sc.nextInt();
            		System.out.println("Ile ma byc ramek?");
            		int rozmiarR4 = sc.nextInt();
            		System.out.println("Jaki ma byc zakres wartosci dla stron?");
            		int zakres4 = sc.nextInt();
            		System.out.println();

            		Zadanie3 a = new Zadanie3(iloscO4, rozmiarR4, zakres4);
            		System.out.println();
            		System.out.println("Ilosc bledow dla FIFO: ");
            		a.FIFO();
            		System.out.println("Bledy braku strony: " + a.iloscBledow);

            		Zadanie3 b = new Zadanie3(iloscO4, rozmiarR4, zakres4);
            		System.out.println("Ilosc bledow dla RAND: ");
            		b.RAND();
            		System.out.println("Bledy braku strony: " + b.iloscBledow);

            		Zadanie3 c = new Zadanie3(iloscO4, rozmiarR4, zakres4);
            		System.out.println("Ilosc bledow dla OPT: ");
            		c.OPT();
            		System.out.println("Bledy braku strony: " + c.iloscBledow);

            		Zadanie3 d = new Zadanie3(iloscO4, rozmiarR4, zakres4);
            		System.out.println("Ilosc bledow dla LRU: ");
            		d.LRU();
            		System.out.println("Bledy braku strony: " + d.iloscBledow);
            		d.iloscBledow = 0;

            		Zadanie3 e = new Zadanie3(iloscO4, rozmiarR4, zakres4);
            		System.out.println("Ilosc bledow dla LRU APROKSYMOWANEGO: ");
            		e.APROKSYMOWANY();
            		System.out.println("Bledy braku strony: " + e.iloscBledow);
            		e.iloscBledow = 0;

            		System.out.println();
            		System.out.println("* * * * * * * * * * * * *");

                    System.out.println("Czy chcesz wrocic do MENU GLOWNEGO? Wcisnij 1, aby wrocic do MENU GLOWNEGO lub wcisnij 0, aby zakonczyc program.");
                    wybor = sc.nextInt();
                     if(wybor == 1) {
                        break;
                }
                case 7:
                System.out.println("Zakonczono dzialanie programu.\n");
                break;
                default:
                System.out.println("\nNiepoprawnie wprowadzone dane. Sprobuj ponownie.");
                dzialanie(m1);
                }
            }
        }

   public static void main(String[] args) {
	   Menu m1 = new Menu();
	   dzialanie(m1);
   }
    }



