package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.UserService;
import com.techelevator.tenmo.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final UserService userService = new UserService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);


    private AuthenticatedUser currentUser;
    private Scanner userInput = new Scanner(System.in);



    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
//            } else if (menuSelection == 3) {
//                //viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
//            } else if (menuSelection == 5) {
//                requestBucks();
            } else if (menuSelection == 6) {
                transferDetails();
            }
            else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
        System.out.println();
//        System.out.println("Current Balance: " + userService.getUserBalance());
        System.out.println("Current Balance: " + userService.getUserBalance(currentUser.getUser().getId()));


        // TODO Auto-generated method stub
		
	}
	private void viewTransferHistory() {
        Integer accountId = currentUser.getUser().getId() + 1000;
        System.out.println();
        System.out.println("Transaction History:\n");
        for(Transaction transaction : userService.getTransactionByUserId(accountId)){
            System.out.println(transaction.toString());
        }
		// TODO Auto-generated method stub
		
	}

//	private void viewPendingRequests() {
//		// TODO Auto-generated method stub
//
//	}

	private void sendBucks() {
        int fromId = currentUser.getUser().getId();
        System.out.println();
        for(User user : userService.getAllUsers()) {
            if (currentUser.getUser().getId() == user.getId()) {
                continue;
            }
            System.out.println(user.getId() + ": " + user.getUsername());
            System.out.println();
        }

        int toId;
        do {
            System.out.println("Enter ID of user you are sending to (0 to cancel): ");
            while(!userInput.hasNextInt()){
                System.out.println("Please try again. The ID has to be the exact number and not your account's ID:");
                userInput.next();
            }
            toId = userInput.nextInt();
        } while(toId <= 0 || currentUser.getUser().getId() == toId);
//                System.out.println("Please try again. The ID has to the exact number and not your account's ID:");
//                userInput.next();

        Double amount = null;
        viewCurrentBalance();

        do {
            System.out.println("How much would you like to send: ");
            while(!userInput.hasNextDouble()){
                System.out.println("Please type in the correct amount:");
                userInput.next();
            }
            amount = userInput.nextDouble();
        }while((((amount > userService.getUserBalance(fromId)) || (amount <= 0))));


        User toUser = new User();
        toUser.setId(toId);
        for(User user : userService.getUserById(toId)) {
            if(toId == user.getId()) {
                toUser.setBalance(userService.getUserBalance(toId));
                toUser.setUsername(user.getUsername());
            }
        }

        User fromUser = new User(fromId);
        fromUser.setBalance(userService.getUserBalance(fromId));
  //      fromUser.setUsername(userService.getUsername());

        for(User user : userService.getUserById(fromId)) {
            if(fromId == user.getId()) {
                fromUser.setId(currentUser.getUser().getId());
                fromUser.setBalance(userService.getUserBalance(fromId));
                fromUser.setUsername(user.getUsername());
            }
        }
        Integer accountFrom = fromId + 1000;
        Integer accountTo = toId + 1000;
        Integer statusId = 2;
        Integer typeId = 2;
  //      userService.updateUserBalance(toId, userService.getUserBalance(toId) + amount);
  //      userService.updateUserBalance(fromUser.getId(), userService.getUserBalance(fromId) - amount);
        Transaction transaction = new Transaction();
        transaction.setActingId(accountFrom);
        transaction.setTargetId(accountTo);
        transaction.setAmount(amount);
        transaction.setStatusId(statusId);
        transaction.setTypeId(typeId);
        userService.updateUserBalance(toId, toUser.getBalance() + amount);
        userService.updateUserBalance(fromUser.getId(), fromUser.getBalance() - amount);
        userService.createTransaction(transaction);

//        userService.updateUserBalance(toId, 500.00);
//        userService.updateUserBalance(fromUser.getId(), 300.00);

//
//
//        userService.updateUserBalance(toId, toUser.getAccount().getBalance() + amount);
//        userService.updateUserBalance(fromUser.getId(), fromUser.getAccount().getBalance() - amount);
//        toUser.getAccount().setBalance(toUser.getAccount().getBalance());


		// TODO Auto-generated method stub
		
	}

    private void transferDetails() {
        Integer transferId;
        ArrayList<Integer> transferIdList = new ArrayList<>();
        Integer accountId = currentUser.getUser().getId() + 1000;

        for(Transaction transaction : userService.getTransactionByUserId(accountId)){
             transferIdList.add(transaction.getTransactionId());
        }

        do {
            System.out.println("Please enter the transfer ID of the transfer you want details for.");
            while(!userInput.hasNextInt()){
                System.out.println("Please type in an integer");
                userInput.next();
            }
            transferId = userInput.nextInt();
        }while((( !transferIdList.contains(transferId)|| (transferId <= 0))));
        System.out.println();
        System.out.println("Transaction Details:\n");
            System.out.println(userService.getTransactionByTransferId(transferId).toString());

    }

//	private void requestBucks() {
//		// TODO Auto-generated method stub
//
//	}

}
