package fill_db.models;

import java.sql.ResultSet;
import java.util.Map;

/**
 *
 * Created by M on 23.04.2016.
 */
public class Dostawa extends DbModel {
    public final static String ID_DOSTAWY = "id_dostawy";
    public final static String NR_EWIDENCYJNY = "nr_ewidencyjny_FK2";
    public final static String NR_DOSTAWCY = "id_dostawcy_FK";
    public final static String DATA_DOSTAWY = "data_dostawy";

    public Dostawa(int idDostawy, int nrEwidencyjny, int nrDostawcy, String dataDostawy) {
        super();
        fields.put(ID_DOSTAWY, idDostawy);
        fields.put(NR_EWIDENCYJNY, nrEwidencyjny);
        fields.put(NR_DOSTAWCY, nrDostawcy);
        fields.put(DATA_DOSTAWY, dataDostawy);
    }

    public Dostawa(ResultSet resultSet) {
        super(resultSet);
    }

    public Dostawa(Map fields) {
        super(fields);
    }

    @Override
    protected void init() {
        tableName = "dostawa";
    }
}
