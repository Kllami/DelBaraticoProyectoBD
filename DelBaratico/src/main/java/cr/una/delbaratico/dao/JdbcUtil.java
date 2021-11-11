package main.java.cr.una.delbaratico.dao;

import java.sql.*;
import java.util.Properties;


public class JdbcUtil {

    private static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String DB_USER;
    private static String DB_PASSWORD;

    private static Connection dbConnection;

    private JdbcUtil(String DB_USER, String DB_PASSWORD) {
        this.DB_USER = DB_USER;
        this.DB_PASSWORD = DB_PASSWORD;
        dbConnection = getDBConnection();
    }

    private static JdbcUtil jdbcUtil;

    public static JdbcUtil instance(String DB_USER, String DB_PASSWORD) {
        if (jdbcUtil == null) {
            jdbcUtil = new JdbcUtil(DB_USER, DB_PASSWORD);
        }
        return jdbcUtil;
    }

    public static Connection getDBConnection() {
        try {
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static void closeConnection() {
        jdbcUtil = null;
    }

    public int executeUpdate(String query) {
        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(query);
            return statement.getUpdateCount();
        } catch (SQLException ex) {
            return 0;
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            Statement statement = dbConnection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException ex) {
        }
        return null;
    }

    public int executeAddAI(String query) {
        try {
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(query);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next())
                return resultSet.getInt(1);
            return 0;
        } catch (SQLException ex) {
            return 0;
        }
    }

    public static boolean verifyConnection() {
        return dbConnection != null;
    }
}
