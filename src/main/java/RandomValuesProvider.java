import java.io.*;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * dasz
 * Created by Developmenton 13.04.2016.
 */
public class RandomValuesProvider {
    private List<String> names;
    private List<String> surnames;
    private List<String> addresses;
    private List<String> companyNames;
    private List<String> forestAnimals;
    private List<String> forestTypes;

    private Random random;
    private static RandomValuesProvider randomValuesProvider = null;

    public static RandomValuesProvider getInstance() {
        if (randomValuesProvider == null)
            randomValuesProvider = new RandomValuesProvider();
        return randomValuesProvider;
    }

    private RandomValuesProvider() {
        random = new Random(System.nanoTime());
        names = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("res/PolandFNames.dat"))) {
            for (String line; (line = br.readLine()) != null; )
                names.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        surnames = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("res/PolandLNames.dat"))) {
            for (String line; (line = br.readLine()) != null; )
                surnames.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        addresses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("res/adresy.txt"))) {
            for (String line; (line = br.readLine()) != null; )
                addresses.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        companyNames = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("res/companyNames.txt"))) {
            for (String line; (line = br.readLine()) != null; )
                companyNames.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        forestAnimals = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("res/forestAnimals.txt"))) {
            for (String line; (line = br.readLine()) != null; )
                forestAnimals.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        forestTypes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("res/forestTypes.txt"))) {
            for (String line; (line = br.readLine()) != null; )
                forestTypes.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRandomName() {
        return names.get(random.nextInt(names.size()));
    }

    public String getRandomSurname() {
        return surnames.get(random.nextInt(surnames.size()));
    }

    public String getRandomAddress() {
        return addresses.get(random.nextInt(addresses.size()));
    }

    public String getRandomCompanyName() {
        return companyNames.get(random.nextInt(companyNames.size()));
    }

    public String getRandomForestAnimal() {
        return forestAnimals.get(random.nextInt(forestAnimals.size()));
    }

    public String getRandomForestType() {
        return forestTypes.get(random.nextInt(forestTypes.size()));
    }

    public String getRandomPhoneNumber() {
        String phoneNumberPattern = "+{0} {1}";
        return MessageFormat.format(phoneNumberPattern, random.nextInt(80), String.valueOf(100000000 + (int) (Math.random() * (999999999 - 100000000))));
    }

    public String getRandomGender() {
        if (random.nextBoolean()) return "M";
        else return "F";
    }

    public int getRandomAreaSize() {
        return random.nextInt(100) + 20;
    }

    public float getRandomSalary() {
        return (random.nextFloat() * 10) + 5;
    }

    public int getRandomTreesCount() {
        return random.nextInt(900) + 100;
    }

    public String getRandomDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, random.nextInt(80));
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(cal.getTime());
    }

    public String getRandomPesel() {
        int wagi[] = new int[]{1, 3, 7, 9, 1, 3, 7, 9, 1, 3};

        int rok = (int) (Math.random() * 100);
        int miesiac = (int) (Math.random() * 12 + 1);
        int dzien = (int) (Math.random() * 28 + 1); // uproszczone
        int numerEwidencyjny = (int) (Math.random() * 999);
        int plec = (int) (Math.random() * 9);

        String pesel = String.format("%02d%02d%02d%03d%d", rok, miesiac, dzien, numerEwidencyjny, plec);
        char[] peselElem = pesel.toCharArray();
        int sumaKontrolna = 0;
        for (int i = 0; i < wagi.length; i++) {
            sumaKontrolna += wagi[i] * Integer.parseInt("" + peselElem[i]);
        }
        return pesel + (sumaKontrolna % 10);
    }
}
