import models.*;

import java.util.ArrayList;
import java.util.List;
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
        DbHandler dbHandler = new DbHandler();
        dbHandler.connect();
        List<DbModel> dbInputData = new ArrayList<>();
        RandomValuesProvider randomValuesProvider = RandomValuesProvider.getInstance();

        long start = System.currentTimeMillis();
        IntStream.range(0, 15).forEach(i -> {
//            dbHandler.insertData(new Pracownik(randomValuesProvider.getRandomPesel(), randomValuesProvider.getRandomName(), randomValuesProvider.getRandomSurname(), randomValuesProvider.getRandomAddress(), randomValuesProvider.getRandomPhoneNumber(), randomValuesProvider.getRandomSalary()));
            dbInputData.add(new Pracownik(randomValuesProvider.getRandomPesel(), randomValuesProvider.getRandomName(), randomValuesProvider.getRandomSurname(), randomValuesProvider.getRandomAddress(), randomValuesProvider.getRandomPhoneNumber(), randomValuesProvider.getRandomSalary()));
            dbInputData.add(new Klient(String.valueOf(i), randomValuesProvider.getRandomName(), randomValuesProvider.getRandomSurname(), randomValuesProvider.getRandomAddress(), randomValuesProvider.getRandomPhoneNumber()));
            dbInputData.add(new Dostawca(i, randomValuesProvider.getRandomCompanyName(), randomValuesProvider.getRandomAddress()));
            dbInputData.add(new Zwierze(i, randomValuesProvider.getRandomForestAnimal(), randomValuesProvider.getRandomGender()));
            dbInputData.add(new Obszar(i, randomValuesProvider.getRandomAreaSize()));
            //            dbHandler.insertData(new Klient(String.valueOf(i), randomValuesProvider.getRandomName(), randomValuesProvider.getRandomSurname(), randomValuesProvider.getRandomAddress(), randomValuesProvider.getRandomPhoneNumber()));
        });
        dbHandler.insertData(dbInputData);

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
