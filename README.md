# Java Banking Management System

A console-based educational project demonstrating object-oriented programming in Java. It is a learning simulation only, not a real banking or payment system.

## Features
- Open savings and current accounts
- Deposit and withdraw money
- Check account balance
- View transaction history
- Validate amounts and account numbers
- Demonstrate different withdrawal rules through inheritance and overriding

## OOP concepts demonstrated
- **Classes and objects:** `Bank`, `Account`, `SavingsAccount`, `CurrentAccount`, `Transaction`
- **Encapsulation:** private fields with controlled public methods
- **Constructors:** initialize account and transaction objects
- **Inheritance:** savings/current accounts extend the abstract `Account` class
- **Abstraction:** `Account` defines common operations and abstract `getAccountType()`
- **Polymorphism:** `Bank` stores different account subtypes as `Account`; overridden methods supply account-specific behavior
- **Method overriding:** `canWithdraw()` and `getAccountType()`
- **Access modifiers:** private, protected and public

## Requirements
- JDK 17 or newer

## Compile and run
From the project root:

```bash
javac -d out src/*.java
java -cp out Main
```

## Demo accounts
- Savings: `S1001` (Aarav Sharma), opening balance 5000.00
- Current: `C2001` (Diya Sen), opening balance 2500.00

## Account rules
- Savings account: opening balance must be at least 500.00 and withdrawals must preserve that minimum balance.
- Current account: allows an overdraft up to 2000.00 below zero.
- Deposits and withdrawals must be positive amounts.

## Design documentation
See `docs/DESIGN.md` and `docs/class-diagram.mmd`.

## Limitations
This is an in-memory, single-user console demonstration. Accounts and transaction history are not persisted after the application exits. It does not connect to a bank, process payments, or provide production-grade security.
