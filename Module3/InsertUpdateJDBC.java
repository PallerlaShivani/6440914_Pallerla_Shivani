import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class StudentDAO {
static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb";
static final String USER = "your_username";
static final String PASSWORD = "your_password";

public void insertStudent(String name, int age) { // cite: 81
    String sql = "INSERT INTO students (name, age) VALUES (?, ?)"; // cite: 82
    try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(sql)) { // cite: 82

        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " row(s) inserted.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void updateStudentAge(int studentId, int newAge) { // cite: 81
    String sql = "UPDATE students SET age = ? WHERE id = ?"; // cite: 82
    try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(sql)) { // cite: 82

        pstmt.setInt(1, newAge);
        pstmt.setInt(2, studentId);
        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " row(s) updated.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}

public class InsertUpdateJDBC {
public static void main(String[] args) {
StudentDAO studentDao = new StudentDAO();

    // Insert new records [cite: 81]
    System.out.println("Inserting new student...");
    studentDao.insertStudent("Charlie", 25);

    // Update student details [cite: 81]
    System.out.println("Updating student age...");
    // Assuming student with ID 1 exists
    studentDao.updateStudentAge(1, 21);
}
}