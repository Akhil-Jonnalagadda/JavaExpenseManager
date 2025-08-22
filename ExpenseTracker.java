
import java.io.*;
import java.util.*;

/**
 * ExpenseTracker is the main class for managing expenses.
 * It provides a simple console interface for users to add, view, and filter expenses.
 */
public class ExpenseTracker {
    // List to store all expenses
    private final List<Expense> expenses = new ArrayList<>();
    // Scanner for user input
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Main method to start the application.
     */
    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.run();
    }

    /**
     * Runs the main menu loop.
     */
    public void run() {
        loadExpenses(); // Load expenses from file at startup
        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. List All Expenses");
            System.out.println("3. Filter by Category");
            System.out.println("4. Show Total by Category");
            System.out.println("5. Filter by Date Range");
            System.out.println("6. Export Expenses to CSV");
            System.out.println("7. Save & Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1: addExpense(); break;
                case 2: listExpenses(); break;
                case 3: filterByCategory(); break;
                case 4: showTotalByCategory(); break;
                case 5: filterByDateRange(); break;
                case 6: exportToCSV(); break;
                case 7: saveExpenses(); System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Adds a new expense based on user input.
     */
    private void addExpense() {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        // Show available categories from enum
        System.out.println("Select category:");
        Expense.Category[] categories = Expense.Category.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }
        System.out.print("Enter category number: ");
        int catChoice = scanner.nextInt();
        scanner.nextLine();
        Expense.Category category;
        if (catChoice >= 1 && catChoice <= categories.length) {
            category = categories[catChoice - 1];
        } else {
            System.out.println("Invalid category. Defaulting to OTHER.");
            category = Expense.Category.OTHER;
        }
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        try {
            expenses.add(new Expense(amount, category, date, description));
            System.out.println("Expense added successfully.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Lists all recorded expenses.
     */
    private void listExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.println("\nAll Expenses:");
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    /**
     * Filters expenses by category and displays them.
     */
    private void filterByCategory() {
        // Show available categories from enum
        System.out.println("Select category to filter:");
        Expense.Category[] categories = Expense.Category.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + ". " + categories[i]);
        }
        System.out.print("Enter category number: ");
        int catChoice = scanner.nextInt();
        scanner.nextLine();
        Expense.Category category;
        if (catChoice >= 1 && catChoice <= categories.length) {
            category = categories[catChoice - 1];
        } else {
            System.out.println("Invalid category. Defaulting to OTHER.");
            category = Expense.Category.OTHER;
        }
        boolean found = false;
        for (Expense e : expenses) {
            if (e.getCategory() == category) {
                System.out.println(e);
                found = true;
            }
        }
        if (!found) System.out.println("No expenses found for this category.");
    }

    /**
     * Shows total expenses for each category.
     */
    private void showTotalByCategory() {
        Map<Expense.Category, Double> totals = new HashMap<>();
        for (Expense e : expenses) {
            Expense.Category cat = e.getCategory();
            totals.put(cat, totals.getOrDefault(cat, 0.0) + e.getAmount());
        }
        System.out.println("\nTotal Expenses by Category:");
        for (Expense.Category cat : totals.keySet()) {
            System.out.println(cat + ": Rs." + totals.get(cat));
        }
    }

    /**
     * Filters expenses by a date range and displays them.
     */
    private void filterByDateRange() {
        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        boolean found = false;
        for (Expense e : expenses) {
            if (e.getDate().compareTo(startDate) >= 0 && e.getDate().compareTo(endDate) <= 0) {
                System.out.println(e);
                found = true;
            }
        }
        if (!found) System.out.println("No expenses found in this date range.");
    }

    /**
     * Exports all expenses to a CSV file.
     */
    private void exportToCSV() {
        System.out.print("Enter CSV file name (e.g., expenses.csv): ");
        String fileName = scanner.nextLine();
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            pw.println("Amount,Category,Date,Description");
            for (Expense e : expenses) {
                pw.println(e.getAmount() + "," + e.getCategory().name() + "," + e.getDate() + "," + e.getDescription().replace(",", " "));
            }
            System.out.println("Expenses exported to " + fileName);
        } catch (IOException ex) {
            System.out.println("Error exporting to CSV: " + ex.getMessage());
        }
    }

    /**
     * Saves expenses to a file for persistence.
     */
    private void saveExpenses() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("expenses.txt"))) {
            for (Expense e : expenses) {
                pw.println(e.getAmount() + "," + e.getCategory().name() + "," + e.getDate() + "," + e.getDescription());
            }
            System.out.println("Expenses saved to file.");
        } catch (IOException ex) {
            System.out.println("Error saving expenses: " + ex.getMessage());
        }
    }

    /**
     * Loads expenses from a file at startup.
     */
    private void loadExpenses() {
        File file = new File("expenses.txt");
        if (!file.exists()) return;
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split(",", 4);
                if (parts.length == 4) {
                    double amount = Double.parseDouble(parts[0]);
                    Expense.Category category;
                    try {
                        category = Expense.Category.valueOf(parts[1]);
                    } catch (IllegalArgumentException ex) {
                        category = Expense.Category.OTHER;
                    }
                    String date = parts[2];
                    String description = parts[3];
                    try {
                        expenses.add(new Expense(amount, category, date, description));
                    } catch (IllegalArgumentException ex) {
                        // Skip invalid expense
                    }
                }
            }
            System.out.println("Loaded expenses from file.");
        } catch (Exception ex) {
            System.out.println("Error loading expenses: " + ex.getMessage());
        }
    }
}
