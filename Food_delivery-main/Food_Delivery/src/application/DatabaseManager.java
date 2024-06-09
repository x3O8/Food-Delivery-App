package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3307/foodstuff";
    private static final String USER = "root";
    private static final String PASSWORD = "";

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

    public static void createUser(String name, String phone_no, String username, String address, String password, String dob, String email) {
        String sql = "INSERT INTO users (name, phone_no, username, address, password, dob, email) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, phone_no);
            pstmt.setString(3, username);
            pstmt.setString(4, address);
            pstmt.setString(5, password);
            pstmt.setString(6, dob);
            pstmt.setString(7, email);

            pstmt.executeUpdate();
            System.out.println("User created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    public static boolean userExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error checking user existence: " + e.getMessage());
        }
        return false;
    }

    public static boolean validateUser(String username, String password) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND BINARY password = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error validating user: " + e.getMessage());
        }
        return false;
    }

    public static void updateUserField(String username, String field, String newValue) {
        String sql = "UPDATE users SET " + field + " = ? WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newValue);
            pstmt.setString(2, username);
            pstmt.executeUpdate();
            System.out.println("User field updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating user field: " + e.getMessage());
        }
    }

    public static void addFeedback(String name, String feedback, String email) {
        String sql = "INSERT INTO feedback (name, feedback, email) VALUES (?, ?, ?)";
        
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setString(2, feedback);
            pstmt.setString(3, email);
            
            pstmt.executeUpdate();
            System.out.println("Feedback added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding feedback: " + e.getMessage());
        }
    }

    public static UserSession getUserDetails(String username) {
        String sql = "SELECT username, email, address, phone_no FROM users WHERE username = ?";
        UserSession user = UserSession.getInstance();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String dbUsername = rs.getString("username");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone_no = rs.getString("phone_no");

                user.setUsername(dbUsername);
                user.setEmail(email);
                user.setAddress(address);
                user.setphone_no(phone_no);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user details: " + e.getMessage());
        }

        return user;
    }
    public static void addOrder(String name, String orderDetails, double totalPrice) {
        String sql = "INSERT INTO orders (name, order_details, total_price) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, orderDetails);
            pstmt.setDouble(3, totalPrice);
            

            pstmt.executeUpdate();
            System.out.println("Order added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding order: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        boolean isConnected = testConnection();
        System.out.println("Connection test result: " + isConnected);
        if (isConnected) {
            UserSession user = getUserDetails("someUsername");  // replace with an actual username in your database
            System.out.println("Username: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Address: " + user.getAddress());
            System.out.println("Phone No: " + user.getphone_no());
        }
    }
}
