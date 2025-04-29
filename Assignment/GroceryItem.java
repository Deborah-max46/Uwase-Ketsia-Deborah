package Assignment;

import java.time.LocalDate;

class GroceryItem extends StockItem {
    private LocalDate expirationDate;
    private boolean flaggedForDiscount;

    public GroceryItem(String itemId, String itemName, int quantityInStock,
                       double pricePerUnit, String supplier, LocalDate expirationDate) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Groceries", supplier);
        this.expirationDate = expirationDate;
        checkExpirationForDiscount();
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
        checkExpirationForDiscount();
    }

    public boolean isFlaggedForDiscount() {
        return flaggedForDiscount;
    }

    private void checkExpirationForDiscount() {

        LocalDate oneWeekFromNow = LocalDate.now().plusDays(7);
        flaggedForDiscount = expirationDate.isBefore(oneWeekFromNow);
    }
    public void updateStock(int quantity) {
        int newQuantity = getQuantityInStock() + quantity;
        setQuantityInStock(newQuantity);
    }

    @Override
    public double calculateStockValue() {
        double stockValue = getQuantityInStock() * getPricePerUnit();
        if (flaggedForDiscount) {
            stockValue *= 0.7; // 30% discount on items nearing expiration
        }
        return stockValue;
    }

    @Override
    public void generateStockReport() {
        System.out.println("\n--- GROCERY ITEM STOCK REPORT ---");
        System.out.println("Item ID: " + getItemId());
        System.out.println("Item Name: " + getItemName());
        System.out.println("Category: " + getCategory());
        System.out.println("Quantity in Stock: " + getQuantityInStock());
        System.out.println("Price per Unit: $" + getPricePerUnit());
        System.out.println("Expiration Date: " + expirationDate);
        System.out.println("Flagged for Discount: " + (flaggedForDiscount ? "Yes (30% off)" : "No"));
        System.out.println("Total Stock Value: $" + calculateStockValue());
        System.out.println("Supplier: " + getSupplier());
        System.out.println("Stock Status: " + (validateStock() ? "Available" : "Expired or Out of Stock"));
    }

    @Override
    public boolean validateStock() {
        return getQuantityInStock() > 0 && expirationDate.isAfter(LocalDate.now());
    }
}

