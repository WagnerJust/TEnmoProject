package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    public static final String API_BASE_URL = "http://localhost:8080";
    private RestTemplate restTemplate = new RestTemplate();
    private String authToken = null;
    private AuthenticatedUser currentUser;

    public User[] getAllUsers() {
        User[] users = null;
        try {
            users = restTemplate.getForObject(API_BASE_URL + "/user", User[].class);
//            for (User user : users) {
//                System.out.println("UserService");
//            }
        } catch (RestClientResponseException | ResourceAccessException | ClassCastException e) {
            BasicLogger.log(e.getMessage());
            System.out.println("User Service");
        }
        return users;
    }

    public User[] getUserById(Integer id) {
        User[] user = null;
        try {
            //        ResponseEntity<User> response = restTemplate.exchange(API_BASE_URL + "/user/" + id, HttpMethod.GET, makeAuthEntity(), User.class);
            //     ResponseEntity<User> response = restTemplate.exchange(API_BASE_URL + "/user/" + id, HttpMethod.GET, makeAuthEntity(), User.class);
            Map<String, String> uriVariable = new HashMap<>();
            uriVariable.put("id", id.toString());
            user = restTemplate.getForObject(API_BASE_URL + "user/" + "?id=" + "{id}", User[].class, uriVariable);
         //   user = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return user;
    }

//    public void updateBalance(User updatedUser) {
//        try{
//            restTemplate.put(API_BASE_URL + "/user/" + updatedUser.getId() + "/balance", Account.class);
//        } catch (RestClientResponseException | ResourceAccessException e) {
//            BasicLogger.log(e.getMessage());
//        }
//    }

//    public User findByUsername(String userName) {
//        User user = null;
//        User[] allUsers = getAllUsers();
//        for(User userError: allUsers){
//            if (userError.getUsername().equals(userName)){
//                user = userError;
//            }
//        }
//        try {
//            ResponseEntity<User> response = restTemplate.exchange(API_BASE_URL + "/user/" + user.getId(), HttpMethod.GET, makeAuthEntity(), User.class);
//            user = response.getBody();
//        } catch (RestClientResponseException | ResourceAccessException e) {
//            BasicLogger.log(e.getMessage());
//        }
//        return user;
//    }
//
//    public int findIdByUsername(String userName) {
//        User user = null;
//        User[] allUsers = getAllUsers();
//        for(User userError: allUsers){
//            if (userError.getUsername().equals(userName)){
//                user = userError;
//            }
//        }
//        try {
//            ResponseEntity<User> response = restTemplate.exchange(API_BASE_URL + "/user/" + user.getId(), HttpMethod.GET, makeAuthEntity(), User.class);
//            user = response.getBody();
//        } catch (RestClientResponseException | ResourceAccessException e) {
//            BasicLogger.log(e.getMessage());
//        }
//        return user.getId();
//    }
//
//    public Account getAccountByUserId(int userId) {
//        Account account = null;
//        try {
//            ResponseEntity<Account> response = restTemplate.exchange(API_BASE_URL + "/account/" + userId, HttpMethod.GET, makeAuthEntity(), Account.class);
//            account = response.getBody();
//        } catch (RestClientResponseException | ResourceAccessException e) {
//            BasicLogger.log(e.getMessage());
//        }
//        return account;
//    }

    public Double getUserBalance(int userId) {
        Double balance = null;
        try {
           // ResponseEntity<User> response = restTemplate.exchange(API_BASE_URL + "/user/" + userId + "/balance" , HttpMethod.GET, makeAuthEntity(), User.class);
            ResponseEntity<Double> response = restTemplate.exchange(API_BASE_URL + "/user/" + userId + "/balance" , HttpMethod.GET, makeAuthEntity(), Double.class);

//            System.out.println(response.getBody().getAccount().toString());
            balance = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }

    public Double updateUserBalance(int userId, Double balance) {
        try {
            // ResponseEntity<User> response = restTemplate.exchange(API_BASE_URL + "/user/" + userId + "/balance" , HttpMethod.GET, makeAuthEntity(), User.class);
            restTemplate.put(API_BASE_URL + "/user/" + userId + "/balance", balance);

//            System.out.println(response.getBody().getAccount().toString());
//            System.out.println(balance.toString());
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }

    public Transaction[] getAllTransactions() {
        Transaction[] userTransactions = null;
        try {
            ResponseEntity<Transaction[]> response = restTemplate.exchange(API_BASE_URL + "/user", HttpMethod.GET, makeAuthEntity(), Transaction[].class);
            userTransactions = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return userTransactions;
    }

    public Transaction[] getTransactionByUserId(int userId) {
        Transaction[] userTransactions = null;
        try {
            ResponseEntity<Transaction[]> response = restTemplate.exchange(API_BASE_URL + "/user/" + userId, HttpMethod.GET, makeAuthEntity(), Transaction[].class);
            userTransactions = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return userTransactions;
    }

    public Transaction getTransactionByTransferId(int transferId) {
        Transaction transaction = null;
        try {
            ResponseEntity<Transaction> response = restTemplate.exchange(API_BASE_URL + "/user/" + currentUser.getUser().getId() + "transaction/" + transferId, HttpMethod.GET, makeAuthEntity(), Transaction.class);
            transaction = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return transaction;
    }

    public Transaction addTransaction(Transaction newTransaction) {
        HttpEntity<Transaction> entity = makeTransactionEntity(newTransaction);
        Transaction returnedTransaction = null;
        try {
            returnedTransaction = restTemplate.postForObject(API_BASE_URL + "/user/" + currentUser.getUser().getId() + "/transaction", entity, Transaction.class);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return returnedTransaction;
    }

    private HttpEntity<Transaction> makeTransactionEntity(Transaction transaction) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(transaction, headers);
    }

    private HttpEntity<Void> makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
}
