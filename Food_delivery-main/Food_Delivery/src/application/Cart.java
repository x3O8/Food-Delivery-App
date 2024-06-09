package application;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private static Cart instance;
    private Map<String, Integer> items = new HashMap<>();
    private Map<String, Double> prices = new HashMap<>();

    private Cart() {}

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addItem(String itemName, int quantity, double price) {
        items.put(itemName, items.getOrDefault(itemName, 0) + quantity);
        prices.put(itemName, price);
    }

    public void clearCart() {
        items.clear();
        prices.clear();
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public Map<String, Double> getPrices() {
        return prices;
    }
}
