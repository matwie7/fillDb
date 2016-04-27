import models.DbModel;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by Development on 27.04.2016.
 */
public class SQLHelper {

    public static String createInsertQuery(DbModel model) {
        String insertPattern = "INSERT INTO {0} ({1}) VALUES ({2});";
        ImmutablePair<List<String>, List<String>> keysAndValues = model.getKeysAndValues();
        String keys = String.join(", ", keysAndValues.getLeft());
        String values = String.join(", ", keysAndValues.getRight());

        return MessageFormat.format(insertPattern, model.getTableName(), keys, values);
    }
}
