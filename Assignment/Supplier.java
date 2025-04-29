package Assignment;

class Supplier {
    private String supplierId;
    private String companyName;
    private String contactPerson;
    private String phone;
    private String email;

    public Supplier(String supplierId, String companyName, String contactPerson,
                    String phone, String email) {
        this.supplierId = supplierId;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        setPhone(phone);
        setEmail(email);
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        // Simple validation for phone number format
        if (phone.matches("\\d{10}|\\d{3}[-.]\\d{3}[-.]\\d{4}|\\(\\d{3}\\)\\s?\\d{3}[-.]\\d{4}")) {
            this.phone = phone;
        } else {
            System.out.println("Error: Invalid phone number format");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // Simple validation for email format
        if (email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            this.email = email;
        } else {
            System.out.println("Error: Invalid email format");
        }
    }

    public void displaySupplierInfo() {
        System.out.println("\n--- SUPPLIER INFORMATION ---");
        System.out.println("Assignment.Supplier ID: " + supplierId);
        System.out.println("Company Name: " + companyName);
        System.out.println("Contact Person: " + contactPerson);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
    }
}