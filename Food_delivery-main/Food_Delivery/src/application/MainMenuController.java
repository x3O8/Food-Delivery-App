package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button cart_button;
    @FXML 
    private Button contact_button;
    @FXML
    private Button profile_button;
    @FXML
    private Label welc_label;

    @FXML
    private Button logout_button;

    @FXML
    private Button addToCartButton1;
    
    @FXML
    private Button addToCartButton2;
    
    @FXML
    private Button addToCartButton3;    
    @FXML
    private Button addToCartButton4;    
    @FXML
    private Button addToCartButton5;    
    @FXML
    private Button addToCartButton6;    
    @FXML
    private Button addToCartButton7;    
    @FXML
    private Button addToCartButton8;    
    @FXML
    private Button addToCartButton9;
    @FXML
    private void initialize() {
        cart_button.setOnAction(this::switchToCart);
        contact_button.setOnAction(this::switchToSupport);
        profile_button.setOnAction(this::switchToProfile);
        String username = UserSession.getInstance().getUsername();
        welc_label.setText("Welcome back, " + username + "!");
        logout_button.setOnAction(this::switchToLogin);
        addToCartButton1.setOnAction(event -> addToCart("Crispy Chicken Burger", 1, 250.00)); 
        addToCartButton2.setOnAction(event -> addToCart("BBQ Olive Pizza", 1, 320.00)); 
        addToCartButton3.setOnAction(event -> addToCart("Chocolate Shake", 1, 80.00)); 
        addToCartButton4.setOnAction(event -> addToCart("Fish & Chips", 1, 145.00)); 
        addToCartButton5.setOnAction(event -> addToCart("Spagetti Bolognese", 1, 160.00)); 
        addToCartButton6.setOnAction(event -> addToCart("Mint Mojito", 1, 90.00));
        addToCartButton7.setOnAction(event -> addToCart("Mac & Cheese", 1, 150.00)); 
        addToCartButton8.setOnAction(event -> addToCart("Butter Chicken", 1, 210.00)); 
        addToCartButton9.setOnAction(event -> addToCart("Lava Cake", 1, 120.00)); 
        

    }

    private void addToCart(String itemName, int quantity, double price) {
        Cart.getInstance().addItem(itemName, quantity, price);
        showAlert("Item Added", itemName + " has been added to your cart.");
    }

    private void switchToCart(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CartPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while loading the cart page.");
        }
    }

    public void switchToSupport(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Customer_Support.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while loading the cart page.");
        }
    }
    private void switchToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while loading the Login page.");
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
