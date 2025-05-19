package com.jibstech.bank_account_management_system;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a financial transaction associated with an Account.
 * Includes details such as transaction ID, account number, amount, type, and timestamp.
 * Implements Serializable for persistence.
 * @see Account
 * @see TransactionType
 */
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    private String transactionId;
    private String accountNumber;
    private double amount;
    private TransactionType type;
    private LocalDateTime timestamp;

    /**
     * Constructs a new Transaction with the given details and timestamp set to now.
     *
     * @param transactionId unique identifier for the transaction
     * @param accountNumber associated account's number
     * @param amount monetary amount of the transaction
     * @param type type of transaction (DEPOSIT, WITHDRAWAL, TRANSFER)
     */
    public Transaction(String transactionId, String accountNumber, double amount, TransactionType type) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Gets the transaction ID.
     *
     * @return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Gets the associated account number.
     *
     * @return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets the transaction amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the transaction type.
     *
     * @return the type
     */
    public TransactionType getType() {
        return type;
    }

    /**
     * Gets the timestamp when the transaction was created.
     *
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Prints the transaction details to the console.
     */
    public void printTransaction() {
        System.out.println("TransactionId: " + transactionId + " | Account: " + accountNumber +
                           " | Amount: " + amount + " | Type: " + type + " | Timestamp: " + timestamp);
    }

    /**
     * Provides a string representation of the transaction.
     *
     * @return formatted transaction details
     */
    @Override
    public String toString() {
        return "TransactionId: " + transactionId + ", AccountNumber: " + accountNumber +
               ", Amount: " + amount + ", Type: " + type + ", Timestamp: " + timestamp;
    }
}

/**
 * Enumeration of possible transaction types for financial operations.
 * @see Transaction
 */
enum TransactionType {
    /** Money added to an account */
    DEPOSIT,
    /** Money withdrawn from an account */
    WITHDRAWAL,
    /** Money moved from one account to another */
    TRANSFER
}