import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionHandlingJDBC {
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb";
    static final String USER = "your_username";
    static final String PASSWORD = "your_password";

    public void transferMoney(int fromAccountId, int toAccountId, double amount) { // cite: 84
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            conn.setAutoCommit(false); // Disable auto-commit for transaction [cite: 84]

            // Debit from source account
            String debitSql = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(debitSql)) {
                pstmt.setDouble(1, amount);
                pstmt.setInt(2, fromAccountId);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("Source account not found or insufficient funds.");
                }
            }

            // Simulate an error to test rollback
            // if (fromAccountId == 1) {
            //     throw new SQLException("Simulating error for testing rollback.");
            // }

            // Credit to destination account
            String creditSql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(creditSql)) {
                pstmt.setDouble(1, amount);
                pstmt.setInt(2, toAccountId);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("Destination account not found.");
                }
            }

            conn.commit(); // Commit if both succeed [cite: 84]
            System.out.println("Money transferred successfully from " + fromAccountId + " to " + toAccountId);

        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback(); // Rollback if any error occurs [cite: 84]
                    System.out.println("Transaction rolled back: " + e.getMessage());
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true); // Re-enable auto-commit
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TransactionHandlingJDBC transaction = new TransactionHandlingJDBC();

        // Successful transfer
        System.out.println("Attempting successful transfer...");
        transaction.transferMoney(1, 2, 100.00);

        // Attempting transfer with potential error (e.g., non-existent account or insufficient funds)
        System.out.println("\nAttempting failed transfer (e.g., to non-existent account)...");
        transaction.transferMoney(1, 99, 50.00);
    }
}