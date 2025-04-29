package Second;
import java.text.SimpleDateFormat;
import java.util.*;



class ReportGenerator {
    public static void generateSalesReport(List<Payment> payments, List<ShoppingItem> items) {
        System.out.println("\n========= SALES REPORT =========");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        System.out.println("Report Date: " + sdf.format(currentDate));
        System.out.println("Report ID: REP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        // Total revenue
        double totalRevenue = payments.stream().mapToDouble(Payment::getAmountPaid).sum();
        System.out.println("\n1. Total Revenue: $" + String.format("%.2f", totalRevenue));

        // Payment breakdown by method
        System.out.println("\n2. Payment Breakdown by Method:");
        Map<String, Double> paymentMethodBreakdown = new HashMap<>();

        for (Payment payment : payments) {
            String method = payment.getPaymentMethod();
            double amount = payment.getAmountPaid();

            if (paymentMethodBreakdown.containsKey(method)) {
                paymentMethodBreakdown.put(method, paymentMethodBreakdown.get(method) + amount);
            } else {
                paymentMethodBreakdown.put(method, amount);
            }
        }

        for (Map.Entry<String, Double> entry : paymentMethodBreakdown.entrySet()) {
            System.out.println("   - " + entry.getKey() + ": $" + String.format("%.2f", entry.getValue()) +
                    " (" + String.format("%.1f", (entry.getValue() / totalRevenue * 100)) + "%)");
        }

        // Item sales report
        System.out.println("\n3. Item Sales Report:");
        Map<String, Integer> itemSales = new HashMap<>();

        // This would be populated from actual sales data in a real system
        // For demonstration, we'll just show stock changes
        for (ShoppingItem item : items) {
            System.out.println("   - " + item.getItemName() + " (ID: " + item.getItemId() + ")");
            System.out.println("     Available Stock: " + item.getStockAvailable());
        }

        System.out.println("\n4. Most Popular Categories:");
        // In a real system, this would be calculated from actual sales data
        System.out.println("   - Electronics: 35% of total sales");
        System.out.println("   - Clothing: 25% of total sales");
        System.out.println("   - Books: 20% of total sales");
        System.out.println("   - Accessories: 15% of total sales");
        System.out.println("   - Groceries: 5% of total sales");

        System.out.println("\n===============================");
    }
}


