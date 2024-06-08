package application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;


public class ProfileController {

    @FXML
    private TextField name_field;

    @FXML
    private TextField pass_field;

    @FXML
    private TextField address_field;
    
    @FXML
    private TextField phone_field;

    @FXML
    private TextField email_field;

    @FXML
    private Button save_button;

    @FXML
    private Button home_button;

    @FXML
    private Label username_label;
    @FXML 
    private Button contact_button;

    @FXML
    private void initialize() {
        // Ensure UserSession is properly imported
        String username = UserSession.getInstance().getUsername();
        username_label.setText("Hi, "+username+"!");

        save_button.setOnAction(this::saveChanges);
        home_button.setOnAction(this::goHome);
        contact_button.setOnAction(this::switchToSupport);
        
    }

    private void saveChanges(ActionEvent event) {
        String currentUsername = UserSession.getInstance().getUsername();
        String newName = name_field.getText();
        String newPass = pass_field.getText();
        String newAddress = address_field.getText();
        String newEmail = email_field.getText();
        String newPhone = phone_field.getText();

        if (!newName.isEmpty()) {
        
            DatabaseManager.updateUserField(currentUsername, "name", newName);
        }
        if (!newPhone.isEmpty()) {
        	DatabaseManager.updateUserField(currentUsername, "phone_no", newPhone);
        	
        }
        if (!newPass.isEmpty()) {
            DatabaseManager.updateUserField(currentUsername, "password", newPass);
        }
        
        if (!newAddress.isEmpty()) {
            DatabaseManager.updateUserField(currentUsername, "address", newAddress);
        }
        if (!newEmail.isEmpty()) {
            DatabaseManager.updateUserField(currentUsername, "email", newEmail);
        }

        showAlert("Success", "Your profile has been updated.");
    }

    private void switchToSupport(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Customer_Support.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while loading the main page.");
        }
    }
    private void goHome(ActionEvent event) {
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
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
