package models;

import java.sql.ResultSet;

/**
 * transakcja
 * Created by Development on 23.04.2016.
 */
public class Transakcja extends DbModel {
    public final static String ID_TRANSAKCJI = "id_transakcji";
    public final static String ID_WYCINKI = "id_wycinki_FK3";
    public final static String PESEL_KLIENTA = "pesel_klienta_FK";
    public final static String ILOSC_DREWNA = "ilosc_drewna";
    public final static String CENA_ZA_M3 = "cena_za_m3";
    public final static String SUMA_BRUTTO = "suma_brutto";


    public Transakcja(int idTransakcji, int idWycinki, int peselKlienta, String iloscDrewna, String cenaZaM3, String sumaBrutto) {
        super();
        fields.put(ID_TRANSAKCJI, idTransakcji);
        fields.put(ID_WYCINKI, idWycinki);
        fields.put(PESEL_KLIENTA, peselKlienta);
        fields.put(ILOSC_DREWNA, iloscDrewna);
        fields.put(CENA_ZA_M3, cenaZaM3);
        fields.put(SUMA_BRUTTO, sumaBrutto);
    }

    public Transakcja(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    protected void init() {
        tableName = "transakcja";
    }
}
