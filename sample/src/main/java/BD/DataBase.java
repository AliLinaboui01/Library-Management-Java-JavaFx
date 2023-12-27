package BD;
import java.sql.*;

public class DataBase {
    Connection conn = null;


    public DataBase(){}
    private static final String URL = "jdbc:mysql://localhost:3306/javaproject";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";



    public Connection connect(){
        Connection conn =null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("good job success connection ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
