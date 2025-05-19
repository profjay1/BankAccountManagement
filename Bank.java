package com.jibstech.bank_account_management_system;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of Account objects and their associated transactions.
 * Provides methods to create accounts, perform deposits, withdrawals, transfers,
 * and persist data to/from files.
 * @see Account
 * @see Transaction
 * @see TransactionType
 */
public class Bank {

    private List<Account> accounts;
    private List<Transaction> transactions;
    private final String FILE_NAME = "accounts.dat";
    private final String TXN_FILE = "transactions.dat";

    /**
     * Initializes the Bank, loading existing accounts and transactions from file storage.
     */
    public Bank() {
        accounts = new ArrayList<>();
        transactions = new ArrayList<>();
        loadAccountsFromFile();
        loadTransactionsFromFile();
    }

    /**
     * Creates a new account with the specified details and persists it.
     *
     * @param accountNumber unique numeric identifier for the new account
     * @param accountName name of the account owner
     * @param accountType category/type of account (e.g., Savings, Checking)
     * @param initialBalance starting balance for the account
     */
    public void createAccount(String accountNumber, String accountName, String accountType, double initialBalance) {
        Account newAccount = new Account(accountNumber, accountName, accountType, initialBalance);
        accounts.add(newAccount);
        System.out.println("Account created successfully!");
        saveAccountsToFile();
    }

    /**
     * Searches for an account by its account number.
     *
     * @param accountNumber the identifier to search for
     * @return the matching Account, or null if not found
     */
    public Account findAccountByNumber(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    /**
     * Performs a deposit on the specified account and records the transaction.
     *
     * @param accountNumber target account identifier
     * @param amount amount to deposit; must be positive
     */
    public void depositToAccount(String accountNumber, double amount) {
        Account account = findAccountByNumber(accountNumber);
        if (account != null && account.deposit(amount)) {
            Transaction txn = new Transaction("TXN" + (transactions.size() + 1), accountNumber, amount, TransactionType.DEPOSIT);
            transactions.add(txn);
            System.out.println("Deposit successful! New account balance: #" + account.getBalance());
            saveTransactionssToFile();
            saveAccountsToFile();
        } else {
            System.out.println("Deposit failed! Invalid account or amount");
        }
    }

    /**
     * Performs a withdrawal on the specified account and records the transaction.
     *
     * @param accountNumber target account identifier
     * @param amount amount to withdraw; must be positive and <= account balance
     */
    public void withdrawFromAccount(String accountNumber, double amount) {
        Account account = findAccountByNumber(accountNumber);
        if (account != null && account.withdraw(amount)) {
            Transaction txn = new Transaction("TXN" + (transactions.size() + 1), accountNumber, amount, TransactionType.WITHDRAWAL);
            transactions.add(txn);
            System.out.println("Withdrawal successful! New account balance: #" + account.getBalance());
            saveTransactionssToFile();
            saveAccountsToFile();
        } else {
            System.out.println("Withdrawal failed! Insufficient funds or invalid account");
        }
    }

    /**
     * Transfers funds between two accounts and records the transaction.
     *
     * @param senderAccount account number of the sender
     * @param receiverAccount account number of the receiver
     * @param amount amount to transfer; must be positive and <= sender's balance
     */
    public void transferBetweenAccounts(String senderAccount, String receiverAccount, double amount) {
        Account sender = findAccountByNumber(senderAccount);
        Account receiver = findAccountByNumber(receiverAccount);
        if (sender != null && receiver != null && sender.transfer(receiver, amount)) {
            Transaction txn = new Transaction("TXN" + (transactions.size() + 1), senderAccount, amount, TransactionType.TRANSFER);
            transactions.add(txn);
            System.out.println("Transfer successful! Sender new account balance #" + sender.getBalance());
            saveTransactionssToFile();
            saveAccountsToFile();
        } else {
            System.out.println("Transfer failed! Insufficient balance or invalid accounts");
        }
    }

    /**
     * Displays details of the specified account.
     *
     * @param accountNumber identifier of the account to display
     */
    public void viewAccountDetails(String accountNumber) {
        Account account = findAccountByNumber(accountNumber);
        if (account != null) {
            System.out.println(account);
        } else {
            System.out.println("Account is not found");
        }
    }

    /**
     * Prints the history of all recorded transactions.
     */
    public void viewTransactionHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available");
        } else {
            for (Transaction history : transactions) {
                System.out.println(history);
            }
        }
    }

    /**
     * Persists the list of accounts to a file.
     */
    public void saveAccountsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(accounts);
            System.out.println("Accounts saved successfully");
        } catch (IOException e) {
            System.out.println("Error saving accounts " + e.getMessage());
        }
    }

    /**
     * Loads the list of accounts from file storage.
     */
    @SuppressWarnings("unchecked")
    public void loadAccountsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            accounts = (List<Account>) ois.readObject();
            System.out.println("Accounts loaded successfully");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts " + e.getMessage());
        }
    }

    /**
     * Persists the list of transactions to a file.
     */
    public void saveTransactionssToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TXN_FILE))) {
            oos.writeObject(transactions);
            System.out.println("Transactions saved successfully");
        } catch (IOException e) {
            System.out.println("Error saving transactions");
        }
    }

    /**
     * Loads the list of transactions from file storage.
     */
    @SuppressWarnings("unchecked")
    public void loadTransactionsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(TXN_FILE))) {
            transactions = (List<Transaction>) ois.readObject();
            System.out.println("Transactions loaded successfully");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No previous transactions found");
        }
    }
}
