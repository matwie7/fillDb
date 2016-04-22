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
        dbHandler.connect();
        List<DbModel> dbInputData = new ArrayList<>();
        RandomValuesProvider randomValuesProvider = RandomValuesProvider.getInstance();

        List<Integer> obszary = new ArrayList<>();
        List<Integer> zwierzeta = new ArrayList<>();
        List<Integer> szkolki = new ArrayList<>();
        List<Integer> dostawcy = new ArrayList<>();

        long start = System.currentTimeMillis();
        IntStream.range(0, 10).forEach(i -> {
            dbInputData.add(new Pracownik(randomValuesProvider.getRandomPesel(), randomValuesProvider.getRandomName(), randomValuesProvider.getRandomSurname(), randomValuesProvider.getRandomAddress(), randomValuesProvider.getRandomPhoneNumber(), randomValuesProvider.getRandomSalary()));
            dbInputData.add(new Klient(String.valueOf(i), randomValuesProvider.getRandomName(), randomValuesProvider.getRandomSurname(), randomValuesProvider.getRandomAddress(), randomValuesProvider.getRandomPhoneNumber()));
            dbInputData.add(new Dostawca(i, randomValuesProvider.getRandomCompanyName(), randomValuesProvider.getRandomAddress()));
            dostawcy.add(i);
            dbInputData.add(new Zwierze(i, randomValuesProvider.getRandomForestAnimal(), randomValuesProvider.getRandomGender()));
            zwierzeta.add(i);
            dbInputData.add(new Obszar(i, randomValuesProvider.getRandomAreaSize()));
            obszary.add(i);
            dbInputData.add(new Szkolka(i, randomValuesProvider.getRandomTreesCount(), randomValuesProvider.getRandomForestType()));
            szkolki.add(i);

        });


        IntStream.range(0, 20).forEach(i -> {
            dbInputData.add(new Zerowisko(i, zwierzeta.get(random.nextInt(zwierzeta.size())), obszary.get(random.nextInt(obszary.size()))));
            dbInputData.add(new Dostawa(i, szkolki.get(random.nextInt(szkolki.size())), dostawcy.get(random.nextInt(dostawcy.size())), randomValuesProvider.getRandomDate()));
        });
        szkolki.stream().forEach(i -> dbInputData.add(new Przynaleznosc(i, obszary.get(random.nextInt(obszary.size())))));


        dbHandler.insertData(dbInputData);
//        dbHandler.clearAllData();

        System.out.print("duration=" + (System.currentTimeMillis() - start));


        //////////////////////////////////////////////////////////////////
        //select test
        List<DbModel> employeeslist = dbHandler.simpleSelect("pracownik");
//        List<DbModel> customersList = dbHandler.simpleSelect("klient");
//
        List<DbModel> employeeslista = employeeslist.stream()
                .filter(e -> (float) e.getValue(Pracownik.STAWKA_GODZINOWA) > 10)
                .collect(Collectors.toList());

        dbHandler.disconnect();
    }
}
