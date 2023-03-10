package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.UserService;

import java.math.BigDecimal;
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
            } else if (menuSelection == 0) {
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
        System.out.println();
        System.out.println("Transaction History:\n");
        for(Transaction transaction : userService.getAllTransactions()){
            System.out.println(transaction.toString());
        }
		// TODO Auto-generated method stub
		
	}

//	private void viewPendingRequests() {
//		// TODO Auto-generated method stub
//
//	}

	private void sendBucks() {
        System.out.println();
        for(User user : userService.getAllUsers()){
            System.out.println(user.getId() + ": " + user.getUsername());
            System.out.println();
        }
        System.out.println("Enter ID of user you are sending to (0 to cancel): "); //TODO: handle 0 to cancel
        int toId = userInput.nextInt();
        viewCurrentBalance();
        System.out.println("How much would you like to send: ");
        BigDecimal amount = userInput.nextBigDecimal();

        User toUser = userService.getUserById(toId);
        User fromUser = currentUser.getUser();


//        userService.updateBalance(toUser.getId());
//        userService.updateBalance(fromUser.getId());
//        toUser.getAccount().setBalance(toUser.getAccount().getBalance());


		// TODO Auto-generated method stub
		
	}

//	private void requestBucks() {
//		// TODO Auto-generated method stub
//
//	}

}
