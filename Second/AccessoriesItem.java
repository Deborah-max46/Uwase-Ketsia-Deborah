package Second;
import java.util.Scanner;

class AccessoriesItem extends ShoppingItem {
    private String material;
    private double averageRating;
    private int numberOfReviews;

    public AccessoriesItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable,
                           String material, double averageRating, int numberOfReviews) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.material = material;
        this.averageRating = averageRating;
        this.numberOfReviews = numberOfReviews;
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
        invoice.append("------- ACCESSORIES INVOICE -------\n");
        invoice.append("Item: ").append(itemName).append("\n");
        invoice.append("Material: ").append(material).append("\n");
        invoice.append("Customer Rating: ").append(String.format("%.1f", averageRating))
                .append(" (").append(numberOfReviews).append(" reviews)\n");
        invoice.append("Price: $").append(String.format("%.2f", price)).append("\n");
        invoice.append("Customer: ").append(customer.getCustomerName()).append("\n");
        invoice.append("----------------------------------\n");
        return invoice.toString();
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && material != null && !material.isEmpty() &&
                averageRating >= 0 && averageRating <= 5 && numberOfReviews >= 0;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nMaterial: " + material +
                "\nCustomer Rating: " + String.format("%.1f", averageRating) +
                " (" + numberOfReviews + " reviews)";
    }
}

