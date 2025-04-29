package Assignment;

public class Product {
    private int productId;
    private String productName;
    private String brand;
    private String supplier;
    private double stockQuantity;

    public Product() {
        this.productId=0;
        this.productName = "";
        this.brand = "";
        this.supplier = "";
        this.stockQuantity = 0;
    }
    public Product(int productId, String productName, String brand, String supplier, int stockQuantity) {
        this.setProductId(productId);
        this.setProductName(productName);
        this.setBrand(brand);
        this.setSupplier(supplier);
        this.setStockQuantity(stockQuantity);
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        if (productId >= 0) {
            this.productId = productId;
        } else {
            System.out.println("Error: Assignment.Product ID cannot be negative");
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (productName != null && !productName.trim().isEmpty()) {
            this.productName = productName;
        } else {
            System.out.println("Error: Assignment.Product name cannot be empty");
        }
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand != null && !brand.trim().isEmpty()) {
            this.brand = brand;
        } else {
            System.out.println("Error: Brand cannot be empty");
        }
    }


    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        if (supplier != null && !supplier.trim().isEmpty()) {
            this.supplier = supplier;
        } else {
            System.out.println("Error: Supplier cannot be empty");
        }
    }


    public double getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(double stockQuantity) {
        if (stockQuantity >= 0) {
            this.stockQuantity = stockQuantity;
        } else {
            System.out.println("Error: Stock quantity cannot be negative");
        }
    }

    public void displayProductInfo() {
        System.out.println("\n--- PRODUCT INFORMATION ---");
        System.out.println("Assignment.Product ID: " + productId);
        System.out.println("Assignment.Product Name: " + productName);
        System.out.println("Brand: " + brand);
        System.out.println("Supplier: " + supplier);
        System.out.println("Stock Quantity: " + stockQuantity);
    }
}
