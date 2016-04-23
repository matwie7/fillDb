package models;

import java.sql.ResultSet;

/**
 * Zlecenie
 * Created by Development on 23.04.2016.
 */
public class Zlecenie extends DbModel {

    public final static String ID_WYCINKI = "id_wycinki_FK4";
    public final static String NR_PESEL = "nr_pesel_FK";

    public Zlecenie(int idWycinki, String pesel) {
        super();
        fields.put(ID_WYCINKI, idWycinki);
        fields.put(NR_PESEL, pesel);
    }

    public Zlecenie(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    protected void init() {
        tableName = "zlecenie";
    }
}
