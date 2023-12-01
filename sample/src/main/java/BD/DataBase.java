package BD;
import java.sql.*;

public class DataBase {
    Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/connect";
    String user = "root";
    String password = "";
    public DataBase(){}
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 Statement st = conn.createStatement()) {

                String query = "SELECT uname FROM students WHERE id=1";
                ResultSet rs = st.executeQuery(query);

                if (rs.next()) {
                    String name = rs.getString("uname");
                    System.out.println("Success connection");
                    System.out.println("Name: " + name);
                } else {
                    System.out.println("No data found for id=1");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Failed connection");
        }
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
