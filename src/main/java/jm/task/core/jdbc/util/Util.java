package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String USERNAME = "Admin";
    private static final String PASSWORD = "1234";
    private static final String URL = "jdbc:mysql://localhost:3306/name_db";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Подключение не было выполнено");
        }
        return connection;
    }
}
