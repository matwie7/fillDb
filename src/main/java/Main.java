import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Main class
 * Created by Development on 13.04.2016.
 */
public class Main {
    public static void main(String[] args) {
        doThings();
    }

    public static void doThings() {
        Random random = new Random(System.nanoTime());
        DbHandler dbHandler = new DbHandler();
        List<DbModel> dbInputData = new ArrayList<>();
        RandomValuesProvider randomValuesProvider = RandomValuesProvider.getInstance();

        List<Integer> obszary = new ArrayList<>();
        List<Integer> zwierzeta = new ArrayList<>();
        List<Integer> szkolki = new ArrayList<>();
        List<Integer> dostawcy = new ArrayList<>();
        List<Integer> wycinki = new ArrayList<>();
        List<Integer> klienci = new ArrayList<>();
        List<String> pracownicy = new ArrayList<>();

        long start = System.currentTimeMillis();
        IntStream.range(0, 100000).forEach(i -> {
            String pesel = randomValuesProvider.getRandomPesel();
            dbInputData.add(new Pracownik(pesel, randomValuesProvider.getRandomName(), randomValuesProvider.getRandomSurname(), randomValuesProvider.getRandomAddress(), randomValuesProvider.getRandomPhoneNumber(), randomValuesProvider.getRandomSalary()));
            pracownicy.add(pesel);
            dbInputData.add(new Klient(i, randomValuesProvider.getRandomName(), randomValuesProvider.getRandomSurname(), randomValuesProvider.getRandomAddress(), randomValuesProvider.getRandomPhoneNumber()));
            klienci.add(i);
            dbInputData.add(new Dostawca(i, randomValuesProvider.getRandomCompanyName(), randomValuesProvider.getRandomAddress()));
            dostawcy.add(i);
            dbInputData.add(new Zwierze(i, randomValuesProvider.getRandomForestAnimal(), randomValuesProvider.getRandomGender()));
            zwierzeta.add(i);
            dbInputData.add(new Obszar(i, randomValuesProvider.getRandomAreaSize()));
            obszary.add(i);
            dbInputData.add(new Szkolka(i, randomValuesProvider.getRandomTreesCount(), randomValuesProvider.getRandomForestType()));
            szkolki.add(i);
        });

        IntStream.range(0, 200000).forEach(i -> {
            dbInputData.add(new Zerowisko(i, zwierzeta.get(random.nextInt(zwierzeta.size())), obszary.get(random.nextInt(obszary.size()))));
            dbInputData.add(new Dostawa(i, szkolki.get(random.nextInt(szkolki.size())), dostawcy.get(random.nextInt(dostawcy.size())), randomValuesProvider.getRandomDate()));
        });

        szkolki.stream().forEach(i -> {
            dbInputData.add(new Przynaleznosc(i, obszary.get(random.nextInt(obszary.size()))));
            dbInputData.add(new Wycinka(i, randomValuesProvider.getRandomDate(), random.nextInt(80) + 20, i));
            wycinki.add(i);
        });

        wycinki.stream().forEach(i -> {
            int amount = random.nextInt(50) + 5;
            int price = random.nextInt(10) + 10;
            dbInputData.add(new Transakcja(i, i, klienci.get(random.nextInt(klienci.size())), String.valueOf(amount), String.valueOf(price), String.valueOf(amount * price)));
            dbInputData.add(new Zlecenie(i, pracownicy.get(random.nextInt(pracownicy.size()))));
            dbInputData.add(new Zlecenie(i, pracownicy.get(random.nextInt(pracownicy.size()))));
        });

        ScriptAsFileSaver.saveAsScript(dbInputData);
        dbHandler.insertData(dbInputData);
//        dbHandler.clearAllData();

        System.out.println("records=" + dbInputData.size());
        System.out.print("duration=" + (System.currentTimeMillis() - start));


        //////////////////////////////////////////////////////////////////
        //select test
//        List<DbModel> employeeslist = dbHandler.simpleSelect("pracownik");
//        List<DbModel> customersList = dbHandler.simpleSelect("klient");
//
//        List<DbModel> employeeslista = employeeslist.stream()
//                .filter(e -> (float) e.getValue(Pracownik.STAWKA_GODZINOWA) > 10)
//                .collect(Collectors.toList());

        dbHandler.disconnect();
    }
}
