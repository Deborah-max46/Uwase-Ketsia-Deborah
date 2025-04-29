package Third;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

// Main class to run the insurance system
class InsuranceManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<InsurancePolicy> policies = new ArrayList<>();
    private static List<Vehicle> vehicles = new ArrayList<>();
    private static List<Person> policyHolders = new ArrayList<>();
    private static List<Claim> claims = new ArrayList<>();
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        System.out.println("==== Welcome to Advanced Motor Vehicle Insurance System ====");

        // Pre-populate some data for testing
        populateSampleData();

        boolean exit = false;
        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Register New Policy Holder");
            System.out.println("2. Register New Vehicle");
            System.out.println("3. Create New Insurance Policy");
            System.out.println("4. Process Insurance Claim");
            System.out.println("5. Generate Reports");
            System.out.println("6. View All Policy Holders");
            System.out.println("7. View All Vehicles");
            System.out.println("8. View All Policies");
            System.out.println("9. View All Claims");
            System.out.println("0. Exit System");

            System.out.print("Please select an option: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    registerPolicyHolder();
                    break;
                case 2:
                    registerVehicle();
                    break;
                case 3:
                    createPolicy();
                    break;
                case 4:
                    processInsuranceClaim();
                    break;
                case 5:
                    generateReports();
                    break;
                case 6:
                    viewAllPolicyHolders();
                    break;
                case 7:
                    viewAllVehicles();
                    break;
                case 8:
                    viewAllPolicies();
                    break;
                case 9:
                    viewAllClaims();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Thank you for using Advanced Motor Vehicle Insurance System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // Helper method for integer input with validation
    private static int getIntInput() {
        int input = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }

        return input;
    }

    // Helper method for double input with validation
    private static double getDoubleInput() {
        double input = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                input = Double.parseDouble(scanner.nextLine().trim());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }

        return input;
    }

    // Helper method for date input with validation
    private static LocalDate getDateInput() {
        LocalDate date = null;
        boolean validInput = false;

        while (!validInput) {
            try {
                String input = scanner.nextLine().trim();
                date = LocalDate.parse(input, dateFormatter);
                validInput = true;
            } catch (DateTimeParseException e) {
                System.out.print("Invalid date format. Please use DD/MM/YYYY format: ");
            }
        }

        return date;
    }

    // Method to register a new policy holder
    private static void registerPolicyHolder() {
        System.out.println("\n==== Register New Policy Holder ====");

        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine().trim();

        System.out.print("Enter Date of Birth (DD/MM/YYYY): ");
        LocalDate dob = getDateInput();

        System.out.print("Enter Email Address: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine().trim();

        Person person = new Person(fullName, dob, email, phone);
        if (person.validate()) {
            policyHolders.add(person);
            System.out.println("Policy holder registered successfully with ID: " + person.getPersonId());
        } else {
            System.out.println("Failed to register policy holder. Please check the provided details.");
        }
    }

    // Method to register a new vehicle
    private static void registerVehicle() {
        System.out.println("\n==== Register New Vehicle ====");

        System.out.print("Enter Vehicle Make: ");
        String make = scanner.nextLine().trim();

        System.out.print("Enter Vehicle Model: ");
        String model = scanner.nextLine().trim();

        System.out.print("Enter Vehicle Year: ");
        int year = getIntInput();

        System.out.println("Select Vehicle Type:");
        System.out.println("1. Sedan");
        System.out.println("2. SUV");
        System.out.println("3. Truck");
        System.out.println("4. Motorcycle");
        System.out.println("5. Van");
        System.out.print("Enter your choice: ");
        int typeChoice = getIntInput();

        String vehicleType;
        switch (typeChoice) {
            case 1:
                vehicleType = "Sedan";
                break;
            case 2:
                vehicleType = "SUV";
                break;
            case 3:
                vehicleType = "Truck";
                break;
            case 4:
                vehicleType = "Motorcycle";
                break;
            case 5:
                vehicleType = "Van";
                break;
            default:
                vehicleType = "Other";
        }

        System.out.print("Enter Engine Capacity (cc): ");
        int engineCapacity = getIntInput();

        Vehicle vehicle = new Vehicle(make, model, year, vehicleType, engineCapacity);
        if (vehicle.validate()) {
            vehicles.add(vehicle);
            System.out.println("Vehicle registered successfully with ID: " + vehicle.getVehicleId());
        } else {
            System.out.println("Failed to register vehicle. Please check the provided details.");
        }
    }

    // Method to create a new insurance policy
    private static void createPolicy() {
        if (policyHolders.isEmpty() || vehicles.isEmpty()) {
            System.out.println("You need to register at least one policy holder and one vehicle before creating a policy.");
            return;
        }

        System.out.println("\n==== Create New Insurance Policy ====");

        // Select policy holder
        System.out.println("Select Policy Holder:");
        for (int i = 0; i < policyHolders.size(); i++) {
            Person holder = policyHolders.get(i);
            System.out.println((i + 1) + ". " + holder.getFullName() + " (" + holder.getPersonId() + ")");
        }
        System.out.print("Enter your choice: ");
        int holderChoice = getIntInput() - 1;

        if (holderChoice < 0 || holderChoice >= policyHolders.size()) {
            System.out.println("Invalid policy holder selection.");
            return;
        }

        Person selectedHolder = policyHolders.get(holderChoice);

        // Select vehicle
        System.out.println("Select Vehicle:");
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            System.out.println((i + 1) + ". " + v.getVehicleMake() + " " + v.getVehicleModel() + " (" + v.getVehicleId() + ")");
        }
        System.out.print("Enter your choice: ");
        int vehicleChoice = getIntInput() - 1;

        if (vehicleChoice < 0 || vehicleChoice >= vehicles.size()) {
            System.out.println("Invalid vehicle selection.");
            return;
        }

        Vehicle selectedVehicle = vehicles.get(vehicleChoice);

        // Select policy type
        System.out.println("Select Policy Type:");
        System.out.println("1. Comprehensive Policy");
        System.out.println("2. Third Party Policy");
        System.out.println("3. Collision Policy");
        System.out.println("4. Liability Policy");
        System.out.println("5. Roadside Assistance Policy");
        System.out.print("Enter your choice: ");
        int policyChoice = getIntInput();

        System.out.print("Enter Coverage Amount: $");
        double coverageAmount = getDoubleInput();

        System.out.print("Enter Policy Start Date (DD/MM/YYYY): ");
        LocalDate startDate = getDateInput();

        System.out.print("Enter Policy End Date (DD/MM/YYYY): ");
        LocalDate endDate = getDateInput();

        InsurancePolicy policy = null;

        switch (policyChoice) {
            case 1:
                policy = new ComprehensivePolicy(selectedVehicle, selectedHolder, coverageAmount, startDate, endDate);
                break;
            case 2:
                policy = new ThirdPartyPolicy(selectedVehicle, selectedHolder, coverageAmount, startDate, endDate);
                break;
            case 3:
                System.out.print("Is the driver considered safe? (yes/no): ");
                boolean safeDriver = scanner.nextLine().trim().equalsIgnoreCase("yes");

                policy = new CollisionPolicy(selectedVehicle, selectedHolder, coverageAmount, startDate, endDate, safeDriver);
                break;
            case 4:
                System.out.print("Has the policy holder completed a medical checkup? (yes/no): ");
                boolean hasMedicalCheckup = scanner.nextLine().trim().equalsIgnoreCase("yes");

                System.out.print("Include extended disability coverage? (yes/no): ");
                boolean hasExtendedCoverage = scanner.nextLine().trim().equalsIgnoreCase("yes");

                policy = new LiabilityPolicy(selectedVehicle, selectedHolder, coverageAmount, startDate, endDate,
                        hasMedicalCheckup, hasExtendedCoverage);
                break;
            case 5:
                System.out.print("Is this for commercial use? (yes/no): ");
                boolean isCommercial = scanner.nextLine().trim().equalsIgnoreCase("yes");

                System.out.print("Has the vehicle passed inspection? (yes/no): ");
                boolean passedInspection = scanner.nextLine().trim().equalsIgnoreCase("yes");

                policy = new RoadsideAssistancePolicy(selectedVehicle, selectedHolder, coverageAmount, startDate, endDate,
                        isCommercial, passedInspection);
                break;
            default:
                System.out.println("Invalid policy type selection.");
                return;
        }

        if (policy != null && policy.validatePolicy()) {
            policy.calculatePremium();
            policies.add(policy);
            System.out.println("Policy created successfully with ID: " + policy.getPolicyId());
            System.out.println("Premium Amount: $" + policy.getPremiumAmount());
        } else {
            System.out.println("Failed to create policy. Please check the provided details and try again.");
        }
    }

    // Method to process an insurance claim
    private static void processInsuranceClaim() {
        if (policies.isEmpty()) {
            System.out.println("No policies found. Please create a policy first.");
            return;
        }

        System.out.println("\n==== Process Insurance Claim ====");

        // Select policy
        System.out.println("Select Policy:");
        for (int i = 0; i < policies.size(); i++) {
            InsurancePolicy policy = policies.get(i);
            System.out.println((i + 1) + ". " + policy.getPolicyId() + " - " +
                    policy.getVehicle().getVehicleMake() + " " + policy.getVehicle().getVehicleModel() + " - " +
                    policy.getPolicyHolder().getFullName());
        }
        System.out.print("Enter your choice: ");
        int policyChoice = getIntInput() - 1;

        if (policyChoice < 0 || policyChoice >= policies.size()) {
            System.out.println("Invalid policy selection.");
            return;
        }

        InsurancePolicy selectedPolicy = policies.get(policyChoice);

        System.out.print("Enter Claim Amount: $");
        double claimAmount = getDoubleInput();

        System.out.print("Enter Claim Date (DD/MM/YYYY): ");
        LocalDate claimDate = getDateInput();

        System.out.print("Enter Claim Description: ");
        String claimDescription = scanner.nextLine().trim();

        Claim claim = new Claim(claimAmount, claimDate, claimDescription, selectedPolicy);

        if (claim.validate()) {
            boolean claimProcessed = selectedPolicy.processClaim(claimAmount);

            if (claimProcessed) {
                claim.setClaimStatus("Approved");
                claims.add(claim);
                System.out.println("Claim processed successfully with ID: " + claim.getClaimId());
            } else {
                claim.setClaimStatus("Rejected");
                claims.add(claim);
                System.out.println("Claim rejected. Amount exceeds policy coverage or policy validation failed.");
            }
        } else {
            System.out.println("Failed to process claim. Please check the provided details.");
        }
    }

    // Method to generate reports
    private static void generateReports() {
        System.out.println("\n==== Generate Reports ====");
        System.out.println("1. Generate Policy Report");
        System.out.println("2. Generate System Summary Report");
        System.out.print("Enter your choice: ");
        int choice = getIntInput();

        switch (choice) {
            case 1:
                generatePolicyReport();
                break;
            case 2:
                generateSystemSummaryReport();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    // Method to generate a policy report
    private static void generatePolicyReport() {
        if (policies.isEmpty()) {
            System.out.println("No policies found. Please create a policy first.");
            return;
        }

        System.out.println("Select Policy to Generate Report:");
        for (int i = 0; i < policies.size(); i++) {
            InsurancePolicy policy = policies.get(i);
            System.out.println((i + 1) + ". " + policy.getPolicyId() + " - " +
                    policy.getVehicle().getVehicleMake() + " " + policy.getVehicle().getVehicleModel() + " - " +
                    policy.getPolicyHolder().getFullName());
        }
        System.out.print("Enter your choice: ");
        int policyChoice = getIntInput() - 1;

        if (policyChoice < 0 || policyChoice >= policies.size()) {
            System.out.println("Invalid policy selection.");
            return;
        }

        InsurancePolicy selectedPolicy = policies.get(policyChoice);
        selectedPolicy.generatePolicyReport();
    }

    // Method to generate a system summary report
    private static void generateSystemSummaryReport() {
        System.out.println("\n==== System Summary Report ====");

        // Calculate total premiums
        double totalPremiums = policies.stream().mapToDouble(InsurancePolicy::getPremiumAmount).sum();

        // Count claims by status
        int approvedClaims = 0;
        int rejectedClaims = 0;
        double totalClaimAmount = 0;

        for (Claim claim : claims) {
            if (claim.getClaimStatus().equals("Approved")) {
                approvedClaims++;
                totalClaimAmount += claim.getClaimAmount();
            } else if (claim.getClaimStatus().equals("Rejected")) {
                rejectedClaims++;
            }
        }

        // Count policies by type
        Map<String, Integer> policyTypeCount = new HashMap<>();
        Map<String, Double> policyTypePremiums = new HashMap<>();

        for (InsurancePolicy policy : policies) {
            String policyType = policy.getClass().getSimpleName();
            policyTypeCount.put(policyType, policyTypeCount.getOrDefault(policyType, 0) + 1);
            policyTypePremiums.put(policyType, policyTypePremiums.getOrDefault(policyType, 0.0) + policy.getPremiumAmount());
        }

        // Print report
        System.out.println("Total Policy Holders: " + policyHolders.size());
        System.out.println("Total Vehicles: " + vehicles.size());
        System.out.println("Total Policies: " + policies.size());
        System.out.println("Total Premiums Collected: $" + totalPremiums);
        System.out.println("\nClaims Summary:");
        System.out.println("Total Claims: " + claims.size());
        System.out.println("Approved Claims: " + approvedClaims);
        System.out.println("Rejected Claims: " + rejectedClaims);
        System.out.println("Total Claim Amount Paid: $" + totalClaimAmount);

        System.out.println("\nPolicy Type Breakdown:");
        for (Map.Entry<String, Integer> entry : policyTypeCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " policies, $" +
                    policyTypePremiums.get(entry.getKey()) + " in premiums");
        }
    }

    // Method to view all policy holders
    private static void viewAllPolicyHolders() {
        if (policyHolders.isEmpty()) {
            System.out.println("No policy holders found.");
            return;
        }

        System.out.println("\n==== All Policy Holders ====");
        for (Person holder : policyHolders) {
            System.out.println("ID: " + holder.getPersonId());
            System.out.println("Name: " + holder.getFullName());
            System.out.println("DOB: " + holder.getDob().format(dateFormatter));
            System.out.println("Email: " + holder.getEmail());
            System.out.println("Phone: " + holder.getPhone());
            System.out.println("------------------------");
        }
    }

    // Method to view all vehicles
    private static void viewAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.println("\n==== All Vehicles ====");
        for (Vehicle vehicle : vehicles) {
            System.out.println("ID: " + vehicle.getVehicleId());
            System.out.println("Make: " + vehicle.getVehicleMake());
            System.out.println("Model: " + vehicle.getVehicleModel());
            System.out.println("Year: " + vehicle.getVehicleYear());
            System.out.println("Type: " + vehicle.getVehicleType());
            System.out.println("Engine Capacity: " + vehicle.getEngineCapacity() + "cc");
            System.out.println("------------------------");
        }
    }

    // Method to view all policies
    private static void viewAllPolicies() {
        if (policies.isEmpty()) {
            System.out.println("No policies found.");
            return;
        }

        System.out.println("\n==== All Policies ====");
        for (InsurancePolicy policy : policies) {
            System.out.println("ID: " + policy.getPolicyId());
            System.out.println("Type: " + policy.getClass().getSimpleName());
            System.out.println("Policy Holder: " + policy.getPolicyHolder().getFullName());
            System.out.println("Vehicle: " + policy.getVehicle().getVehicleMake() + " " + policy.getVehicle().getVehicleModel());
            System.out.println("Coverage: $" + policy.getCoverageAmount());
            System.out.println("Premium: $" + policy.getPremiumAmount());
            System.out.println("Start Date: " + policy.getPolicyStartDate().format(dateFormatter));
            System.out.println("End Date: " + policy.getPolicyEndDate().format(dateFormatter));
            System.out.println("------------------------");
        }
    }

    // Method to view all claims
    private static void viewAllClaims() {
        if (claims.isEmpty()) {
            System.out.println("No claims found.");
            return;
        }

        System.out.println("\n==== All Claims ====");
        for (Claim claim : claims) {
            System.out.println("ID: " + claim.getClaimId());
            System.out.println("Policy: " + claim.getPolicy().getPolicyId());
            System.out.println("Amount: $" + claim.getClaimAmount());
            System.out.println("Date: " + claim.getClaimDate().format(dateFormatter));
            System.out.println("Status: " + claim.getClaimStatus());
            System.out.println("Description: " + claim.getDescription());
            System.out.println("------------------------");
        }
    }

    // Method to pre-populate sample data for testing
    private static void populateSampleData() {
        // Add sample policy holders
        Person person1 = new Person("John Smith", LocalDate.of(1985, 5, 12), "john@example.com", "123-456-7890");
        Person person2 = new Person("Jane Doe", LocalDate.of(1990, 3, 22), "jane@example.com", "987-654-3210");
        policyHolders.add(person1);
        policyHolders.add(person2);

        // Add sample vehicles
        Vehicle vehicle1 = new Vehicle("Toyota", "Camry", 2018, "Sedan", 2500);
        Vehicle vehicle2 = new Vehicle("Honda", "CR-V", 2020, "SUV", 1800);
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        // Add sample policies
        InsurancePolicy policy1 = new ComprehensivePolicy(vehicle1, person1, 50000.0, LocalDate.now(), LocalDate.now().plusYears(1));
        policy1.calculatePremium();

        InsurancePolicy policy2 = new ThirdPartyPolicy(vehicle2, person2, 30000.0, LocalDate.now(), LocalDate.now().plusYears(1));
        policy2.calculatePremium();

        policies.add(policy1);
        policies.add(policy2);
    }
}
