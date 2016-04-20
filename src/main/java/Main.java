import models.DbModel;
import models.Klient;
import models.Pracownik;


import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Main class
 * Created by Development on 13.04.2016.
 */
public class Main {
    public static void main(String[] args) {
        doTHings();
    }


    public static void doTHings() {
        DbHandler dbHandler = new DbHandler();
        dbHandler.connect();
        RandomValuesProvider randomValuesProvider = RandomValuesProvider.getInstance();

        long start = System.currentTimeMillis();
        IntStream.range(0, 10000).forEach(i -> {
//            dbHandler.insertData(new Pracownik(randomValuesProvider.getRandomPesel(), randomValuesProvider.getRandomName(), randomValuesProvider.getRandomSurname(), randomValuesProvider.getRandomAddress(), randomValuesProvider.getRandomPhoneNumber(), randomValuesProvider.getRandomSalary()));
//            dbHandler.insertData(new Klient(String.valueOf(i), randomValuesProvider.getRandomName(), randomValuesProvider.getRandomSurname(), randomValuesProvider.getRandomAddress(), randomValuesProvider.getRandomPhoneNumber()));
            if (i % 100 == 0)
                System.out.println(MessageFormat.format("inserted items: {0}, in: {1} miliseconds", i, System.currentTimeMillis() - start));
        });
        System.out.print("duration=" + (System.currentTimeMillis() - start));

        List<DbModel> employeeslist = dbHandler.simpleSelect("pracownik");
//        List<DbModel> customersList = dbHandler.simpleSelect("klient");
//
        List<DbModel> employeeslista = employeeslist.stream()
                .filter(e -> (float) e.getValue(Pracownik.STAWKA_GODZINOWA) > 10)
                .collect(Collectors.toList());

        dbHandler.disconnect();
    }
}
