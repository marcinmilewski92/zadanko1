import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            String nazwaKatalogu = args[0];

            File katalog = new File(nazwaKatalogu);

            File lista[] = katalog.listFiles(); //Tworzymy listę plików i folderów z wybranego katalogu


            String listaPlikow1[] = new String[lista.length]; // Tworzymy tablicę, w której zapiszemy nazwy folderów



        /*
        Instrukcja warunkowa pozbywa się nazw folderów i zapisuje tylko nazwy plików do nowej tablicy.
        K służy do określenia ile nazw folderów usunęliśmy, oraz sprawienia by nie było pustych pól między w "środku" tablicy

         */

            int k = 0;

            for (int a = 0; a < lista.length; a++) {
                if (!(lista[a].isDirectory())) {
                    listaPlikow1[a - k] = lista[a].toString();

                } else {
                    k++;
                }
            }


            // Tworzymy nową tablicę, która nie będzie przechowywać pustych pul, które przerzuciliśmy na koniec
            String listaPlikow[] = new String[listaPlikow1.length - k];

            for (int b = 0; b < listaPlikow.length; b++) {
                listaPlikow[b] = listaPlikow1[b];
            }


            System.out.println(nazwaKatalogu);

            HashMap<Character, Integer> mapa = new HashMap<>();

            /* Tworzymy mapę, której za pomocą pętli bierzemy po koleji wyrazy z tablicy, ucinamy rozszerzenie pliku, sprawdzamy ostatnią literę nazwy pliku.
            Jeżeli litera nie wystąpiła, dodajemy do mapy, gdzie kluczem jest owa litera, wartością jest "1";
            Jeżeli litera jest już w mapie, zastępujemy vartość liczbą o 1 większą.
            */


            for (int i = 0; i < listaPlikow.length; i++) {
                for (int j = listaPlikow[i].length() - 1; j >= 0; j--) {
                    if (listaPlikow[i].charAt(j) == '.') {
                        listaPlikow[i] = listaPlikow[i].substring(0, j);

                        if (!(mapa.containsKey(listaPlikow[i].charAt(j - 1)))) {
                            mapa.put(listaPlikow[i].charAt(j - 1), 1);
                        } else {
                            int staraWartosc = mapa.get(listaPlikow[i].charAt(j - 1));
                            staraWartosc++;
                            mapa.replace(listaPlikow[i].charAt(j - 1), staraWartosc);
                        }

                        break;
                    }
                }
            }

            for (Map.Entry<Character, Integer> para : mapa.entrySet()) {
                Character klucz = para.getKey();
                Integer wartosc = para.getValue();
                System.out.println("Plik, którego nazwa kończy literą " + klucz + " na końcu, pojawił się " + wartosc + " raz(y).");
            }

            if (mapa.isEmpty()) {
                System.out.println("W katalogu nie ma plików.");
            }


        } catch (Exception e) {
            System.out.println("Błędny argument");
        }


    }

}