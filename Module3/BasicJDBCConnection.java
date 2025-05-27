import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasicJDBCConnection {
    // JDBC URL for MySQL (adjust if using SQLite or another database)
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb"; // Replace testdb with your database name
    static final String USER = "your_username"; // Replace with your database username
    static final String PASSWORD = "your_password"; // Replace with your database password

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Load the JDBC driver (not strictly necessary for modern JDBC but good practice) [cite: 78]
            Class.forName("com.mysql.cj.jdbc.Driver"); // For MySQL

            // Create a connection [cite: 78]
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // Execute a SELECT query [cite: 78]
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT id, name, age FROM students";
            rs = stmt.executeQuery(sql);

            // Print results [cite: 78]
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
            } catch (SQLException se2) {
                // do nothing
            }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
                // do nothing
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}