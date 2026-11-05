# Design Documentation – Banking Management System

## 1. Purpose
Build a small console application to demonstrate Java OOP concepts through account creation, deposits, withdrawals, balance checks, and transaction history.

## 2. Scope
Included: two account types, account lookup, transaction recording during the current run, input validation, and a text-based menu.

Excluded: real payments, authentication, database persistence, interest calculation, regulatory controls, and multi-user access.

## 3. Class responsibilities
- `Account` (abstract): common account identity, balance, deposit/withdraw logic, transaction history, and withdrawal policy hook.
- `SavingsAccount`: enforces a minimum opening balance and minimum retained balance.
- `CurrentAccount`: supports a configured overdraft limit.
- `Transaction`: immutable record of transaction type, amount, resulting balance, description, and timestamp.
- `Bank`: manages accounts and exposes account-level operations.
- `Main`: handles console input/output and application flow.

## 4. OOP design decisions
- Encapsulation: account fields are private; balance changes only through deposit/withdraw methods.
- Abstraction: `Account` is abstract and requires each subtype to identify its account type.
- Inheritance: savings and current accounts reuse common account behavior.
- Polymorphism: `Bank` stores child objects using the parent `Account` type.
- Overriding: each subtype implements `getAccountType()` and its own `canWithdraw()` rule.
- Access modifiers: public API for operations, protected extension points, private internal state.

## 5. Validation and error handling
- Blank account identifiers and names are rejected.
- Opening balance cannot be negative.
- Deposit and withdrawal amounts must be greater than zero.
- Duplicate account numbers and unknown account numbers are rejected.
- Withdrawal is rejected if the account-specific policy would be violated.
- Invalid numeric input is converted into a clear user-facing error.

## 6. Data structures
`Bank` uses a `LinkedHashMap<String, Account>` so account numbers are unique keys and insertion order is retained. Each account maintains an `ArrayList`-backed transaction history and exposes it as an unmodifiable copy.

## 7. Example test scenarios
1. Open a savings account with 500.00: accepted.
2. Open a savings account with 499.99: rejected.
3. Deposit 250.00 into S1001: balance increases by 250.00.
4. Withdraw 100.00 from S1001: balance decreases by 100.00.
5. Withdraw enough to take savings below 500.00: rejected.
6. Withdraw from current account within the 2000.00 overdraft limit: accepted.
7. Withdraw beyond current-account overdraft limit: rejected.
8. Deposit zero or a negative amount: rejected.
9. Search for an unknown account: rejected with a clear message.
10. Open an account using an existing account number: rejected.

## 8. Build and run
```bash
javac -d out src/*.java
java -cp out Main
```

## 9. Future improvements
Add unit tests, persistent storage, secure authentication, audit logging, a graphical interface, and database-backed account management.
