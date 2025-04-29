package Third;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


class ComprehensivePolicy extends InsurancePolicy {
    private static final int MIN_VEHICLE_YEAR = 1990;
    private static final double BASE_PREMIUM = 1000.0;

    public ComprehensivePolicy(Vehicle vehicle, Person policyHolder, double coverageAmount,
                               LocalDate policyStartDate, LocalDate policyEndDate) {
        super(vehicle, policyHolder, coverageAmount, policyStartDate, policyEndDate);
    }

    @Override
    public void calculatePremium() {
        int vehicleAge = LocalDate.now().getYear() - getVehicle().getVehicleYear();
        double premium = BASE_PREMIUM;

        // Add premium based on vehicle age
        if (vehicleAge < 3) {
            premium += 500.0;
        } else if (vehicleAge < 7) {
            premium += 300.0;
        } else if (vehicleAge < 12) {
            premium += 600.0;
        } else {
            premium += 1000.0;
        }

        // Add premium based on coverage amount
        premium += getCoverageAmount() * 0.05;

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

        System.out.println("Processing comprehensive claim for $" + claimAmount);
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("\n===== Comprehensive Policy Report =====");
        System.out.println("Policy ID: " + getPolicyId());
        System.out.println("Policy Type: Comprehensive");
        System.out.println("Policy Holder: " + getPolicyHolder().getFullName());
        System.out.println("Contact: " + getPolicyHolder().getEmail() + ", " + getPolicyHolder().getPhone());
        System.out.println("\nVehicle Details:");
        System.out.println("Make: " + getVehicle().getVehicleMake());
        System.out.println("Model: " + getVehicle().getVehicleModel());
        System.out.println("Year: " + getVehicle().getVehicleYear());
        System.out.println("Type: " + getVehicle().getVehicleType());
        System.out.println("\nCoverage Details:");
        System.out.println("Coverage Amount: $" + getCoverageAmount());
        System.out.println("Premium Amount: $" + getPremiumAmount());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Start Date: " + getPolicyStartDate().format(formatter));
        System.out.println("End Date: " + getPolicyEndDate().format(formatter));
        System.out.println("Status: " + (isPolicyActive() ? "Active" : "Inactive"));
        System.out.println("\nCoverage Includes:");
        System.out.println("- Collision damage");
        System.out.println("- Theft protection");
        System.out.println("- Natural disasters");
        System.out.println("- Third-party liability");
        System.out.println("======================================");
    }

    @Override
    public boolean validatePolicy() {
        // Check if vehicle year is valid
        if (getVehicle().getVehicleYear() < MIN_VEHICLE_YEAR) {
            System.out.println("Vehicle year must be " + MIN_VEHICLE_YEAR + " or newer for comprehensive policy.");
            return false;
        }

        // Check if date range is valid
        if (!isValidDateRange()) {
            System.out.println("End date must be after start date.");
            return false;
        }

        return true;
    }
}