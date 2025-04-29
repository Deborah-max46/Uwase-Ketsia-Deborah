
package Second;
import java.util.Scanner;

class ElectronicsItem extends ShoppingItem {
    private int warrantyPeriod; // in months
    private String productRegistrationId;

    public ElectronicsItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable,
                           int warrantyPeriod, String productRegistrationId) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.warrantyPeriod = warrantyPeriod;
        this.productRegistrationId = productRegistrationId;
    }

    @Override
    public boolean updateStock(int quantity) {
        if (quantity <= stockAvailable && quantity > 0) {
            stockAvailable -= quantity;
            return true;
        }
        return false;
    }

    @Override
    public boolean addToCart(Customer customer) {
        if (validateItem()) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter quantity to add to cart: ");
            int quantity = scanner.nextInt();

            if (quantity <= 0) {
                System.out.println("Error: Quantity must be positive!");
                return false;
            }

            if (quantity > stockAvailable) {
                System.out.println("Error: Not enough stock available! Available: " + stockAvailable);
                return false;
            }

            ShoppingCart cart = customer.getCart();
            cart.addItem(this, quantity);
            return true;
        }
        return false;
    }

    @Override
    public String generateInvoice(Customer customer) {
        StringBuilder invoice = new StringBuilder();
        invoice.append("------- ELECTRONICS INVOICE -------\n");
        invoice.append("Item: ").append(itemName).append("\n");
        invoice.append("Price: $").append(String.format("%.2f", price)).append("\n");
        invoice.append("Warranty Period: ").append(warrantyPeriod).append(" months\n");
        invoice.append("Registration ID: ").append(productRegistrationId).append("\n");
        invoice.append("Customer: ").append(customer.getCustomerName()).append("\n");
        invoice.append("----------------------------------\n");
        return invoice.toString();
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && warrantyPeriod > 0 && productRegistrationId != null && !productRegistrationId.isEmpty();
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nWarranty Period: " + warrantyPeriod + " months" +
                "\nRegistration ID: " + productRegistrationId;
    }
}






