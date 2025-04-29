package Assignment;

class FurnitureItem extends StockItem {
    private double weightInKg;
    private boolean packedForDelivery;

    public FurnitureItem(String itemId, String itemName, int quantityInStock,
                         double pricePerUnit, String supplier, double weightInKg) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Furniture", supplier);
        this.weightInKg = weightInKg;
        this.packedForDelivery = false;
    }

    public double getWeightInKg() {
        return weightInKg;
    }

    public void setWeightInKg(double weightInKg) {
        if (weightInKg > 0) {
            this.weightInKg = weightInKg;
        } else {
            System.out.println("Error: Weight must be greater than zero");
        }
    }

    public boolean isPackedForDelivery() {
        return packedForDelivery;
    }

    public void setPackedForDelivery(boolean packedForDelivery) {
        this.packedForDelivery = packedForDelivery;
    }

    public double calculateShippingCost() {
        // Basic shipping calculation - $2 per kg
        return weightInKg * 2;
    }

    @Override
    public void updateStock(int quantity) {
        int newQuantity = getQuantityInStock() + quantity;
        setQuantityInStock(newQuantity);
    }

    @Override
    public double calculateStockValue() {
        return getQuantityInStock() * getPricePerUnit();
    }

    @Override
    public void generateStockReport() {
        System.out.println("\n--- FURNITURE ITEM STOCK REPORT ---");
        System.out.println("Item ID: " + getItemId());
        System.out.println("Item Name: " + getItemName());
        System.out.println("Category: " + getCategory());
        System.out.println("Quantity in Stock: " + getQuantityInStock());
        System.out.println("Price per Unit: $" + getPricePerUnit());
        System.out.println("Weight: " + weightInKg + " kg");
        System.out.println("Packed for Delivery: " + (packedForDelivery ? "Yes" : "No"));
        System.out.println("Shipping Cost per Unit: $" + calculateShippingCost());
        System.out.println("Total Stock Value: $" + calculateStockValue());
        System.out.println("Supplier: " + getSupplier());
        System.out.println("Stock Status: " + (validateStock() ? "Available" : "Out of Stock"));
    }

    @Override
    public boolean validateStock() {
        return getQuantityInStock() > 0 && packedForDelivery;
    }
}
