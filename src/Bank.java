import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bank {
    private final Map<String, Account> accounts = new LinkedHashMap<>();

    public void openAccount(Account account) {
        if (accounts.containsKey(account.getAccountNumber())) {
            throw new IllegalArgumentException("Account number already exists.");
        }
        accounts.put(account.getAccountNumber(), account);
    }

    public Account findAccount(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }
        return account;
    }

    public void deposit(String accountNumber, BigDecimal amount) {
        findAccount(accountNumber).deposit(amount);
    }

    public void withdraw(String accountNumber, BigDecimal amount) {
        findAccount(accountNumber).withdraw(amount);
    }

    public Collection<Account> getAllAccounts() {
        return Collections.unmodifiableCollection(accounts.values());
    }
}
