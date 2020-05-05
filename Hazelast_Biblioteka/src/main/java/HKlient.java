import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.hibernate.instance.IHazelcastInstanceFactory;
import com.hazelcast.map.IMap;

import java.net.UnknownHostException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HKlient {

    static Scanner scanner;
    static Random r;
    static boolean koniec = false;

    public static void main(String[] args) throws UnknownHostException {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.OFF);
        ClientConfig clientConfig = HConfig.getClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        Map<Long, Biblioteka> bibliotekaMap = client.getMap("biblioteka");
        scanner = new Scanner(System.in);
        r = new Random();
        Long id;
        Long id1;
        bibliotekaMap.put(r.nextLong(), completeData(1, "Lśnienie", "Stephen King", 2016, "wolna"));
        bibliotekaMap.put(r.nextLong(), completeData(2, "Ekstradycja", "Remigiusz Mróz", 2020, "wypożyczona"));
        bibliotekaMap.put(r.nextLong(), completeData(3, "Listy do Pałacu", "Jorge Diaz", 2017, "wolna"));
        bibliotekaMap.put(r.nextLong(), completeData(4, "Kod Leonadra da Vinci", "Dan Brown", 2013, "wypożyczona"));
        bibliotekaMap.put(r.nextLong(), completeData(5, "Dziewczyna z Pociągu", "Paula Hawkins", 2016,"wolna"));
        while (true) {
            showMenu();
            System.out.println("Podaj wybór:");
            int wybor = scanner.nextInt();
            scanner.nextLine();
            switch (wybor) {
                case 1:
                    bibliotekaMap.put(r.nextLong(), addNew());
                    break;
                case 2:
                    getAll(bibliotekaMap);
                    break;
                case 3:
                    getOneByID(bibliotekaMap);
                    break;
                case 4:
                    System.out.println("Podaj klucz: ");
                    String linia = scanner.nextLine();
                    id1 = Long.parseLong(linia);
                    Biblioteka b ;
                    b = update(bibliotekaMap,id1);
                    bibliotekaMap.put(id1,b);
                    break;
                case 5:
                    System.out.println("Podaj Id: ");
                    id = Long.parseLong(scanner.nextLine());
                    bibliotekaMap.remove(id);
                System.out.println("\nUsunięto książkę");
                    break;
                case 0:
                    koniec = true;
                    break;
                default:
                    System.out.println("BŁĄD");
            }
        }
    }


    static Biblioteka completeData(int numerid, String tytul, String autor, int rokWydania, String status) {
        Biblioteka biblioteka = new Biblioteka(numerid, tytul, autor, rokWydania, status);
        return biblioteka;
    }

    public static Biblioteka addNew() {
        Biblioteka biblioteka = new Biblioteka();
        System.out.println("Podaj ID książki: ");
        biblioteka.setNumerId(Integer.parseInt(scanner.nextLine()));

        System.out.println("Podaj tytuł: ");
        biblioteka.setTytul(scanner.nextLine());

        System.out.println("Podaj autora: ");
        biblioteka.setAutor(scanner.nextLine());

        System.out.println("Podaj rok wydania: ");
        biblioteka.setRokWydania(Integer.parseInt(scanner.nextLine()));

        System.out.println("Podaj status: ");
        biblioteka.setStatus(scanner.nextLine());

        System.out.println("Dodano książkę ");

        return biblioteka;
    }

    static void getAll(Map<Long, Biblioteka> bibliotekaMap) {
        System.out.println("Książki: ");
        for (Map.Entry<Long, Biblioteka> e : bibliotekaMap.entrySet()) {
            System.out.println(e.getKey() + " => " + e.getValue().toString());
        }
    }

    static Biblioteka getOneByID(Map<Long, Biblioteka> bibliotekaMap) {
        Biblioteka biblioteka = new Biblioteka();
        System.out.println("Podaj ID Książki: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Map.Entry<Long, Biblioteka> e : bibliotekaMap.entrySet()) {
            if (e.getValue().getNumberId() == id) {
                System.out.println(e.getKey() + " => " + e.getValue().toString());
            }
        }
        return biblioteka;
    }

    static Biblioteka update(Map<Long, Biblioteka> bibliotekaMap, Long key) {
        Biblioteka biblioteka ;
        for (Map.Entry<Long, Biblioteka> e : bibliotekaMap.entrySet()) {
            if (key.equals(e.getKey()) == true) {

                biblioteka = e.getValue();
                System.out.println(e.getKey() + " => " + e.getValue().toString());
                System.out.println("Zmien ID: ");
                System.out.println("Aby pominąć naciśnij Q");
                String nrID = scanner.nextLine();
                if (nrID.equals("Q") == false) {
                    biblioteka.setNumerId(Integer.parseInt(nrID));
                }

                System.out.println("Zmień tytuł: ");
                System.out.println("Aby pominąć naciśnij Q");
                String tyt = scanner.nextLine();
                if (tyt.equals("Q") == false) {
                    biblioteka.setTytul(tyt);
                }

                System.out.println("Zmień autora: ");
                System.out.println("Aby pominąć naciśnij Q");
                String aut = scanner.nextLine();
                if (aut.equals("Q") == false) {
                    biblioteka.setAutor(aut);
                }

                System.out.println("Zmień rok wydania: ");
                System.out.println("Aby pominąć naciśnij Q");
                String rok = scanner.nextLine();
                if (rok.equals("Q") == false) {
                    biblioteka.setRokWydania(Integer.parseInt(rok));
                }

                System.out.println("Zmień status: ");
                System.out.println("Aby pominąć naciśnij Q");
                String stat = scanner.nextLine();
                if (stat.equals("Q") == false) {
                    biblioteka.setStatus(stat);
                }

                return biblioteka;
            }
        }
        return null;
    }
    static private void showMenu() {
        System.out.println("\nBiblioteka");
        System.out.println("1 - Dodaj książkę ");
        System.out.println("2 - Wyświetl wszystkie książki ");
        System.out.println("3 - Wyświetl książkę po ID ");
        System.out.println("4 - Zaaktualizuj ksiażkę");
        System.out.println("5 - Usuń książkę");
        System.out.println("0 - WYJŚCIE\n");
    }
}

