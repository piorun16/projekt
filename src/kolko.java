import java.util.Scanner;


public class kolko {
    public static void main(String[] args) {
        System.out.println("Witaj w grze, podaj rozmiar planszy");
        int wymiar = new Scanner(System.in).nextInt();
        char obecnySymbol = 'X';
        char[][] plansza = new char[wymiar][wymiar];
        boolean czyKontynuowac = true;
        int licznikRuchow = 0;



        while (czyKontynuowac && licznikRuchow<=wymiar*wymiar) {
            kolko.drukujPlansze(plansza);
            boolean ruchPoprawny = wykonajRuch(plansza,obecnySymbol);
            if(ruchPoprawny) {
                licznikRuchow++;
                boolean wygranaWiersze = sprawdzWiersze(plansza,obecnySymbol);
                boolean wygranaKolumny = sprawdzKolumny(plansza,obecnySymbol);
                boolean wygranaSkos = sprawdzSkos(plansza,obecnySymbol);
                boolean wygranaSkosdwa = sprawdzSkosdwa(plansza,obecnySymbol);
                if(wygranaWiersze || wygranaKolumny || wygranaSkos||wygranaSkosdwa){
                    System.out.println("Gratulacje "+obecnySymbol);
                    czyKontynuowac = false;
                }
                obecnySymbol = obecnySymbol == 'X' ? 'O' : 'X';
            }
        }

    }

    public static boolean sprawdzSkos (char[][] plansza, char symbol){
        int wymiar = plansza.length;
        for( int i = 0; i < wymiar; i++) {
            if ( plansza[i][i] != symbol ) {
                return false;
            }
        }
        return true;
    }
    public static boolean sprawdzSkosdwa (char[][] plansza, char symbol){
        int wymiar = plansza.length;
        for( int i = 0; i < wymiar; i++) {
            if ( plansza[i][wymiar - i -1] != symbol ) {
                return false;
            }
        }
        return true;
    }

    public static boolean sprawdzKolumny (char[][] plansza, char symbol){
        int wymiar = plansza.length;
        for( int kolumna = 0; kolumna < wymiar; kolumna++) {
            boolean wygrana = true;
            for (char[] chars : plansza) {
                if (chars[kolumna] != symbol) {
                    wygrana = false;
                    break;
                }
            }
            if ( wygrana ) { return true;}
        }

        return false;
    }

    public static boolean sprawdzWiersze (char[][] plansza, char symbol){
        int wymiar = plansza.length;
        for (char[] chars : plansza) {
            boolean wygrana = true;
            for (int kolumna = 0; kolumna < wymiar; kolumna++) {
                if (chars[kolumna] != symbol) {
                    wygrana = false;
                    break;
                }
            }
            if (wygrana) {
                return true;
            }
        }

        return false;
    }


    public static boolean wykonajRuch(char[][] plansza, char obecnySymbol) {
        System.out.println(obecnySymbol + " twój ruch");
        System.out.println("Podaj indeks wiersza");
        int wiersz = new Scanner(System.in).nextInt();
        System.out.println("Podaj indeks kolumny");
        int kolumna = new Scanner(System.in).nextInt();
        boolean ruchPoprawny = plansza[wiersz][kolumna] == 0;
        if (ruchPoprawny) {
            plansza[wiersz][kolumna] = obecnySymbol;
            return true;
        } else {
            System.out.println("niepoprawny ruch, podaj inne pole \t");
            return false;
        }
    }

    public static void drukujPlansze(char[][] plansza) {
        int wymiar = plansza.length;
        // nagłówki kolumn
        System.out.print("\t");
        // petla drukujaca naglówki kolumn
        for (int i = 0; i < wymiar; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        // drukowanie wierszy
        for (int wiersz = 0; wiersz < wymiar; wiersz++) {
            System.out.print(wiersz + ":\t");
            for (int kolumna = 0; kolumna < wymiar; kolumna++) {
                System.out.print(plansza[wiersz][kolumna] + "\t");
            }
            System.out.println();
        }
    }
}