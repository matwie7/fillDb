package models;


import org.apache.commons.lang3.tuple.ImmutablePair;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * DbModel abstract class
 * Created by Development on 13.04.2016.
 */
public abstract class DbModel {
    protected Map<String, Object> fields;
    protected String tableName;

    public DbModel() {
        fields = new HashMap<>();
        init();
    }

    public DbModel(ResultSet resultSet) {
        this();
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++)
                switch (resultSetMetaData.getColumnType(i)) {
                    case Types.VARCHAR:
                        fields.put(resultSetMetaData.getColumnName(i), resultSet.getString(i));
                        break;
                    case Types.INTEGER:
                        fields.put(resultSetMetaData.getColumnName(i), resultSet.getInt(i));
                        break;
                    case Types.REAL:
                        fields.put(resultSetMetaData.getColumnName(i), resultSet.getFloat(i));
                        break;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTableName() {
        return tableName;
    }

    public Object getValue(String key) {
        return fields.get(key);
    }

    public void setValue(String key, String value) {
        if (fields.containsKey(key))
            fields.replace(key, value);
        else
            fields.put(key, value);
    }

    public ImmutablePair<List<String>, List<String>> getKeysAndValues() {
        List<String> valuesList = new ArrayList<>();
        List<String> keysList = new ArrayList<>();
        String stringPattern = "''{0}''";
        for (Map.Entry<String, Object> entry : fields.entrySet()) {
            keysList.add(entry.getKey());
            if (entry.getValue() instanceof String)
                valuesList.add(MessageFormat.format(stringPattern, entry.getValue()));
            else
                valuesList.add(entry.getValue().toString());
        }
        return new ImmutablePair<>(keysList, valuesList);
    }

    protected abstract void init();
}
