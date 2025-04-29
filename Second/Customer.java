package Second;
import java.util.UUID;

class Customer {
    private String customerId;
    private String customerName;
    private String email;
    private String address;
    private String phone;
    private ShoppingCart cart;

    public Customer(String customerId, String customerName, String email, String address, String phone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.cart = new ShoppingCart(UUID.randomUUID().toString(), this);
    }

    // Validate customer details
    public boolean validateCustomerDetails() {
        // Validate email format (simple validation)
        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            System.out.println("Invalid email format.");
            return false;
        }

        // Validate phone (simple validation)
        if (phone == null || !phone.matches("^\\d{10}$")) {
            System.out.println("Invalid phone number. Please use 10 digits format.");
            return false;
        }

        // Check if address is empty
        if (address == null || address.trim().isEmpty()) {
            System.out.println("Address cannot be empty.");
            return false;
        }

        return true;
    }

    // Getters and setters
    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        if (customerName != null && !customerName.trim().isEmpty()) {
            this.customerName = customerName;
        } else {
            System.out.println("Customer name cannot be empty.");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            this.email = email;
        } else {
            System.out.println("Invalid email format.");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null && !address.trim().isEmpty()) {
            this.address = address;
        } else {
            System.out.println("Address cannot be empty.");
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null && phone.matches("^\\d{10}$")) {
            this.phone = phone;
        } else {
            System.out.println("Invalid phone number. Please use 10 digits format.");
        }
    }

    public ShoppingCart getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId +
                "\nName: " + customerName +
                "\nEmail: " + email +
                "\nAddress: " + address +
                "\nPhone: " + phone;
    }
}
