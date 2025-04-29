package Third;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


class CollisionPolicy extends InsurancePolicy {
    private static final double BASE_PREMIUM = 800.0;
    private boolean safeDriver;

    public CollisionPolicy(Vehicle vehicle, Person policyHolder, double coverageAmount,
                           LocalDate policyStartDate, LocalDate policyEndDate, boolean safeDriver) {
        super(vehicle, policyHolder, coverageAmount, policyStartDate, policyEndDate);
        this.safeDriver = safeDriver;
    }

    @Override
    public void calculatePremium() {
        double premium = BASE_PREMIUM;

        // Add premium based on vehicle type
        String vehicleType = getVehicle().getVehicleType();
        if (vehicleType.equals("Sports Car") || vehicleType.equals("Luxury")) {
            premium += 500.0;
        } else if (vehicleType.equals("SUV") || vehicleType.equals("Truck")) {
            premium += 300.0;
        } else {
            premium += 200.0;
        }

        // Safe driver discount
        if (safeDriver) {
            premium *= 0.8; // 20% discount
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

        System.out.println("Processing collision claim for $" + claimAmount);
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("\n===== Collision Policy Report =====");
        System.out.println("Policy ID: " + getPolicyId());
        System.out.println("Policy Type: Collision");
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
        System.out.println("Safe Driver Discount: " + (safeDriver ? "Yes" : "No"));
        System.out.println("\nCoverage Includes:");
        System.out.println("- Collision damage to own vehicle");
        System.out.println("- Rollovers");
        System.out.println("- Vehicle repairs");
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