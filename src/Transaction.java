import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final String type;
    private final BigDecimal amount;
    private final BigDecimal balanceAfter;
    private final String description;
    private final LocalDateTime timestamp;

    public Transaction(String type, BigDecimal amount, BigDecimal balanceAfter, String description) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                + " | " + type + " | Amount: " + amount
                + " | Balance after: " + balanceAfter + " | " + description;
    }
}
