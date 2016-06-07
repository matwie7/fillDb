package fill_db.models;

import java.sql.ResultSet;

/**
 * Dostawca persistence classk
 * Created by Development on 20.04.2016.
 */
public class Dostawca extends DbModel {
    public final static String ID_DOSTAWCY = "id_dostawcy";
    public final static String NAZWA_FIRMY = "nazwa_firmy";
    public final static String ADRES_FIRMY = "adres_firmy";

    public Dostawca(int idDostawcy, String nazwaFirmy, String adresFirmy) {
        super();
        fields.put(ID_DOSTAWCY, idDostawcy);
        fields.put(NAZWA_FIRMY, nazwaFirmy);
        fields.put(ADRES_FIRMY, adresFirmy);
    }

    public Dostawca(ResultSet resultSet) {
        super(resultSet);
    }

    protected void init() {
        tableName = "dostawca";
    }
}
