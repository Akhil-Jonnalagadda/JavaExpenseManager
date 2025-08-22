# Personal Expense Tracker (Java)

A simple console-based Java application to record, view, and categorize daily expenses.

## Features
- Add new expenses (amount, category, date, description)
- List all expenses
- Filter expenses by category
- Filter expenses by date range
- Show total expenses per category
- Export expenses to CSV file
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

## Usage
- Add, list, and filter expenses using the menu options.
- To filter by date range, select the corresponding menu option and enter start/end dates in `YYYY-MM-DD` format.
- To export expenses, select the export option and provide a CSV filename (e.g., `expenses.csv`).

## Code Structure
- `Expense.java`: Model class for an expense entry
- `ExpenseTracker.java`: Main logic and user interface
- `expenses.txt`: Data file for saved expenses

## Comments & Documentation
- All classes and methods are commented for clarity.
- Read the code to understand the flow and logic.

## Next Steps
- Add more features (edit/delete expenses, monthly summary, etc.)
- Build a GUI or mobile version
