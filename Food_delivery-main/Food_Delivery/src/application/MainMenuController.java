package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button cart_button;

    @FXML
    private Button profile_button;

    @FXML
    private void initialize() {
        cart_button.setOnAction(this::switchToCart);
        profile_button.setOnAction(this::switchToProfile);
    }

    private void switchToCart(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Cart.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while loading the cart page.");
        }
    }

    private void switchToProfile(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while loading the profile page.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public class MainPageController {
        private String username;

        public void setUsername(String username) {
            this.username = username;
            // Use the username as needed
        }
    }
}
