package fill_db.models;

import java.sql.ResultSet;

/**
 * Zwierze
 * Created by Development on 21.04.2016.
 */
public class Zwierze extends DbModel {
    public final static String ID_ZWIERZECIA = "id_zwierzecia";
    public final static String GATUNEK = "gatunek";
    public final static String PLEC = "plec";

    public Zwierze(int idZwierzecia, String gatunek, String plec) {
        super();
        fields.put(ID_ZWIERZECIA, idZwierzecia);
        fields.put(GATUNEK, gatunek);
        fields.put(PLEC, plec);
    }

    public Zwierze(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    protected void init() {
        tableName = "zwierze";
    }
}
