package fill_db.models;

import java.sql.ResultSet;

/**
 * Żeroswisko persistance class
 * Created by M on 21.04.2016.
 */
public class Zerowisko extends DbModel {
    public final static String ID_ZWIERZECIA = "id_zwierzecia_FK";
    public final static String NR_DZIALKI = "nr_dzialki_FK";
    public final static String ID_ZEROWISKA = "id_zerowiska";


    public Zerowisko(int idZerowiska, int idZwierzecia, int nrDzialki) {
        super();
        fields.put(ID_ZEROWISKA, idZerowiska);
        fields.put(ID_ZWIERZECIA, idZwierzecia);
        fields.put(NR_DZIALKI, nrDzialki);
    }

    public Zerowisko(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    protected void init() {
        tableName = "zerowisko";
    }
}
