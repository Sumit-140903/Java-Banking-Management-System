import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final Bank BANK = new Bank();

    public static void main(String[] args) {
        seedDemoAccounts();
        System.out.println("=== Java Banking Management System (Educational Demo) ===");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = INPUT.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> openSavingsAccount();
                    case "2" -> openCurrentAccount();
                    case "3" -> showAccounts();
                    case "4" -> deposit();
                    case "5" -> withdraw();
                    case "6" -> checkBalance();
                    case "7" -> showHistory();
                    case "8" -> running = false;
                    default -> System.out.println("Choose a valid option (1-8).");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Operation failed: " + ex.getMessage());
            }
        }
        INPUT.close();
        System.out.println("Thank you for using the demo banking system.");
    }

    private static void seedDemoAccounts() {
        BANK.openAccount(new SavingsAccount("S1001", "Aarav Sharma", new BigDecimal("5000.00")));
        BANK.openAccount(new CurrentAccount("C2001", "Diya Sen", new BigDecimal("2500.00")));
    }

    private static void printMenu() {
        System.out.println("""

                1. Open savings account
                2. Open current account
                3. View all accounts
                4. Deposit
                5. Withdraw
                6. Check balance
                7. View transaction history
                8. Exit
                Enter choice:""");
    }

    private static void openSavingsAccount() {
        Account account = createAccount(true);
        BANK.openAccount(account);
        System.out.println("Savings account created: " + account);
    }

    private static void openCurrentAccount() {
        Account account = createAccount(false);
        BANK.openAccount(account);
        System.out.println("Current account created: " + account);
    }

    private static Account createAccount(boolean savings) {
        System.out.print("Account number: ");
        String number = INPUT.nextLine().trim();
        System.out.print("Account holder: ");
        String holder = INPUT.nextLine().trim();
        BigDecimal opening = readAmount("Opening balance: ");
        return savings
                ? new SavingsAccount(number, holder, opening)
                : new CurrentAccount(number, holder, opening);
    }

    private static void showAccounts() {
        if (BANK.getAllAccounts().isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }
        BANK.getAllAccounts().forEach(System.out::println);
    }

    private static void deposit() {
        System.out.print("Account number: ");
        String number = INPUT.nextLine().trim();
        BANK.deposit(number, readAmount("Deposit amount: "));
        System.out.println("Deposit successful.");
    }

    private static void withdraw() {
        System.out.print("Account number: ");
        String number = INPUT.nextLine().trim();
        BANK.withdraw(number, readAmount("Withdrawal amount: "));
        System.out.println("Withdrawal successful.");
    }

    private static void checkBalance() {
        System.out.print("Account number: ");
        Account account = BANK.findAccount(INPUT.nextLine().trim());
        System.out.println("Available balance: " + account.getBalance());
    }

    private static void showHistory() {
        System.out.print("Account number: ");
        Account account = BANK.findAccount(INPUT.nextLine().trim());
        System.out.println("Transaction history for " + account.getAccountNumber());
        if (account.getTransactionHistory().isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            account.getTransactionHistory().forEach(System.out::println);
        }
    }

    private static BigDecimal readAmount(String prompt) {
        System.out.print(prompt);
        try {
            return new BigDecimal(INPUT.nextLine().trim());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Enter a valid numeric amount.");
        }
    }
}
