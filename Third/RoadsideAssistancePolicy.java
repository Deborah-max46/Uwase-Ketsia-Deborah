package Third;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class RoadsideAssistancePolicy extends InsurancePolicy {
    private static final double BASE_PREMIUM = 300.0;
    private boolean isCommercialUse;
    private boolean passedInspection;

    public RoadsideAssistancePolicy(Vehicle vehicle, Person policyHolder, double coverageAmount,
                                    LocalDate policyStartDate, LocalDate policyEndDate,
                                    boolean isCommercialUse, boolean passedInspection) {
        super(vehicle, policyHolder, coverageAmount, policyStartDate, policyEndDate);
        this.isCommercialUse = isCommercialUse;
        this.passedInspection = passedInspection;
    }

    @Override
    public void calculatePremium() {
        double premium = BASE_PREMIUM;

        // Adjust premium based on commercial use
        if (isCommercialUse) {
            premium *= 1.5; // 50% additional for commercial use
        }

        // Vehicle age adjustment
        int vehicleAge = LocalDate.now().getYear() - getVehicle().getVehicleYear();
        if (vehicleAge > 10) {
            premium += 200.0;
        } else if (vehicleAge > 5) {
            premium += 100.0;
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

        System.out.println("Processing roadside assistance claim for $" + claimAmount);
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("\n===== Roadside Assistance Policy Report =====");
        System.out.println("Policy ID: " + getPolicyId());
        System.out.println("Policy Type: Roadside Assistance");
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
        System.out.println("Commercial Use: " + (isCommercialUse ? "Yes" : "No"));
        System.out.println("Passed Inspection: " + (passedInspection ? "Yes" : "No"));
        System.out.println("\nCoverage Includes:");
        System.out.println("- Towing services");
        System.out.println("- Flat tire assistance");
        System.out.println("- Battery jump-start");
        System.out.println("- Fuel delivery");
        System.out.println("- Lockout services");
        System.out.println("=========================================");
    }

    @Override
    public boolean validatePolicy() {
        // Check if date range is valid
        if (!isValidDateRange()) {
            System.out.println("End date must be after start date.");
            return false;
        }

        // Check if vehicle has passed inspection
        if (!passedInspection) {
            System.out.println("Vehicle must pass inspection for roadside assistance policy.");
            return false;
        }

        return true;
    }
}
