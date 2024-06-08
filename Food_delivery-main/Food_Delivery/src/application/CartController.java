package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import java.util.Map;
import java.util.List;
import java.io.IOException;

public class CartController {

    @FXML
    private Label item1_label;
    @FXML
    private Label item1_quantity;
    @FXML
    private Label item2_label;
    @FXML
    private Label item2_quantity;
    @FXML
    private Label item3_label;
    @FXML
    private Label item3_quantity;
    @FXML
    private Label item4_label;
    @FXML
    private Label item4_quantity;
    @FXML
    private Label item5_label;
    @FXML
    private Label item5_quantity;
    @FXML
    private Label total_price;
    @FXML
    private Button home_button;
    @FXML
    private Button clear_button;
    @FXML
    private Button place_order;

    @FXML
    private Button max1;
    @FXML
    private Button min1;
    @FXML
    private Button max2;
    @FXML
    private Button min2;
    @FXML
    private Button max3;
    @FXML
    private Button min3;
    @FXML
    private Button max4;
    @FXML
    private Button min4;
    @FXML
    private Button max5;
    @FXML
    private Button min5;

    @FXML
    private void initialize() {
        loadCartItems();
        home_button.setOnAction(this::goHome);
        clear_button.setOnAction(event -> {
            Cart.getInstance().clearCart();
            loadCartItems();
        });
        place_order.setOnAction(event -> showAlert("Order Placed", "Your order has been placed successfully."));

        max1.setOnAction(event -> updateQuantity(item1_label.getText(), 1));
        min1.setOnAction(event -> updateQuantity(item1_label.getText(), -1));
        max2.setOnAction(event -> updateQuantity(item2_label.getText(), 1));
        min2.setOnAction(event -> updateQuantity(item2_label.getText(), -1));
        max3.setOnAction(event -> updateQuantity(item3_label.getText(), 1));
        min3.setOnAction(event -> updateQuantity(item3_label.getText(), -1));
        max4.setOnAction(event -> updateQuantity(item4_label.getText(), 1));
        min4.setOnAction(event -> updateQuantity(item4_label.getText(), -1));
        max5.setOnAction(event -> updateQuantity(item5_label.getText(), 1));
        min5.setOnAction(event -> updateQuantity(item5_label.getText(), -1));
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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

    private void loadCartItems() {
        Cart cart = Cart.getInstance();
        Map<String, Integer> items = cart.getItems();

        // Create lists of labels and buttons for easier manipulation
        List<Label> itemLabels = List.of(item1_label, item2_label, item3_label, item4_label, item5_label);
        List<Label> quantityLabels = List.of(item1_quantity, item2_quantity, item3_quantity, item4_quantity, item5_quantity);
        List<Button> maxButtons = List.of(max1, max2, max3, max4, max5);
        List<Button> minButtons = List.of(min1, min2, min3, min4, min5);

        double totalPrice = 0;

        int index = 0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            if (index >= itemLabels.size()) {
                break; // We only have 5 sets of labels
            }

            String itemName = entry.getKey();
            int quantity = entry.getValue();

            itemLabels.get(index).setText(itemName);
            itemLabels.get(index).setStyle("-fx-text-fill: black;");
            quantityLabels.get(index).setText(" " + quantity);
            quantityLabels.get(index).setStyle("-fx-text-fill: black;");
            maxButtons.get(index).setVisible(true);
            minButtons.get(index).setVisible(true);

            // Assuming each item has a fixed price of $10 for this example
            totalPrice += quantity * 10;
            index++;
        }

        for (int i = index; i < itemLabels.size(); i++) {
            itemLabels.get(i).setText(""); // Clear unused labels
            quantityLabels.get(i).setText("");
            maxButtons.get(i).setVisible(false);
            minButtons.get(i).setVisible(false);
        }

        double totalWithTax = totalPrice * 1.22; // 22% tax
        total_price.setText("₹" + String.format("%.2f", totalWithTax));
        total_price.setStyle("-fx-text-fill: black;");
    }

    private void updateQuantity(String itemName, int delta) {
        if (itemName.isEmpty()) {
            return;
        }

        Cart cart = Cart.getInstance();
        int currentQuantity = cart.getItems().getOrDefault(itemName, 0);
        int newQuantity = currentQuantity + delta;
        if (newQuantity < 0) {
            newQuantity = 0;
        }

        cart.addItem(itemName, newQuantity - currentQuantity, 10.0); // Assuming fixed price of $10 for this example
        loadCartItems();
    }
}
