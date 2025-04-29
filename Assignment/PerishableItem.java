package Assignment;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class PerishableItem extends StockItem {
    private LocalDate expirationDate;
    private int shelfLifeDays;

    public PerishableItem(String itemId, String itemName, int quantityInStock,
                          double pricePerUnit, String supplier,
                          LocalDate expirationDate, int shelfLifeDays) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Perishable", supplier);
        this.expirationDate = expirationDate;
        this.shelfLifeDays = shelfLifeDays;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getShelfLifeDays() {
        return shelfLifeDays;
    }

    public void setShelfLifeDays(int shelfLifeDays) {
        if (shelfLifeDays > 0) {
            this.shelfLifeDays = shelfLifeDays;
        } else {
            System.out.println("Error: Shelf life must be greater than zero days");
        }
    }

    public boolean shouldDispose() {
        return LocalDate.now().isAfter(expirationDate);
    }

    public void generateExpirationAlert() {
        if (shouldDispose()) {
            System.out.println("ALERT: " + getItemName() + " (ID: " + getItemId() + ") has expired! Dispose immediately.");
        } else {
            long daysUntilExpiration = ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
            if (daysUntilExpiration < 3) {
                System.out.println("WARNING: " + getItemName() + " (ID: " + getItemId() +
                        ") will expire in " + daysUntilExpiration + " days!");
            }
        }
    }

    @Override
    public void updateStock(int quantity) {
        int newQuantity = getQuantityInStock() + quantity;
        setQuantityInStock(newQuantity);
    }

    @Override
    public double calculateStockValue() {
        double stockValue = getQuantityInStock() * getPricePerUnit();

        // Apply discount based on how close to expiration
        long daysUntilExpiration = ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
        if (daysUntilExpiration < 3) {
            stockValue *= 0.5; // 50% discount if less than 3 days to expiration
        } else if (daysUntilExpiration < 7) {
            stockValue *= 0.8; // 20% discount if less than 7 days to expiration
        }

        return stockValue;
    }

    @Override
    public void generateStockReport() {
        System.out.println("\n--- PERISHABLE ITEM STOCK REPORT ---");
        System.out.println("Item ID: " + getItemId());
        System.out.println("Item Name: " + getItemName());
        System.out.println("Category: " + getCategory());
        System.out.println("Quantity in Stock: " + getQuantityInStock());
        System.out.println("Price per Unit: $" + getPricePerUnit());
        System.out.println("Expiration Date: " + expirationDate);
        System.out.println("Shelf Life: " + shelfLifeDays + " days");

        long daysUntilExpiration = ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
        System.out.println("Days Until Expiration: " + daysUntilExpiration);

        if (daysUntilExpiration < 0) {
            System.out.println("Status: EXPIRED - NEEDS DISPOSAL");
        } else if (daysUntilExpiration < 3) {
            System.out.println("Status: CRITICAL - Apply 50% discount");
        } else if (daysUntilExpiration < 7) {
            System.out.println("Status: WARNING - Apply 20% discount");
        } else {
            System.out.println("Status: Good");
        }

        System.out.println("Total Stock Value: $" + calculateStockValue());
        System.out.println("Supplier: " + getSupplier());
        System.out.println("Stock Validation: " + (validateStock() ? "Valid" : "Invalid - Check expiration"));
    }

    @Override
    public boolean validateStock() {
        return getQuantityInStock() > 0 && !shouldDispose();
    }
}
