package Second;
import java.util.Scanner;

class ClothingItem extends ShoppingItem {
    private String size;
    private String season;
    private double discountRate;

    public ClothingItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable,
                        String size, String season, double discountRate) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.size = size;
        this.season = season;
        this.discountRate = discountRate;
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
            scanner.nextLine(); // Clear buffer

            if (quantity <= 0) {
                System.out.println("Error: Quantity must be positive!");
                return false;
            }

            if (quantity > stockAvailable) {
                System.out.println("Error: Not enough stock available! Available: " + stockAvailable);
                return false;
            }

            System.out.print("Enter size (S/M/L/XL): ");
            String selectedSize = scanner.nextLine();

            if (!selectedSize.equalsIgnoreCase("S") && !selectedSize.equalsIgnoreCase("M") &&
                    !selectedSize.equalsIgnoreCase("L") && !selectedSize.equalsIgnoreCase("XL")) {
                System.out.println("Invalid size selected. Please choose S, M, L, or XL.");
                return false;
            }

            ShoppingCart cart = customer.getCart();
            cart.addItem(this, quantity);
            System.out.println("Added " + quantity + " of " + itemName + " (Size: " + selectedSize + ") to cart.");

            double finalPrice = price;
            if (discountRate > 0) {
                finalPrice = price * (1 - discountRate);
                System.out.println("Applied seasonal discount: " + (discountRate * 100) + "% off!");
            }

            return true;
        }
        return false;
    }

    @Override
    public String generateInvoice(Customer customer) {
        StringBuilder invoice = new StringBuilder();
        invoice.append("------- CLOTHING INVOICE -------\n");
        invoice.append("Item: ").append(itemName).append("\n");
        invoice.append("Size: ").append(size).append("\n");
        invoice.append("Season: ").append(season).append("\n");

        double finalPrice = price;
        if (discountRate > 0) {
            finalPrice = price * (1 - discountRate);
            invoice.append("Original Price: $").append(String.format("%.2f", price)).append("\n");
            invoice.append("Discount: ").append(discountRate * 100).append("%\n");
            invoice.append("Final Price: $").append(String.format("%.2f", finalPrice)).append("\n");
        } else {
            invoice.append("Price: $").append(String.format("%.2f", price)).append("\n");
        }

        invoice.append("Customer: ").append(customer.getCustomerName()).append("\n");
        invoice.append("----------------------------------\n");
        return invoice.toString();
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && size != null && !size.isEmpty() &&
                season != null && !season.isEmpty() && discountRate >= 0 && discountRate < 1;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nSize: " + size +
                "\nSeason: " + season +
                "\nDiscount Rate: " + (discountRate * 100) + "%";
    }
}
