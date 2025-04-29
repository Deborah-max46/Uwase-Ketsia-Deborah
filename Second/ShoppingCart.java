package Second;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ShoppingCart {
    private String cartId;
    private Map<ShoppingItem, Integer> cartItems; // Item and quantity
    private double totalPrice;
    private Customer customer;

    public ShoppingCart(String cartId, Customer customer) {
        this.cartId = cartId;
        this.customer = customer;
        this.cartItems = new HashMap<>();
        this.totalPrice = 0.0;
    }

    // Add item to cart
    public boolean addItem(ShoppingItem item, int quantity) {
        if (item == null || quantity <= 0) {
            System.out.println("Invalid item or quantity.");
            return false;
        }

        if (quantity > item.getStockAvailable()) {
            System.out.println("Not enough stock available for " + item.getItemName());
            return false;
        }

        // If item already exists in cart, update quantity
        if (cartItems.containsKey(item)) {
            int currentQuantity = cartItems.get(item);
            int newQuantity = currentQuantity + quantity;

            if (newQuantity > item.getStockAvailable()) {
                System.out.println("Cannot add more. Available stock: " + item.getStockAvailable());
                return false;
            }

            cartItems.put(item, newQuantity);
        } else {
            cartItems.put(item, quantity);
        }

        // Update price and stock
        totalPrice += item.getPrice() * quantity;
        System.out.println("Added " + quantity + " " + item.getItemName() + " to cart.");
        return true;
    }

    // Remove item from cart
    public boolean removeItem(ShoppingItem item, int quantity) {
        if (!cartItems.containsKey(item)) {
            System.out.println("Item not in cart.");
            return false;
        }

        int currentQuantity = cartItems.get(item);
        if (quantity > currentQuantity) {
            System.out.println("Cannot remove more than what's in cart.");
            return false;
        }

        if (currentQuantity == quantity) {
            cartItems.remove(item);
        } else {
            cartItems.put(item, currentQuantity - quantity);
        }

        totalPrice -= item.getPrice() * quantity;
        System.out.println("Removed " + quantity + " " + item.getItemName() + " from cart.");
        return true;
    }

    // Process checkout
    public boolean checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty. Cannot checkout.");
            return false;
        }

        if (!customer.validateCustomerDetails()) {
            System.out.println("Invalid customer details. Please update before checkout.");
            return false;
        }

        System.out.println("\n===== CHECKOUT SUMMARY =====");
        System.out.println("Customer: " + customer.getCustomerName());
        System.out.println("Items:");

        for (Map.Entry<ShoppingItem, Integer> entry : cartItems.entrySet()) {
            ShoppingItem item = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(" - " + item.getItemName() + " x" + quantity +
                    " = $" + String.format("%.2f", item.getPrice() * quantity));

            // Update stock
            item.updateStock(quantity);
        }

        System.out.println("Total Price: $" + String.format("%.2f", totalPrice));
        System.out.println("============================");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Proceed to payment? (y/n): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("y")) {
            Payment payment = new Payment();
            return payment.processPayment(this);
        } else {
            System.out.println("Checkout canceled.");
            return false;
        }
    }

    // Getters
    public String getCartId() {
        return cartId;
    }

    public Map<ShoppingItem, Integer> getCartItems() {
        return new HashMap<>(cartItems);  // Return a copy to prevent direct manipulation
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cart ID: ").append(cartId).append("\n");
        sb.append("Customer: ").append(customer.getCustomerName()).append("\n");
        sb.append("Items:\n");

        for (Map.Entry<ShoppingItem, Integer> entry : cartItems.entrySet()) {
            ShoppingItem item = entry.getKey();
            int quantity = entry.getValue();
            sb.append(" - ").append(item.getItemName())
                    .append(" x").append(quantity)
                    .append(" = $").append(String.format("%.2f", item.getPrice() * quantity))
                    .append("\n");
        }

        sb.append("Total Price: $").append(String.format("%.2f", totalPrice));
        return sb.toString();
    }
}
