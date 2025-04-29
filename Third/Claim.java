package Third;
import java.time.LocalDate;
import java.util.UUID;


class Claim {
    private String claimId;
    private double claimAmount;
    private LocalDate claimDate;
    private String claimStatus;
    private String description;
    private InsurancePolicy policy;

    public Claim(double claimAmount, LocalDate claimDate, String description, InsurancePolicy policy) {
        this.claimId = "CLM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.claimAmount = claimAmount;
        this.claimDate = claimDate;
        this.claimStatus = "Pending";
        this.description = description;
        this.policy = policy;
    }

    // Getters and Setters
    public String getClaimId() {
        return claimId;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InsurancePolicy getPolicy() {
        return policy;
    }

    // Validate claim details
    public boolean validate() {
        if (claimAmount <= 0) {
            System.out.println("Claim amount must be greater than zero.");
            return false;
        }

        if (claimAmount > policy.getCoverageAmount()) {
            System.out.println("Claim amount exceeds policy coverage.");
            return false;
        }

        if (claimDate == null || claimDate.isAfter(LocalDate.now())) {
            System.out.println("Invalid claim date.");
            return false;
        }

        if (claimDate.isBefore(policy.getPolicyStartDate()) || claimDate.isAfter(policy.getPolicyEndDate())) {
            System.out.println("Claim date is outside policy coverage period.");
            return false;
        }

        if (description == null || description.trim().isEmpty()) {
            System.out.println("Claim description cannot be empty.");
            return false;
        }

        return true;
    }
}


