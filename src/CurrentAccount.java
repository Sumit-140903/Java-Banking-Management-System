import java.math.BigDecimal;

public class CurrentAccount extends Account {
    private static final BigDecimal OVERDRAFT_LIMIT = new BigDecimal("2000.00");

    public CurrentAccount(String number, String holder, BigDecimal openingBalance) {
        super(number, holder, openingBalance);
    }

    @Override
    protected boolean canWithdraw(BigDecimal amount) {
        return getBalance().subtract(amount).compareTo(OVERDRAFT_LIMIT.negate()) >= 0;
    }

    @Override
    public String getAccountType() {
        return "Current";
    }
}
