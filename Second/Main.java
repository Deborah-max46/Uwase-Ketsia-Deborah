package Second;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;


class OnlineShoppingSystem {
    private static List<ShoppingItem> inventory = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Payment> payments = new ArrayList<>();
    private static Customer currentCustomer = null;
    private static Scanner scanner = new Scanner(System.in);


    // Main menu
    private static void showMainMenu() {
        while (true) {
            System.out.println("\n===== ONLINE SHOPPING SYSTEM =====");
            System.out.println("1. Register as new customer");
            System.out.println("2. Login as existing customer");
            System.out.println("3. Browse products");
            System.out.println("4. View cart");
            System.out.println("5. Checkout");
            System.out.println("6. Generate Sales Report");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    loginCustomer();
                    break;
                case 3:
                    browseProducts();
                    break;
                case 4:
                    viewCart();
                    break;
                case 5:
                    checkout();
                    break;
                case 6:
                    generateReport();
                    break;
                case 7:
                    System.out.println("Thank you for shopping with us!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Register new customer
    private static void registerCustomer() {
        System.out.println("\n===== CUSTOMER REGISTRATION =====");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        // Check if email already exists
        boolean emailExists = customers.stream().anyMatch(c -> c.getEmail().equals(email));
        if (emailExists) {
            System.out.println("An account with this email already exists. Please login instead.");
            return;
        }

        System.out.print("Enter your address: ");
        String address = scanner.nextLine();

        System.out.print("Enter your phone (10 digits): ");
        String phone = scanner.nextLine();

        String customerId = "CUST" + (customers.size() + 1);
        Customer newCustomer = new Customer(customerId, name, email, address, phone);

        if (newCustomer.validateCustomerDetails()) {
            customers.add(newCustomer);
            currentCustomer = newCustomer;
            System.out.println("Registration successful! You are now logged in as " + name);
        } else {
            System.out.println("Registration failed. Please check your details and try again.");
        }
    }

    // Login existing customer
    private static void loginCustomer() {
        System.out.println("\n===== CUSTOMER LOGIN =====");

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                currentCustomer = customer;
                System.out.println("Login successful! Welcome back, " + customer.getCustomerName());
                return;
            }
        }

        System.out.println("Email not found. Would you like to register? (y/n): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("y")) {
            registerCustomer();
        } else {
            System.out.println("Login cancelled.");
        }
    }

    // Browse products
    private static void browseProducts() {
        while (true) {
            System.out.println("\n===== PRODUCT CATEGORIES =====");
            System.out.println("1. Electronics");
            System.out.println("2. Clothing");
            System.out.println("3. Groceries");
            System.out.println("4. Books");
            System.out.println("5. Accessories");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose a category: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            if (choice == 6) {
                return;
            }

            List<ShoppingItem> categoryItems = new ArrayList<>();
            String categoryName = "";

            switch (choice) {
                case 1:
                    categoryName = "Electronics";
                    for (ShoppingItem item : inventory) {
                        if (item instanceof ElectronicsItem) {
                            categoryItems.add(item);
                        }
                    }
                    break;
                case 2:
                    categoryName = "Clothing";
                    for (ShoppingItem item : inventory) {
                        if (item instanceof ClothingItem) {
                            categoryItems.add(item);
                        }
                    }
                    break;
                case 3:
                    categoryName = "Groceries";
                    for (ShoppingItem item : inventory) {
                        if (item instanceof GroceriesItem) {
                            categoryItems.add(item);
                        }
                    }
                    break;
                case 4:
                    categoryName = "Books";
                    for (ShoppingItem item : inventory) {
                        if (item instanceof BooksItem) {
                            categoryItems.add(item);
                        }
                    }
                    break;
                case 5:
                    categoryName = "Accessories";
                    for (ShoppingItem item : inventory) {
                        if (item instanceof AccessoriesItem) {
                            categoryItems.add(item);
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid category choice.");
                    continue;
            }

            displayCategoryItems(categoryName, categoryItems);
        }
    }

    // Display items in a category
    private static void displayCategoryItems(String categoryName, List<ShoppingItem> items) {
        if (items.isEmpty()) {
            System.out.println("No items found in " + categoryName);
            return;
        }

        while (true) {
            System.out.println("\n===== " + categoryName.toUpperCase() + " =====");

            for (int i = 0; i < items.size(); i++) {
                ShoppingItem item = items.get(i);
                System.out.println((i + 1) + ". " + item.getItemName() + " - $" +
                        String.format("%.2f", item.getPrice()) +
                        " (Stock: " + item.getStockAvailable() + ")");
            }

            System.out.println((items.size() + 1) + ". Back to Categories");
            System.out.print("Select an item to view details or go back: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            if (choice == items.size() + 1) {
                return;
            }

            if (choice >= 1 && choice <= items.size()) {
                ShoppingItem selectedItem = items.get(choice - 1);
                displayItemDetails(selectedItem);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    // Display item details
    private static void displayItemDetails(ShoppingItem item) {
        System.out.println("\n===== ITEM DETAILS =====");
        System.out.println(item.toString());

        if (currentCustomer == null) {
            System.out.println("\nYou need to login/register to add items to cart.");
            System.out.print("Would you like to login/register now? (y/n): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("y")) {
                System.out.print("Do you have an account? (y/n): ");
                choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("y")) {
                    loginCustomer();
                } else {
                    registerCustomer();
                }
            } else {
                return;
            }
        }

        if (currentCustomer != null) {
            System.out.print("\nWould you like to add this item to your cart? (y/n): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("y")) {
                item.addToCart(currentCustomer);
            }
        }
    }

    // View cart
    private static void viewCart() {
        if (currentCustomer == null) {
            System.out.println("You need to login/register to view your cart.");
            return;
        }

        ShoppingCart cart = currentCustomer.getCart();
        Map<ShoppingItem, Integer> cartItems = cart.getCartItems();

        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("\n===== YOUR CART =====");
        System.out.println(cart.toString());

        System.out.print("\nWould you like to remove any items? (y/n): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("y")) {
            List<ShoppingItem> itemsList = new ArrayList<>(cartItems.keySet());

            for (int i = 0; i < itemsList.size(); i++) {
                ShoppingItem item = itemsList.get(i);
                System.out.println((i + 1) + ". " + item.getItemName() + " x" + cartItems.get(item));
            }

            System.out.print("Select item number to remove (0 to cancel): ");
            int itemChoice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            if (itemChoice >= 1 && itemChoice <= itemsList.size()) {
                ShoppingItem selectedItem = itemsList.get(itemChoice - 1);
                System.out.print("How many to remove?: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); // Clear buffer

                cart.removeItem(selectedItem, quantity);
            }
        }
    }

    // Checkout
    private static void checkout() {
        if (currentCustomer == null) {
            System.out.println("You need to login/register to checkout.");
            return;
        }

        ShoppingCart cart = currentCustomer.getCart();
        if (cart.getCartItems().isEmpty()) {
            System.out.println("Your cart is empty. Add items before checking out.");
            return;
        }

        boolean success = cart.checkout();
        if (success) {
            // In a real system, we would create a new Payment object and add it to payments list
            Payment payment = new Payment();
            payment.setPaymentMethod("Credit Card"); // This would come from user input in a real system
            payment.setAmountPaid(cart.getTotalPrice());
            payments.add(payment);

            // Create a new cart for the customer
            Customer refreshedCustomer = new Customer(
                    currentCustomer.getCustomerId(),
                    currentCustomer.getCustomerName(),
                    currentCustomer.getEmail(),
                    currentCustomer.getAddress(),
                    currentCustomer.getPhone()
            );

            // Find and replace the current customer in the customers list
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getCustomerId().equals(currentCustomer.getCustomerId())) {
                    customers.set(i, refreshedCustomer);
                    break;
                }
            }

            currentCustomer = refreshedCustomer;
            System.out.println("Order placed successfully! A new cart has been created for your next purchase.");
        }
    }

    // Generate sales report
    private static void generateReport() {
        System.out.println("Generating sales report...");
        ReportGenerator.generateSalesReport(payments, inventory);
    }

    // Main method
    public static void main(String[] args) {

        System.out.println("Welcome to the Advanced Online Shopping System!");
        showMainMenu();
    }
}
