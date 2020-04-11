import java.sql.*;

public class Database {
    private static Database database=null;
    private static Connection connection=null;
    private Database() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            connection=createConnection();
        }
        return connection;
    }

    public static Database getDatabase() {
        if (database == null) {
            database= new Database(); }
            return database;
    }

    static Connection createConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
           return DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "student", "student");

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

