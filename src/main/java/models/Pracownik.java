package models;

import java.sql.ResultSet;

/**
 * Pracownik - model
 * Created by Development on 13.04.2016.
 */
public class Pracownik extends DbModel {
    public final static String NR_PRACOWNIKA = "nr_pesel_pracownika";
    public final static String IMIE = "imie";
    public final static String NAZWISKO = "nazwisko";
    public final static String ADRES = "adres";
    public final static String NR_TELEFONU = "nr_telefonu";
    public final static String STAWKA_GODZINOWA = "stawka_godzinowa";

    public Pracownik(String nr_pesel_pracownika, String imie, String nazwisko, String adres, String nr_telefonu, float stawka_godzinowa) {
        super();
        fields.put(NR_PRACOWNIKA, nr_pesel_pracownika);
        fields.put(IMIE, imie);
        fields.put(NAZWISKO, nazwisko);
        fields.put(ADRES, adres);
        fields.put(NR_TELEFONU, nr_telefonu);
        fields.put(STAWKA_GODZINOWA, stawka_godzinowa);
    }

    public Pracownik(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    protected void init() {
        tableName = "pracownik";
    }
}
