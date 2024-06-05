package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3307/foodstuff";
    private static final String USER = "root";
    private static final String PASSWORD = "123abhijith456";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection successful");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    public static boolean testConnection() {
        try (Connection conn = connect()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Database connection is successful.");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Failed to check connection: " + e.getMessage());
        }
        System.out.println("Database connection failed.");
        return false;
    }

    public static void createUser(String name, String phoneNo, String username, String address, String password, String dob, String email) {
        String sql = "INSERT INTO users (name, phone, username, address, password, dob, email) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, phoneNo);
            pstmt.setString(3, username);
            pstmt.setString(4, address);
            pstmt.setString(5, password);
            pstmt.setString(6, dob);
            pstmt.setString(7, email);

            pstmt.executeUpdate();
            System.out.println("User created successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        boolean isConnected = testConnection();
        System.out.println("Connection test result: " + isConnected);
        
    }
}
