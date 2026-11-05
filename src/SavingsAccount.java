import java.math.BigDecimal;

public class SavingsAccount extends Account {
    private static final BigDecimal MINIMUM_BALANCE = new BigDecimal("500.00");

    public SavingsAccount(String number, String holder, BigDecimal openingBalance) {
        super(number, holder, openingBalance);
        if (openingBalance.compareTo(MINIMUM_BALANCE) < 0) {
            throw new IllegalArgumentException("Savings account requires a minimum opening balance of 500.00.");
        }
    }

    @Override
    protected boolean canWithdraw(BigDecimal amount) {
        return getBalance().subtract(amount).compareTo(MINIMUM_BALANCE) >= 0;
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}
