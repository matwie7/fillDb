package models;

import java.sql.ResultSet;

/**
 * Szkolka persistence class
 * Created by Development on 22.04.2016.
 */
public class Szkolka extends DbModel {
    public final static String NR_EWIDENCYJNY = "nr_ewidencyjny";
    public final static String ILOSC_DRZEWEK = "ilosc_drzewek";
    public final static String TYP_DRZEWEK = "typ_drzewek";

    public Szkolka(int nrEwidencyjny, int iloscDrzewek, String typDrzewek) {
        super();
        fields.put(NR_EWIDENCYJNY, nrEwidencyjny);
        fields.put(ILOSC_DRZEWEK, iloscDrzewek);
        fields.put(TYP_DRZEWEK, typDrzewek);
    }

    public Szkolka(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    protected void init() {
        tableName = "szkolka";
    }
}
