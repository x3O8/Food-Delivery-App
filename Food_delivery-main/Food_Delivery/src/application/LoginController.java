package application;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button Login_button;

    @FXML
    private Button Not_Member;

    @FXML
    private void initialize() {
        Login_button.setOnAction(event -> handleLogin(event));
        Not_Member.setOnAction(event -> switchToSignup(event));
    }

    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty()) {
            showAlert("Login Failed", "Username cannot be blank.");
            return;
        }

        if (password.isEmpty()) {
            showAlert("Login Failed", "Password cannot be blank.");
            return;
        }

        if (DatabaseManager.userExists(username)) {
            if (DatabaseManager.validateUser(username, password)) {
                UserSession.getInstance().setUsername(username); // Set username in UserSession
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("Main_Page.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                    showAlert("Error", "An error occurred while loading the main page.");
                }
            } else {
                showAlert("Login Failed", "Incorrect password. Please try again.");
            }
        } else {
            showAlert("User Not Found", "This username does not exist. Please create a new account.");
        }
    }

    private void switchToSignup(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while loading the signup page.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
