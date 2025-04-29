package Assignment;

public class ElectronicsItem extends StockItem {

    private int warrantyPeriodMonths;
    private double discountPercentage;

    public ElectronicsItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String category, String supplier) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Electronics", supplier);
        setWarrantyPeriodMonths(warrantyPeriodMonths);
        this.discountPercentage = 0.0;
    }

    public int getWarrantyPeriodMonths() {
        return warrantyPeriodMonths;
    }

    public void setWarrantyPeriodMonths(int warrantyPeriodMonths) {
        if (warrantyPeriodMonths >= 0 && warrantyPeriodMonths <= 60)
        this.warrantyPeriodMonths = warrantyPeriodMonths;

    else{
        System.out.println("Error: Warranty period must be between 0 and 60 months");
    }
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        if (discountPercentage >= 0 && discountPercentage <= 50) {
        this.discountPercentage = discountPercentage;
    }
        else{
            System.out.println("Error: Discount percentage must be between 0 and 50%");
        }
        }

    public void applyDiscount(double discountPercentage) {
        setDiscountPercentage(discountPercentage);
        System.out.println("Discount of " + discountPercentage + "% applied to " + ElectronicsItem.this.getItemName());}

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
        System.out.println("\n--- ELECTRONICS ITEM STOCK REPORT ---");
        System.out.println("Item ID: " + getItemId());
        System.out.println("Item Name: " + getItemName());
        System.out.println("Category: " + getCategory());
        System.out.println("Quantity in Stock: " + getQuantityInStock());
        System.out.println("Price per Unit: $" + getPricePerUnit());
        System.out.println("Warranty Period: " + warrantyPeriodMonths + " months");
        System.out.println("Discount: " + discountPercentage + "%");
        System.out.println("Total Stock Value: $" + calculateStockValue());
        System.out.println("Supplier: " + getSupplier());
        System.out.println("Stock Status: " + (validateStock() ? "Available" : "Out of Stock"));
    }

    }

