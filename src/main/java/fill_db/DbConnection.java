package fill_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database connection
 * Created by M on 23.04.2016.
 */
public class DbConnection {
    private Connection connection;
    private Statement stmt;

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found?");
            e.printStackTrace();
            return null;
        }

        System.out.println("MySQL JDBC Driver Registered");

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/testdb", "mat777", "zaq1@WSX");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }

        if (connection != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }

    public Statement getStatement() {
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }
}
