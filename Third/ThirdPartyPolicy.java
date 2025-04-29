package Third;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

class ThirdPartyPolicy extends InsurancePolicy {
    private static final double BASE_PREMIUM = 500.0;
    private boolean hasAdditionalCoverage;

    public ThirdPartyPolicy(Vehicle vehicle, Person policyHolder, double coverageAmount,
                            LocalDate policyStartDate, LocalDate policyEndDate) {
        super(vehicle, policyHolder, coverageAmount, policyStartDate, policyEndDate);
        this.hasAdditionalCoverage = false;
    }

    public void setAdditionalCoverage(boolean hasAdditionalCoverage) {
        this.hasAdditionalCoverage = hasAdditionalCoverage;
    }

    @Override
    public void calculatePremium() {
        double premium = BASE_PREMIUM;

        // Add premium based on engine capacity
        int engineCapacity = getVehicle().getEngineCapacity();
        if (engineCapacity < 1500) {
            premium += 200.0;
        } else if (engineCapacity < 2500) {
            premium += 400.0;
        } else {
            premium += 600.0;
        }

        // Add premium for additional coverage
        if (hasAdditionalCoverage) {
            premium += 300.0;
        }

        setPremiumAmount(premium);
    }

    @Override
    public boolean processClaim(double claimAmount) {
        if (!validatePolicy()) {
            return false;
        }

        if (claimAmount > getCoverageAmount()) {
            return false;
        }

        System.out.println("Processing third-party claim for $" + claimAmount);
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("\n===== Third Party Policy Report =====");
        System.out.println("Policy ID: " + getPolicyId());
        System.out.println("Policy Type: Third Party");
        System.out.println("Policy Holder: " + getPolicyHolder().getFullName());
        System.out.println("Contact: " + getPolicyHolder().getEmail() + ", " + getPolicyHolder().getPhone());
        System.out.println("\nVehicle Details:");
        System.out.println("Make: " + getVehicle().getVehicleMake());
        System.out.println("Model: " + getVehicle().getVehicleModel());
        System.out.println("Year: " + getVehicle().getVehicleYear());
        System.out.println("Type: " + getVehicle().getVehicleType());
        System.out.println("Engine Capacity: " + getVehicle().getEngineCapacity() + "cc");
        System.out.println("\nCoverage Details:");
        System.out.println("Coverage Amount: $" + getCoverageAmount());
        System.out.println("Premium Amount: $" + getPremiumAmount());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Start Date: " + getPolicyStartDate().format(formatter));
        System.out.println("End Date: " + getPolicyEndDate().format(formatter));
        System.out.println("Status: " + (isPolicyActive() ? "Active" : "Inactive"));
        System.out.println("Additional Coverage: " + (hasAdditionalCoverage ? "Yes" : "No"));
        System.out.println("\nCoverage Includes:");
        System.out.println("- Third-party bodily injury");
        System.out.println("- Third-party property damage");
        if (hasAdditionalCoverage) {
            System.out.println("- Legal expenses");
            System.out.println("- Emergency medical costs");
        }
        System.out.println("======================================");
    }

    @Override
    public boolean validatePolicy() {
        // Check if date range is valid
        if (!isValidDateRange()) {
            System.out.println("End date must be after start date.");
            return false;
        }

        return true;
    }
}