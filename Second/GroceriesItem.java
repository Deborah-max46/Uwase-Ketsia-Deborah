package Second;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class GroceriesItem extends ShoppingItem {
    private Date expirationDate;
    private double bulkDiscountRate;
    private int bulkQuantityThreshold;

    public GroceriesItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable,
                         Date expirationDate, double bulkDiscountRate, int bulkQuantityThreshold) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.expirationDate = expirationDate;
        this.bulkDiscountRate = bulkDiscountRate;
        this.bulkQuantityThreshold = bulkQuantityThreshold;
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

            if (quantity >= bulkQuantityThreshold) {
                double discountedPrice = price * (1 - bulkDiscountRate);
                System.out.println("Bulk discount applied! New price per item: $" +
                        String.format("%.2f", discountedPrice));
            }

            return true;
        }
        return false;
    }

    @Override
    public String generateInvoice(Customer customer) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder invoice = new StringBuilder();
        invoice.append("------- GROCERIES INVOICE -------\n");
        invoice.append("Item: ").append(itemName).append("\n");
        invoice.append("Price: $").append(String.format("%.2f", price)).append("\n");
        invoice.append("Expiration Date: ").append(sdf.format(expirationDate)).append("\n");
        invoice.append("Bulk Discount: Buy ").append(bulkQuantityThreshold)
                .append(" or more for ").append(bulkDiscountRate * 100).append("% off\n");
        invoice.append("Customer: ").append(customer.getCustomerName()).append("\n");
        invoice.append("----------------------------------\n");
        return invoice.toString();
    }

    @Override
    public boolean validateItem() {
        Date currentDate = new Date();
        return stockAvailable > 0 && expirationDate != null && expirationDate.after(currentDate) &&
                bulkDiscountRate >= 0 && bulkDiscountRate < 1 && bulkQuantityThreshold > 0;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return super.toString() +
                "\nExpiration Date: " + sdf.format(expirationDate) +
                "\nBulk Discount: Buy " + bulkQuantityThreshold +
                " or more for " + (bulkDiscountRate * 100) + "% off";
    }
}

