package fill_db.models;

import java.sql.ResultSet;

/**
 * wycinka
 * Created by Development on 23.04.2016.
 */
public class Wycinka extends DbModel {
    public final static String ID_WYCINKI = "id_wycinki";
    public final static String DATA_WYCINKI = "data_wycinki";
    public final static String ILOSC_GODZIN_ROBOCZYCH = "ilosc_godzin_roboczych";
    public final static String NR_EWIDENCYJNY_SZKOLKI = "nr_ewidencyjny_FK3";


    public Wycinka(int idWycinki, String dataWycinki, int iloscGodzinRoboczych, int nrEwidencyjnySzkolki) {
        super();
        fields.put(ID_WYCINKI, idWycinki);
        fields.put(DATA_WYCINKI, dataWycinki);
        fields.put(ILOSC_GODZIN_ROBOCZYCH, iloscGodzinRoboczych);
        fields.put(NR_EWIDENCYJNY_SZKOLKI, nrEwidencyjnySzkolki);
    }

    public Wycinka(ResultSet resultSet) {
        super(resultSet);
    }

    @Override
    protected void init() {
        tableName = "wycinka";
    }
}
