package fill_db.models;

import java.sql.ResultSet;

/**
 * Przynaleznosc persistence class
 * Created by M on 23.04.2016.
 */
public class Przynaleznosc extends DbModel {
    public final static String NR_EWIDENCYJNY = "nr_ewidencyjny_FK";
    public final static String NR_DZIALKI = "nr_dzialki_FK";

    public Przynaleznosc(int nrEwidencyjny, int nrDzialki) {
        super();
        fields.put(NR_EWIDENCYJNY, nrEwidencyjny);
        fields.put(NR_DZIALKI, nrDzialki);
    }

    public Przynaleznosc(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    protected void init() {
        tableName = "przynaleznosc";
    }
}
