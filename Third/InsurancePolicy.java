package Third;

import java.time.LocalDate;
import java.util.*;

abstract class InsurancePolicy {
    private String policyId;
    private Vehicle vehicle;
    private Person policyHolder;
    private double coverageAmount;
    private double premiumAmount;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;

    public InsurancePolicy(Vehicle vehicle, Person policyHolder, double coverageAmount,
                           LocalDate policyStartDate, LocalDate policyEndDate) {
        this.policyId = "POL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.vehicle = vehicle;
        this.policyHolder = policyHolder;
        this.coverageAmount = coverageAmount;
        this.premiumAmount = 0.0;
        this.policyStartDate = policyStartDate;
        this.policyEndDate = policyEndDate;
    }

    // Abstract methods
    public abstract void calculatePremium();
    public abstract boolean processClaim(double claimAmount);
    public abstract void generatePolicyReport();
    public abstract boolean validatePolicy();

    // Getters and Setters
    public String getPolicyId() {
        return policyId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Person getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(Person policyHolder) {
        this.policyHolder = policyHolder;
    }

    public double getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(double coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public LocalDate getPolicyStartDate() {
        return policyStartDate;
    }

    public void setPolicyStartDate(LocalDate policyStartDate) {
        this.policyStartDate = policyStartDate;
    }

    public LocalDate getPolicyEndDate() {
        return policyEndDate;
    }

    public void setPolicyEndDate(LocalDate policyEndDate) {
        this.policyEndDate = policyEndDate;
    }

    // Common validation method
    protected boolean isValidDateRange() {
        return !policyEndDate.isBefore(policyStartDate);
    }

    protected boolean isPolicyActive() {
        LocalDate today = LocalDate.now();
        return !today.isAfter(policyEndDate) && !today.isBefore(policyStartDate);
    }
}
