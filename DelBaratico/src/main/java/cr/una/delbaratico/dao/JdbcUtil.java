package main.java.cr.una.delbaratico.dao;

import java.sql.*;

public class JdbcUtil {

    private static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static Connection dbConnection;
    private static boolean incorrectConnection;
    private static JdbcUtil jdbcUtil;
    private static Statement statement;
    private static ResultSet resultSet;

    private JdbcUtil(String DB_USER, String DB_PASSWORD) {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        this.DB_USER = DB_USER;
        this.DB_PASSWORD = DB_PASSWORD;
        dbConnection = getDBConnection();
        try{
            if(dbConnection != null)
                statement = dbConnection.createStatement();
        } catch (Exception e) {
            System.err.println(methodName + " - " + e.getMessage());
        }
    }

    public boolean isThereAConnection(){
        return (dbConnection!=null)?true:false;
    }

    public static JdbcUtil instance(String DB_USER, String DB_PASSWORD) {
        cerrarConexion();
        jdbcUtil = new JdbcUtil(DB_USER, DB_PASSWORD);
        return jdbcUtil;
    }

    public static void cerrarConexion(){
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        try {
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(dbConnection != null){
                dbConnection.close();
            }
        } catch (Exception e) {
            System.err.println(methodName + " - " + e.getMessage());
        }
    }

    public static Connection getDBConnection() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        try {
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            System.err.println(" - " + methodName + " - " + e.getMessage());
        }
        return null;
    }

    public static void closeConnection() {
        jdbcUtil = null;
    }

    public int executeUpdate(String query) {
        try {
            statement = dbConnection.createStatement();
            statement.executeUpdate(query);
            return statement.getUpdateCount();
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return 0;
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            System.out.println(query);
            statement = dbConnection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
        return null;
    }

    public int executeAddAI(String query) {
        try {
            statement = dbConnection.createStatement();
            statement.executeUpdate(query);
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next())
                return resultSet.getInt(1);
            return 0;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return 0;
        }
    }

}
