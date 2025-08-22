
/**
 * Expense class represents a single expense entry.
 * Each expense has an amount, category, date, and description.
 * Includes input validation, setters, and category enum for better structure.
 */
public class Expense implements Comparable<Expense> {
    /**
     * Enum for standard expense categories.
     */
    public enum Category {
        FOOD, TRAVEL, ENTERTAINMENT, UTILITIES, SHOPPING, OTHER
    }

    // Amount spent
    private double amount;
    // Category of the expense
    private Category category;
    // Date of the expense in YYYY-MM-DD format
    private String date;
    // Description or note about the expense
    private String description;

    /**
     * Constructor to initialize an expense.
     * @param amount Amount spent (must be >= 0)
     * @param category Category of the expense
     * @param date Date in YYYY-MM-DD format
     * @param description Description or note
     * @throws IllegalArgumentException if amount is negative or date is invalid
     */
    public Expense(double amount, Category category, String date, String description) {
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative.");
        if (category == null) throw new IllegalArgumentException("Category cannot be null.");
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Date must be in YYYY-MM-DD format.");
        }
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.description = description;
    }

    /**
     * Gets the amount spent.
     * @return amount
     */
    public double getAmount() { return amount; }

    /**
     * Sets the amount spent. Must be >= 0.
     * @param amount Amount spent
     */
    public void setAmount(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative.");
        this.amount = amount;
    }

    /**
     * Gets the category of the expense.
     * @return category
     */
    public Category getCategory() { return category; }

    /**
     * Sets the category of the expense.
     * @param category Expense category
     */
    public void setCategory(Category category) {
        if (category == null) throw new IllegalArgumentException("Category cannot be null.");
        this.category = category;
    }

    /**
     * Gets the date of the expense.
     * @return date in YYYY-MM-DD format
     */
    public String getDate() { return date; }

    /**
     * Sets the date of the expense. Must be in YYYY-MM-DD format.
     * @param date Date string
     */
    public void setDate(String date) {
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new IllegalArgumentException("Date must be in YYYY-MM-DD format.");
        }
        this.date = date;
    }

    /**
     * Gets the description of the expense.
     * @return description
     */
    public String getDescription() { return description; }

    /**
     * Sets the description of the expense.
     * @param description Description string
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the expense.
     * @return formatted string
     */
    @Override
    public String toString() {
        return date + " | " + category + " | Rs." + amount + " | " + description;
    }

    /**
     * Compares expenses by date (lexicographically).
     * @param other Another expense
     * @return comparison result
     */
    @Override
    public int compareTo(Expense other) {
        return this.date.compareTo(other.date);
    }
}
