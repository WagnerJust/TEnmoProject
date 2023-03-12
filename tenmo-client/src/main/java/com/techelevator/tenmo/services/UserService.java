package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    public static final String API_BASE_URL = "http://localhost:8080";
    private final RestTemplate restTemplate = new RestTemplate();
    private String authToken = null;
    private AuthenticatedUser currentUser;

    public User[] getAllUsers() {
        User[] users = null;
        try {
            users = restTemplate.getForObject(API_BASE_URL + "/user", User[].class);
        } catch (RestClientResponseException | ResourceAccessException | ClassCastException e) {
            BasicLogger.log(e.getMessage());
            System.out.println("User Service");
        }
        return users;
    }

    public User[] getUserById(Integer id) {
        User[] user = null;
        try {
            Map<String, String> uriVariable = new HashMap<>();
            uriVariable.put("id", id.toString());
            user = restTemplate.getForObject(API_BASE_URL + "user/" + "?id=" + "{id}", User[].class, uriVariable);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return user;
    }

    public Double getUserBalance(int userId) {
        Double balance = null;
        try {
            ResponseEntity<Double> response = restTemplate.exchange(API_BASE_URL + "/user/" + userId + "/balance", HttpMethod.GET, makeAuthEntity(), Double.class);
            balance = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }

    public Double updateUserBalance(int userId, Double balance) {
        try {
            restTemplate.put(API_BASE_URL + "/user/" + userId + "/balance", balance);
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }

//    public Transaction[] getAllTransactions() {
//        Transaction[] userTransactions = null;
//        try {
//            ResponseEntity<Transaction[]> response = restTemplate.exchange(API_BASE_URL + "/user", HttpMethod.GET, makeAuthEntity(), Transaction[].class);
//            userTransactions = response.getBody();
//        } catch (RestClientResponseException | ResourceAccessException e) {
//            BasicLogger.log(e.getMessage());
//        }
//        return userTransactions;
//    }

    public Transaction[] getTransactionByUserId(Integer accountId) {
        Transaction[] userTransactions = null;
        try {
            ResponseEntity<Transaction[]> response = restTemplate.getForEntity(API_BASE_URL + "/user/" + accountId + "/transaction", Transaction[].class);
            userTransactions = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return userTransactions;

    }


    //    Map<String, String> uriVariable = new HashMap<>();
//            uriVariable.put("id", id.toString());
//    user = restTemplate.getForObject(API_BASE_URL + "user/" + "?id=" + "{id}", User[].class, uriVariable);
//} catch (RestClientResponseException | ResourceAccessException e) {
//        BasicLogger.log(e.getMessage());
//        }
//        return user;
    public Transaction getTransactionByTransferId(int transferId) {
        Transaction transaction = null;
        try {
            ResponseEntity<Transaction> response = restTemplate.getForEntity(API_BASE_URL + "/user/" + currentUser.getUser().getId() + "transaction/" + transferId, Transaction.class);
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

