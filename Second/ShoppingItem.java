package Second;

import java.util.*;
import java.text.SimpleDateFormat;

// Abstract class serving as the base class for all shopping items
abstract class ShoppingItem {
    protected String itemId;
    protected String itemName;
    protected String itemDescription;
    protected double price;
    protected int stockAvailable;

    public ShoppingItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
        this.stockAvailable = stockAvailable;
    }

    // Abstract methods
    public abstract boolean updateStock(int quantity);
    public abstract boolean addToCart(Customer customer);
    public abstract String generateInvoice(Customer customer);
    public abstract boolean validateItem();

    // Getters and setters with validation
    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public double getPrice() {
        return price;
    }

    public int getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(int stockAvailable) {
        if (stockAvailable >= 0) {
            this.stockAvailable = stockAvailable;
        } else {
            System.out.println("Error: Stock cannot be negative!");
        }
    }

    @Override
    public String toString() {
        return "Item ID: " + itemId +
                "\nName: " + itemName +
                "\nDescription: " + itemDescription +
                "\nPrice: $" + String.format("%.2f", price) +
                "\nStock Available: " + stockAvailable;
    }
}
