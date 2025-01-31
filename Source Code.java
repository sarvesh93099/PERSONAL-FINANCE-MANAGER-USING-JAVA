import java.util.ArrayList;
import java.util.Scanner;

    class Transaction {
    private String description;
    private String category;
    private String type; // "income" or "expense"
    private double amount;
       public Transaction(String description, String category, String type, double amount) {
        this.description = description;
        this.category = category;
        this.type = type;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return type + ": " + description + " (" + category + ") - " + amount;
    }
}




class User {
    private String username;
    private String password;
    private ArrayList<Transaction> transactions;
    private double monthlyBudget;
   
    public User(String username, String password, double budget) {
        this.username = username;
        this.password = password;
        this.transactions = new ArrayList<>();
        this.monthlyBudget = budget;
    }

    public String getUsername() {
        return username;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public double getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }
    public double getTotalExpenses() {
        double total = 0;
        for (Transaction t : transactions) {
            if (t.getType().equals("expense")) {
                total += t.getAmount();
            }
        }
        return total;
    }
}

public class PersonalFinanceManager {
    private static User currentUser;
public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       
        // Creating a demo user for testing
        currentUser = new User("user1", "password123", 1000.0);
       
        System.out.println("Welcome to Personal Finance Manager");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
       
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
       
        if (currentUser.getUsername().equals(username) && currentUser.validatePassword(password)) {
            System.out.println("Login successful!");
            showMenu(scanner);
        } else {
            System.out.println("Invalid credentials. Exiting...");
        }
       
        scanner.close(); // Ensure the scanner is closed
    }
   
    public static void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transactions");
            System.out.println("3. View Budget");
            System.out.println("4. Set Budget");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
           
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
           
            switch (choice) {
                case 1:
                   addTransaction(scanner);
                    break;

                case 2:
                    viewTransactions();
                    break;
                case 3:
                    viewBudget();
                    break;
                case 4:
                    setBudget(scanner);
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
   
    public static void addTransaction(Scanner scanner) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
       
        System.out.print("Enter category (e.g., Food, Rent, Travel): ");
        String category = scanner.nextLine();
       
        System.out.print("Enter type (income/expense): ");
        String type = scanner.nextLine();
       
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline
       
        Transaction transaction = new Transaction(description, category, type, amount);
        currentUser.addTransaction(transaction);
       
        System.out.println("Transaction added!");
    }
   
    public static void viewTransactions() {
        System.out.println("\nTransactions:");
        for (Transaction t : currentUser.getTransactions()) {
            System.out.println(t);
        }
    }
   
    public static void viewBudget() {
        double totalExpenses = currentUser.getTotalExpenses();
        double budget = currentUser.getMonthlyBudget();
        System.out.println("\nMonthly Budget: " + budget);
        System.out.println("Total Expenses: " + totalExpenses);
       
        if (totalExpenses > budget) {
            System.out.println("Warning: You have exceeded your budget!");
        }
    }
   
    public static void setBudget(Scanner scanner) {
        System.out.print("Enter new monthly budget: ");
        double budget = scanner.nextDouble();
        scanner.nextLine(); // consume newline
       
        currentUser.setMonthlyBudget(budget);
        System.out.println("Budget updated!");
    }
}

