package fill_db.models;

import java.sql.ResultSet;
import java.util.Map;

public class Klient extends DbModel {
    public final static String PESEL = "pesel";
    public final static String IMIE = "imie";
    public final static String NAZWISKO = "nazwisko";
    public final static String ADRES = "adres";
    public final static String NR_TELEFONU = "nr_telefonu";

    public Klient(String pesel, String imie, String nazwisko, String adres, String nr_telefonu) {
        super();
        fields.put(PESEL, pesel);
        fields.put(IMIE, imie);
        fields.put(NAZWISKO, nazwisko);
        fields.put(ADRES, adres);
        fields.put(NR_TELEFONU, nr_telefonu);
    }

    @Override
    protected void init() {
        tableName = "klient";
    }

    public Klient(ResultSet resultSet) {
        super(resultSet);
    }

    public Klient(Map fields) {
        super(fields);
    }
}
