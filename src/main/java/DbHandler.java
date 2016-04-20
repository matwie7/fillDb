import models.DbModel;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DbHandler {
    private Connection connection = null;
    private Statement stmt = null;

    public void insertData(DbModel model) {
        String insertPattern = "INSERT INTO {0} ({1}) VALUES ({2});";
        ImmutablePair<List<String>, List<String>> keysAndValues = model.getKeysAndValues();
        String keys = String.join(", ", keysAndValues.getLeft());
        String values = String.join(", ", keysAndValues.getRight());

        String sql = MessageFormat.format(insertPattern, model.getTableName(), keys, values);

        try {
            stmt.executeUpdate(sql);
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

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found?");
            e.printStackTrace();
            return;
        }

        System.out.println("MySQL JDBC Driver Registered");

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/testdb", "mat777", "zaq1@WSX");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Failed to make connection!");
        }

        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getClassName(String tableName) {
        String namePattern = "models.{0}";
        char[] string = tableName.toCharArray();
        string[0] = Character.toUpperCase(string[0]);
        return MessageFormat.format(namePattern, new String(string));
    }
}