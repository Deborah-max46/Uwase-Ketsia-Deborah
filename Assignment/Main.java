package Assignment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get product details from user
        System.out.print("Enter Assignment.Product ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Assignment.Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Stock Quantity: ");
        int qty = scanner.nextInt();

        System.out.print("Enter Price Per Unit: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume leftover

        System.out.print("Enter Category: ");
        String cat = scanner.nextLine();

        System.out.print("Enter Supplier: ");
        String supp = scanner.nextLine();

        System.out.print("Enter Warranty (months): ");
        int warranty = scanner.nextInt();

        ElectronicsItem eItem = new ElectronicsItem(id, name, qty, price, cat, supp);

        // Display Report
        if (eItem.validateStock()) {
            System.out.println("\n--- Stock Report ---");
            eItem.generateStockReport();
        } else {
            System.out.println("Invalid Stock Info!");
        }

        scanner.close();
    }
}




