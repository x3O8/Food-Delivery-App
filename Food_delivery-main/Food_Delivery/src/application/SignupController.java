package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignupController {

    @FXML
    private TextField adress_field;

    @FXML
    private PasswordField confirm_pass_field;

    @FXML
    private TextField email_field;

    @FXML
    private TextField name_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField phone_field;

    @FXML
    private TextField username_field;

    @FXML
    private CheckBox terms_box;

    @FXML
    private Label err_label;

    @FXML
    private Label name_err;

    @FXML
    private Label ph_err;

    @FXML
    private Label user_err;

    @FXML
    private Label address_err;

    @FXML
    private Label pass_err;

    @FXML
    private Label dob_err;

    @FXML
    private Label confirm_err;

    @FXML
    private Label mail_err;

    @FXML
    private DatePicker dob_field;
    
    @FXML
    private Label mismatch_err;
   

    public void signupHandler(ActionEvent event) throws IOException {
        boolean hasError = false;

        // Reset error labels
        resetErrorLabels();

        // Validate each field
        if (name_field.getText().isEmpty()) {
            name_err.setText("*");
            name_err.setStyle("-fx-text-fill: red;");
            hasError = true;
        }

        if (phone_field.getText().isEmpty()) {
            ph_err.setText("*");
            ph_err.setStyle("-fx-text-fill: red;");
            hasError = true;
        }

        if (username_field.getText().isEmpty()) {
            user_err.setText("*");
            user_err.setStyle("-fx-text-fill: red;");
            hasError = true;
        }

        if (adress_field.getText().isEmpty()) {
            address_err.setText("*");
            address_err.setStyle("-fx-text-fill: red;");
            hasError = true;
        }

        if (password_field.getText().isEmpty()) {
            pass_err.setText("*");
            pass_err.setStyle("-fx-text-fill: red;");
            hasError = true;
        }

        if (confirm_pass_field.getText().isEmpty()) {
            confirm_err.setText("*");
            confirm_err.setStyle("-fx-text-fill: red;");
            hasError = true;
        }

        if (email_field.getText().isEmpty()) {
            mail_err.setText("*");
            mail_err.setStyle("-fx-text-fill: red;");
            hasError = true;
        }

        if (dob_field.getValue() == null) {
            dob_err.setText("*");
            dob_err.setStyle("-fx-text-fill: red;");
            hasError = true;
        } else {
            // Check if the user is at least 18 years old
            LocalDate dob = dob_field.getValue();
            if (Period.between(dob, LocalDate.now()).getYears() < 18) {
                showAlert("Age Restriction", "You must be at least 18 years old to create an account.");
                return;
            }
        }

        // Check if passwords match
        if (!password_field.getText().equals(confirm_pass_field.getText())) {
            mismatch_err.setText("Password confirmation does not match.");
            mismatch_err.setStyle("-fx-text-fill: red;");
            hasError = true;
        }

        if (!terms_box.isSelected()) {
            showErrorMessage("You must agree to the terms and conditions to create an account.");
            return;
        }

        if (hasError) {
            return;
        }

        DatabaseManager dab = new DatabaseManager();

        try {
            String name = name_field.getText();
            String user_name = username_field.getText();
            String passw = password_field.getText();
            String phone = phone_field.getText();
            String address = adress_field.getText();
            String email = email_field.getText();
            LocalDate dob = dob_field.getValue();
            String dobString = dob.toString();

            if (dab.userExists(user_name)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Username Exists");
                alert.setHeaderText(null);
                alert.setContentText("The username already exists. Please choose a different username.");
                alert.showAndWait();
                return;
            }

            dab.createUser(name, phone, user_name, address, passw, dobString, email);
            switchtoScene1(event);

        } catch (Exception e) {
            System.out.print("Unexpected error" + e);
        }
    }


    private void resetErrorLabels() {
        name_err.setText("");
        ph_err.setText("");
        user_err.setText("");
        address_err.setText("");
        pass_err.setText("");
        dob_err.setText("");
        confirm_err.setText("");
        mail_err.setText("");
    }

    private void showErrorMessage(String message) {
        err_label.setText(message);
        err_label.setStyle("-fx-text-fill: red;");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void switchtoScene1(ActionEvent e) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.print("Could not link the Scene1 due to IOException");
            throw ex;
        }
    }
}
