package com.jibstech.bank_account_management_system;

import java.io.Serializable;

/**
 * Represents a bank account with basic operations such as deposit, withdrawal, and transfer.
 * Implements Serializable to allow persistence of account objects to file storage.
 * @see Bank
 * @see Transaction
 */
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accountNumber;
    private String ownerName;
    private String accountType;
    private double balance;

    /**
     * Constructs a new Account instance with the specified details.
     *
     * @param accountNumber unique identifier for the account
     * @param ownerName name of the account owner
     * @param accountType type/category of the account (e.g., Savings, Checking)
     * @param balance initial account balance
     */
    public Account(String accountNumber, String ownerName, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.accountType = accountType;
        this.balance = balance;
    }

    /**
     * Gets the account number.
     *
     * @return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets the name of the account owner.
     *
     * @return the ownerName
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Gets the account type.
     *
     * @return the accountType
     */
    public String getAcountType() {
        return accountType;
    }

    /**
     * Gets the current account balance.
     *
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposits the specified amount into the account if the amount is positive.
     *
     * @param amount the amount to deposit; must be greater than zero
     * @return true if deposition succeeded, false otherwise
     * @see #withdraw(double)
     */
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Withdraws the specified amount from the account if sufficient funds exist.
     *
     * @param amount the amount to withdraw; must be greater than zero and less than or equal to balance
     * @return true if withdrawal succeeded, false otherwise
     * @see #deposit(double)
     */
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Transfers the specified amount from this account to a target account.
     * Withdraws the amount from this account and deposits it into targetAccount.
     *
     * @param targetAccount the Account to receive the transferred funds
     * @param amount the amount to transfer; must be positive and less than or equal to this account's balance
     * @return true if transfer succeeded, false otherwise
     * @see #withdraw(double)
     * @see Account#deposit(double)
     */
    public boolean transfer(Account targetAccount, double amount) {
        if (withdraw(amount)) {
            return targetAccount.deposit(amount);
        }
        return false;
    }

    /**
     * Returns a new Account object with the same details as this account.
     * Useful for providing a snapshot of account details without exposing internal state.
     *
     * @return a new Account instance with identical field values
     */
    public Account getDetails() {
        return new Account(accountNumber, ownerName, accountType, balance);
    }

    /**
     * Provides a string representation of the account details.
     *
     * @return a formatted string containing accountNumber, ownerName, accountType, and balance
     */
    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Owner Name: " + ownerName + 
               ", Account Type: " + accountType + ", Balance: " + balance;
    }
}
