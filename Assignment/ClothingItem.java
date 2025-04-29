package Assignment;

public class ClothingItem extends StockItem {

    private String[] availableSizes;
    private String[] availableColors;
    private double discountPercentage;

    public ClothingItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String category, String supplier,String[] availableSizes, String[] availableColors) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Clothing", supplier);
        this.availableSizes = availableSizes;
        this.availableColors = availableColors;
        this.discountPercentage = 0.0;
    }

    public String[] getAvailableSizes() {
        return availableSizes;
    }

    public void setAvailableSizes(String[] availableSizes) {
        this.availableSizes = availableSizes;
    }

    public String[] getAvailableColors() {
        return availableColors;
    }

    public void setAvailableColors(String[] availableColors) {
        this.availableColors = availableColors;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        if (discountPercentage >= 0 && discountPercentage <= 50) {
            this.discountPercentage = discountPercentage;
        } else {
            System.out.println("Error: Discount percentage must be between 0 and 50%");
        }
    }

    public  void updateStock(int quantity){
        int newQuantity = getQuantityInStock() + quantity;
        setQuantityInStock(newQuantity);

    }
    public  double calculateStockValue(){
        double discountedPrice = getPricePerUnit() * (1 - (discountPercentage / 100));
        return getQuantityInStock() * discountedPrice;

    }
    public  boolean validateStock(){
        return getQuantityInStock() > 0;
    }

    public  void generateStockReport(){
        System.out.println("\n--- CLOTHING ITEM STOCK REPORT ---");
        System.out.println("Item ID: " + getItemId());
        System.out.println("Item Name: " + getItemName());
        System.out.println("Category: " + getCategory());
        System.out.println("Quantity in Stock: " + getQuantityInStock());
        System.out.println("Price per Unit: $" + getPricePerUnit());
        System.out.print("Available Sizes: ");
        for (String size : availableSizes) {
            System.out.print(size + " ");
        }

        System.out.print("\nAvailable Colors: ");
        for (String color : availableColors) {
            System.out.print(color + " ");
        }

        System.out.println("Discount: " + discountPercentage + "%");
        System.out.println("Total Stock Value: $" + calculateStockValue());
        System.out.println("Supplier: " + getSupplier());
        System.out.println("Stock Status: " + (validateStock() ? "Available" : "Out of Stock"));
    }

}
