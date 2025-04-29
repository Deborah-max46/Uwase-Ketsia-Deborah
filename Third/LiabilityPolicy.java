package Third;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

class LiabilityPolicy extends InsurancePolicy {
    private static final double BASE_PREMIUM = 600.0;
    private boolean hasMedicalCheckup;
    private boolean hasExtendedCoverage;

    public LiabilityPolicy(Vehicle vehicle, Person policyHolder, double coverageAmount,
                           LocalDate policyStartDate, LocalDate policyEndDate,
                           boolean hasMedicalCheckup, boolean hasExtendedCoverage) {
        super(vehicle, policyHolder, coverageAmount, policyStartDate, policyEndDate);
        this.hasMedicalCheckup = hasMedicalCheckup;
        this.hasExtendedCoverage = hasExtendedCoverage;
    }

    @Override
    public void calculatePremium() {
        double premium = BASE_PREMIUM;

        // Adjust premium based on policy holder age
        int age = Period.between(getPolicyHolder().getDob(), LocalDate.now()).getYears();
        if (age < 25) {
            premium += 300.0;
        } else if (age > 65) {
            premium += 200.0;
        }

        // Medical checkup discount
        if (hasMedicalCheckup) {
            premium *= 0.9; // 10% discount
        }

        // Extended coverage premium
        if (hasExtendedCoverage) {
            premium += 400.0;
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

        System.out.println("Processing liability claim for $" + claimAmount);
        return true;
    }

    @Override
    public void generatePolicyReport() {
        System.out.println("\n===== Liability Policy Report =====");
        System.out.println("Policy ID: " + getPolicyId());
        System.out.println("Policy Type: Liability");
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
        System.out.println("Medical Checkup: " + (hasMedicalCheckup ? "Yes" : "No"));
        System.out.println("Extended Disability Coverage: " + (hasExtendedCoverage ? "Yes" : "No"));
        System.out.println("\nCoverage Includes:");
        System.out.println("- Bodily injury to others");
        System.out.println("- Property damage liability");
        if (hasExtendedCoverage) {
            System.out.println("- Long-term disability coverage");
            System.out.println("- Medical expenses for long-term care");
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

        // Check if medical checkup is required but not done
        if (!hasMedicalCheckup) {
            System.out.println("Medical checkup is required for liability policy.");
            return false;
        }

        return true;
    }
}


