package fill_db;

import fill_db.models.DbModel;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Saving queries into file
 * Created by Development on 27.04.2016.
 */
public class ScriptAsFileSaver {
    public static void saveAsScript(List<DbModel> inputData) {
        try (PrintWriter out = new PrintWriter("script.txt")) {
            inputData.stream().forEach(record -> out.println(SQLHelper.createInsertQuery(record)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
