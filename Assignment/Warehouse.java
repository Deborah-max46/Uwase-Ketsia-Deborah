package Assignment;

class Warehouse {
    private String warehouseId;
    private String location;
    private int capacity;
    private String managerName;
    private int currentStock;

    public Warehouse(String warehouseId, String location, int capacity, String managerName) {
        this.warehouseId = warehouseId;
        this.location = location;
        this.capacity = capacity;
        this.managerName = managerName;
        this.currentStock = 0;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity >= 0) {
            this.capacity = capacity;
        } else {
            System.out.println("Error: Capacity cannot be negative");
        }
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public boolean addStock(int quantity) {
        if (currentStock + quantity <= capacity) {
            currentStock += quantity;
            return true;
        } else {
            System.out.println("Error: Cannot exceed warehouse capacity");
            return false;
        }
    }

    public boolean removeStock(int quantity) {
        if (currentStock >= quantity) {
            currentStock -= quantity;
            return true;
        } else {
            System.out.println("Error: Not enough stock to remove");
            return false;
        }
    }

    public void trackInventory() {
        System.out.println("\n--- WAREHOUSE INVENTORY ---");
        System.out.println("Assignment.Warehouse ID: " + warehouseId);
        System.out.println("Location: " + location);
        System.out.println("Manager: " + managerName);
        System.out.println("Current Stock: " + currentStock + " / " + capacity);
        System.out.println("Available Space: " + (capacity - currentStock));
        System.out.println("Utilization: " + (currentStock * 100.0 / capacity) + "%");
    }
}