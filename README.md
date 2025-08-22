# Personal Expense Tracker (Java)

A simple console-based Java application to record, view, and categorize daily expenses.

## Features
- Add new expenses (amount, category, date, description)
- List all expenses
- Filter expenses by category
- Show total expenses per category
- Save/load expenses from a file

## How to Run
1. Compile both `Expense.java` and `ExpenseTracker.java`:
   ```
   javac Expense.java ExpenseTracker.java
   ```
2. Run the application:
   ```
   java ExpenseTracker
   ```

## Code Structure
- `Expense.java`: Model class for an expense entry
- `ExpenseTracker.java`: Main logic and user interface
- `expenses.txt`: Data file for saved expenses

## Comments & Documentation
- All classes and methods are commented for clarity.
- Read the code to understand the flow and logic.

## Next Steps
- Add more features (date filtering, export to CSV, etc.)
- Build a GUI or mobile version
