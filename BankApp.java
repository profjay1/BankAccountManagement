package com.jibstech.bank_account_management_system;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Entry point for the Bank Management System application.
 * Provides a console-based interface for user interaction.
 * @see Bank
 */
public class BankApp {

	/**
     * Main method to launch the application, display the menu, and process user input.
     *
     * @param args command-line arguments (not used)
     */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Bank bank = new Bank();
		
		
		while (true) {
			System.out.println("\n==***Welcome to the Bank Management System!***==\n Menu:");
			System.out.println("1. Create a new account");
			System.out.println("2. Deposit money");
			System.out.println("3. Withdraw money");
			System.out.println("4. Transfer money");
			System.out.println("5. View account details");
			System.out.println("6. View transaction history");
			System.out.println("7. Exit");
			System.out.println("Enter an operation");
			
			int choice = getValidatedChoice(scanner);
			
			switch (choice) {
				case 1 -> {
					try {
						System.out.print("Enter accountant number: ");
						String accountNum = getValidatedAccountNumber(scanner);
						
						System.out.print("Enter accountant owner's name: ");
						String accountName = getValidatedString(scanner);
						
						System.out.print("Enter accountant type: ");
						String accountType = getValidatedString(scanner);
						
						System.out.print("Enter initial balance: ");
						double initialBalance = getValidatedDouble(scanner);
						
						bank.createAccount(accountNum, accountName, accountType, initialBalance);
					} catch (NumberFormatException e) {
						System.out.println("An error occured while creating an account, please try again");
						}
					}
				
				case 2 -> {
					try {
						System.out.print("Enter the account to deposit into: ");
						String accountNum = getValidatedAccountNumber(scanner);
						
						System.out.print("Enter amount to deposit: ");
						double amount = getValidatedAmount(scanner);
						
						bank.depositToAccount(accountNum, amount);
					} catch (NumberFormatException e) {
						System.out.println("An error occurred while depositing into account, please try again: " + e.getMessage());
					}
				}
				
				case 3 -> {
					try {
						System.out.print("Enter account to witdraw from: ");
						String accountNum = getValidatedAccountNumber(scanner);
						
						System.out.print("Enter amount to withdraw: ");
						double amount = getValidatedAmount(scanner);
						
						bank.withdrawFromAccount(accountNum, amount);
					} catch (NumberFormatException e) {
						System.out.println("Error occurred while trying to withdraw, please try again: " + e.getMessage());
					}
				}
				
				case 4 -> {
					try {
						System.out.print("Enter the sender's account number: ");
						String accountNumSender = getValidatedAccountNumber(scanner);
						
						System.out.print("Enter the receiver's account number: ");
						String accountNumReceiver = getValidatedAccountNumber(scanner);
						
						System.out.println("Enter amount to transfer: ");
						double amount = getValidatedAmount(scanner);
						
						bank.transferBetweenAccounts(accountNumSender, accountNumReceiver, amount);
						
					} catch (NumberFormatException e) {
						System.out.println("Error occurred while transferring, please try again: " + e.getMessage());
					}
				}
				
				case 5 -> {
				
						System.out.println("Enter account number: ");
						String accountNum = getValidatedAccountNumber(scanner);
						
						bank.viewAccountDetails(accountNum);	
				}
				case 6 -> bank.viewTransactionHistory(); 
				
				case 7 -> {
					System.out.println("Exiting the bank management system...");
					System.exit(0);
					scanner.close();
					return;
				}
				
				default -> System.out.println("Invalid choice! Please try again.");
			}
		}
	}
		
	private static int getValidatedChoice(Scanner scanner) {
        int choice;
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume leftover newline
                if (choice < 1 || choice > 7) {
                    System.out.println("Invalid choice! Please select a valid option between 1 and 7.");
                } else {
                    return choice;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 7.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static int getValidatedInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
    
    private static double getValidatedDouble(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid double.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static String getValidatedString(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Input cannot be empty! Please enter a valid value.");
            }
        }
    }
    
    private static String getValidatedAccountNumber(Scanner scanner) {
    	while (true) {
    		String accountNum = scanner.nextLine().trim();
    		if (accountNum.matches("\\d{6,10}")) { // A 6 - 10 digits account number
    			return accountNum;
    		    				
    		} else {
    			System.out.println("Invalid account number! Enter a numeric account number (6 to 10 digits long)");
    		}
    	}
    }
    
    private static double getValidatedAmount (Scanner scanner) {
    	while(true) {
    		try {
    			double amount = scanner.nextDouble();
    			if (amount > 0) {
    				return amount;
    			}
    			System.out.println("Enter amount graeter than zero");
    		} catch (InputMismatchException e) {
    			System.out.println("Invalid input! Enter a valid numeric amount");
    		}
    		
    		scanner.nextLine(); // Clear invalid input
    	}
    }
}
