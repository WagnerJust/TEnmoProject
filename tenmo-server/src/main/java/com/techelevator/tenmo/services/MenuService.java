package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transaction;

import java.math.BigDecimal;
import java.util.Scanner;

public class MenuService {

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection() {
        int menuSelection;
        System.out.print("Please choose one of the following options: ");
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("----Tenmo Main Menu----");
        System.out.println("1: Get balance");
        System.out.println("2: List all transfers");
        System.out.println("3: Find transfer by transfer id");
        System.out.println("4: Send request to send money");
        System.out.println("5: Send request to receive money");
        System.out.println("0: Exit");
    }

    public BigDecimal promptForTransferAmount() {
        BigDecimal transferAmount;
        System.out.println("");
        System.out.println("Enter the amount you would like to send/receive:");
        try {
            transferAmount = BigDecimal.valueOf(scanner.nextDouble());
        } catch (NumberFormatException e) {
            transferAmount = BigDecimal.valueOf(0);
        }
        return transferAmount;
    }

    public String promptForTransferType() {

        String transferType = "";
        System.out.println("Would you like to (s)end or (r)eceive money?")
            transferType = scanner.nextLine().toLowerCase();
            if (transferType.equals("s") || transferType.equals("send") || transferType.equals("r") || transferType.equals("receive")) {
                return transferType;
            } else {
                return "Invalid option";
            }
    }

    private Transaction makeTransaction() {
        Transaction transaction = null;
        return transaction;
    }
}
