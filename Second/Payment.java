package Second;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.Map;


class Payment {
    private String paymentId;
    private String paymentMethod;
    private double amountPaid;
    private Date transactionDate;
    private static final List<String> VALID_PAYMENT_METHODS = Arrays.asList(
            "Credit Card", "Debit Card", "PayPal", "Bank Transfer", "Cash on Delivery"
    );

    public Payment() {
        this.paymentId = UUID.randomUUID().toString();
        this.transactionDate = new Date();
    }

    // Process payment
    public boolean processPayment(ShoppingCart cart) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n===== PAYMENT METHODS =====");
        for (int i = 0; i < VALID_PAYMENT_METHODS.size(); i++) {
            System.out.println((i + 1) + ". " + VALID_PAYMENT_METHODS.get(i));
        }

        System.out.print("Select payment method (1-" + VALID_PAYMENT_METHODS.size() + "): ");
        int paymentMethodIndex = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        if (paymentMethodIndex < 1 || paymentMethodIndex > VALID_PAYMENT_METHODS.size()) {
            System.out.println("Invalid payment method selected.");
            return false;
        }

        this.paymentMethod = VALID_PAYMENT_METHODS.get(paymentMethodIndex - 1);
        this.amountPaid = cart.getTotalPrice();

        System.out.println("\nProcessing payment of $" + String.format("%.2f", amountPaid) +
                " via " + paymentMethod);

        if (paymentMethod.equals("Credit Card") || paymentMethod.equals("Debit Card")) {
            System.out.print("Enter card number: ");
            String cardNumber = scanner.nextLine();

            if (!cardNumber.matches("^\\d{16}$")) {
                System.out.println("Invalid card number. Must be 16 digits.");
                return false;
            }

            System.out.print("Enter expiry date (MM/YY): ");
            String expiryDate = scanner.nextLine();
            if (!expiryDate.matches("^(0[1-9]|1[0-2])/[0-9]{2}$")) {
                System.out.println("Invalid expiry date format. Use MM/YY.");
                return false;
            }

            System.out.print("Enter CVV: ");
            String cvv = scanner.nextLine();
            if (!cvv.matches("^\\d{3}$")) {
                System.out.println("Invalid CVV. Must be 3 digits.");
                return false;
            }
        } else if (paymentMethod.equals("PayPal")) {
            System.out.print("Enter PayPal email: ");
            String paypalEmail = scanner.nextLine();

            if (!paypalEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                System.out.println("Invalid email format.");
                return false;
            }
        }

        // Payment successful
        System.out.println("\nPayment successful!");
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Transaction Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(transactionDate));

        // Generate invoice
        generateInvoice(cart);

        return true;
    }

    // Generate invoice
    private void generateInvoice(ShoppingCart cart) {
        System.out.println("\n========= INVOICE =========");
        System.out.println("Invoice Date: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(transactionDate));
        System.out.println("Invoice ID: INV-" + paymentId.substring(0, 8).toUpperCase());

        Customer customer = cart.getCustomer();
        System.out.println("\nCustomer Details:");
        System.out.println("Name: " + customer.getCustomerName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Address: " + customer.getAddress());
        System.out.println("Phone: " + customer.getPhone());

        System.out.println("\nItems Purchased:");
        Map<ShoppingItem, Integer> items = cart.getCartItems();
        int itemNumber = 1;

        for (Map.Entry<ShoppingItem, Integer> entry : items.entrySet()) {
            ShoppingItem item = entry.getKey();
            int quantity = entry.getValue();
            double itemTotal = item.getPrice() * quantity;

            System.out.println(itemNumber + ". " + item.getItemName() +
                    " (ID: " + item.getItemId() + ")");
            System.out.println("   Quantity: " + quantity);
            System.out.println("   Price per unit: $" + String.format("%.2f", item.getPrice()));
            System.out.println("   Total: $" + String.format("%.2f", itemTotal));
            itemNumber++;
        }

        System.out.println("\nPayment Details:");
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Amount Paid: $" + String.format("%.2f", amountPaid));

        System.out.println("\nTotal Amount: $" + String.format("%.2f", cart.getTotalPrice()));
        System.out.println("=============================");
    }

    // Getters and setters
    public String getPaymentId() {
        return paymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        if (VALID_PAYMENT_METHODS.contains(paymentMethod)) {
            this.paymentMethod = paymentMethod;
        } else {
            System.out.println("Invalid payment method.");
        }
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        if (amountPaid > 0) {
            this.amountPaid = amountPaid;
        } else {
            System.out.println("Amount paid must be positive.");
        }
    }

    public Date getTransactionDate() {
        return new Date(transactionDate.getTime()); // Return a copy to prevent direct manipulation
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Payment ID: " + paymentId +
                "\nPayment Method: " + paymentMethod +
                "\nAmount Paid: $" + String.format("%.2f", amountPaid) +
                "\nTransaction Date: " + sdf.format(transactionDate);
    }
}
