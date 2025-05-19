## Bank Account Management System

A simple Java-based console application that allows users to create and manage bank accounts, perform deposits, withdrawals, transfers, and view transaction history. Data is persisted to local files using Java serialization.

---

## Table of Contents

* [Features](#features)
* [Prerequisites](#prerequisites)
* [Installation](#installation)
* [Usage](#usage)
* [Project Structure](#project-structure)
* [Classes and Responsibilities](#classes-and-responsibilities)
* [Extending the Application](#extending-the-application)
* [Contributing](#contributing)
* [License](#license)

---

## Features

* Create new bank accounts with unique account numbers and initial balances
* Deposit and withdraw funds
* Transfer money between accounts
* View account details and balances
* View complete transaction history
* Data persistence through serialized files (`accounts.dat`, `transactions.dat`)

---

## Prerequisites

* Java Development Kit (JDK) 8 or higher
* Apache Maven (optional, if building with Maven)
* Git (for cloning the repository)

---

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/bank-account-management-system.git
   cd bank-account-management-system
   ```

2. (Optional) Build with Maven:

   ```bash
   mvn clean package
   ```

---

## Usage

### Compile and Run Manually

1. Compile the source files:

   ```bash
   javac -d out src/main/java/com/jibstech/bank_account_management_system/*.java
   ```
2. Run the application:

   ```bash
   java -cp out com.jibstech.bank_account_management_system.BankApp
   ```

### Using Maven

```bash
mvn exec:java -Dexec.mainClass="com.jibstech.bank_account_management_system.BankApp"
```

Follow the on-screen menu to perform operations.

---

## Project Structure

```
bank-account-management-system/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── jibstech/
│                   └── bank_account_management_system/
│                       ├── Account.java
│                       ├── Bank.java
│                       ├── BankApp.java
│                       ├── Transaction.java
│                       └── TransactionType.java
├── .gitignore
└── README.md
```

---

## Classes and Responsibilities

* **Account**: Represents a bank account with operations for deposit, withdraw, transfer, and snapshot retrieval.
* **Bank**: Manages a collection of `Account` objects and their `Transaction` records, providing methods for account creation, deposits, withdrawals, transfers, and persistence.
* **Transaction**: Encapsulates details of a financial transaction (ID, account number, amount, type, timestamp).
* **TransactionType**: Enum defining transaction categories: DEPOSIT, WITHDRAWAL, TRANSFER.
* **BankApp**: Entry point with a console-based menu for user interaction and input validation.

---

## Extending the Application

* Integrate a database (e.g., MySQL, PostgreSQL) instead of file-based serialization
* Add a graphical user interface (GUI) using JavaFX or Swing
* Implement user authentication and encryption for sensitive data
* Add unit and integration tests using JUnit and Mockito

---

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes.

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Open a pull request

---

## License
MIT © Saheed Omotola
