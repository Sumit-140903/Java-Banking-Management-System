import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account {
    private final String accountNumber;
    private final String accountHolder;
    private BigDecimal balance;
    private final List<Transaction> transactions = new ArrayList<>();

    protected Account(String accountNumber, String accountHolder, BigDecimal openingBalance) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number cannot be empty.");
        }
        if (accountHolder == null || accountHolder.isBlank()) {
            throw new IllegalArgumentException("Account holder cannot be empty.");
        }
        if (openingBalance == null || openingBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        }
        this.accountNumber = accountNumber.trim();
        this.accountHolder = accountHolder.trim();
        this.balance = money(openingBalance);
        if (this.balance.compareTo(BigDecimal.ZERO) > 0) {
            transactions.add(new Transaction("OPENING", this.balance, this.balance,
                    "Opening balance"));
        }
    }

    public final String getAccountNumber() { return accountNumber; }
    public final String getAccountHolder() { return accountHolder; }
    public final BigDecimal getBalance() { return balance; }

    public final void deposit(BigDecimal amount) {
        validatePositiveAmount(amount);
        BigDecimal value = money(amount);
        balance = balance.add(value);
        transactions.add(new Transaction("DEPOSIT", value, balance, "Cash deposit"));
    }

    public final void withdraw(BigDecimal amount) {
        validatePositiveAmount(amount);
        BigDecimal value = money(amount);
        if (!canWithdraw(value)) {
            throw new IllegalArgumentException("Withdrawal denied: insufficient funds or account limit.");
        }
        balance = balance.subtract(value);
        transactions.add(new Transaction("WITHDRAWAL", value, balance, "Cash withdrawal"));
    }

    protected boolean canWithdraw(BigDecimal amount) {
        return balance.compareTo(amount) >= 0;
    }

    protected static void validatePositiveAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
    }

    protected static BigDecimal money(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public final List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(new ArrayList<>(transactions));
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | Balance: %s",
                getAccountType(), accountNumber, accountHolder, balance);
    }

    public abstract String getAccountType();
}
