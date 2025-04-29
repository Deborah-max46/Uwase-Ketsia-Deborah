package Second;
import java.util.Scanner;

class BooksItem extends ShoppingItem {
    private String isbn;
    private String edition;
    private String printQuality;

    public BooksItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable,
                     String isbn, String edition, String printQuality) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.isbn = isbn;
        this.edition = edition;
        this.printQuality = printQuality;
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
        invoice.append("------- BOOKS INVOICE -------\n");
        invoice.append("Item: ").append(itemName).append("\n");
        invoice.append("ISBN: ").append(isbn).append("\n");
        invoice.append("Edition: ").append(edition).append("\n");
        invoice.append("Print Quality: ").append(printQuality).append("\n");
        invoice.append("Price: $").append(String.format("%.2f", price)).append("\n");
        invoice.append("Customer: ").append(customer.getCustomerName()).append("\n");
        invoice.append("----------------------------------\n");
        return invoice.toString();
    }

    @Override
    public boolean validateItem() {
        return stockAvailable > 0 && isbn != null && !isbn.isEmpty() &&
                edition != null && !edition.isEmpty() &&
                printQuality != null && !printQuality.isEmpty();
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nISBN: " + isbn +
                "\nEdition: " + edition +
                "\nPrint Quality: " + printQuality;
    }
}
