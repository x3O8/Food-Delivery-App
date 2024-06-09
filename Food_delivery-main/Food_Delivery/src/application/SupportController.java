package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SupportController {

    @FXML
    private Button home_button;
    @FXML
    private Button cart_button;
    @FXML
    private Button profile_button;
    @FXML
    private Button submit_button;
    @FXML
    private TextArea querie_text;

    @FXML
    private void initialize() {
        home_button.setOnAction(this::goHome);
        cart_button.setOnAction(this::goToCart);
        profile_button.setOnAction(this::goToProfile);
        submit_button.setOnAction(this::submitFeedback);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void goHome(ActionEvent event) {
        navigateToScene(event, "Main_Page.fxml");
    }

    private void goToCart(ActionEvent event) {
        navigateToScene(event, "Cart.fxml");
    }

    private void goToProfile(ActionEvent event) {
        navigateToScene(event, "Profile.fxml");
    }

    private void navigateToScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while loading the page.");
        }
    }

    private void submitFeedback(ActionEvent event) {
        String feedbackText = querie_text.getText();

        if (feedbackText.isEmpty()) {
            showAlert("Error", "Feedback cannot be empty.");
            return;
        }

        UserSession userSession = UserSession.getInstance();
        String username = userSession.getUsername();
        DatabaseManager.getUserDetails(username);  // Ensure UserSession is populated with the latest details
        String email = userSession.getEmail();

        if (username != null && email != null) {
            DatabaseManager.addFeedback(username, feedbackText, email);
            showAlert("Success", "Feedback submitted successfully.");
            querie_text.clear(); // Clear the text area after submission
        } else {
            showAlert("Error", "User details not found.");
        }
    }
}
