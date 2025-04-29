package Third;
import java.time.LocalDate;
import java.util.UUID;


class Person {
    private String personId;
    private String fullName;
    private LocalDate dob;
    private String email;
    private String phone;

    public Person(String fullName, LocalDate dob, String email, String phone) {
        this.personId = "PER-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
    public String getPersonId() {
        return personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Validate person details
    public boolean validate() {
        if (fullName == null || fullName.trim().isEmpty()) {
            System.out.println("Full name cannot be empty.");
            return false;
        }

        if (dob == null || dob.isAfter(LocalDate.now().minusYears(16))) {
            System.out.println("Person must be at least 16 years old.");
            return false;
        }

        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            System.out.println("Invalid email format.");
            return false;
        }

        if (phone == null || phone.trim().isEmpty()) {
            System.out.println("Phone number cannot be empty.");
            return false;
        }

        return true;
    }
}