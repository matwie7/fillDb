import models.DbModel;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

public class DbHandler {
    private Statement stmt;
    private DbConnection dbConnection;

    public DbHandler() {
        dbConnection = new DbConnection();
        dbConnection.connect();
        stmt = dbConnection.getStatement();
    }

    public void disconnect() {
        dbConnection.disconnect();
    }

    public void insertData(DbModel model) {
        performStatement(SQLHelper.createInsertQuery(model));
    }

    public void insertData(List<DbModel> data) {
        data.stream().forEach(record -> {
            try {
                stmt.addBatch(SQLHelper.createInsertQuery(record));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        performStatement();
    }

    private void performStatement(String SQLstatement) {
        try {
            stmt.execute(SQLstatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearAllData() {
        try {
            stmt.addBatch("DELETE FROM zerowisko;");
            stmt.addBatch("DELETE FROM przynaleznosc;");
            stmt.addBatch("DELETE FROM transakcja;");
            stmt.addBatch("DELETE FROM zlecenie;");
            stmt.addBatch("DELETE FROM dostawa;");
            stmt.addBatch("DELETE FROM dostawca;");
            stmt.addBatch("DELETE FROM klient;");
            stmt.addBatch("DELETE FROM wycinka;");
            stmt.addBatch("DELETE FROM obszar;");
            stmt.addBatch("DELETE FROM pracownik;");
            stmt.addBatch("DELETE FROM szkolka;");
            stmt.addBatch("DELETE FROM zwierze;");
            stmt.executeBatch();
            stmt.clearBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void performStatement() {
        try {
            stmt.executeBatch();
            stmt.clearBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public <T extends DbModel> List<DbModel> simpleSelect(String tableName) {
        String selectPattern = "SELECT {0} FROM {1};";
        String sql = MessageFormat.format(selectPattern, "*", tableName);
        List<DbModel> results = new LinkedList<>();

        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Class<T> clazz = (Class<T>) Class.forName(getClassName(tableName));
                Constructor<T> constructor = clazz.getConstructor(ResultSet.class);
                results.add(constructor.newInstance(rs));
            }
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return results;
    }


    private String getClassName(String tableName) {
        String namePattern = "models.{0}";
        char[] string = tableName.toCharArray();
        string[0] = Character.toUpperCase(string[0]);
        return MessageFormat.format(namePattern, new String(string));
    }
}
