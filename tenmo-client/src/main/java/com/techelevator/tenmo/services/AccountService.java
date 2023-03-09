package com.techelevator.tenmo.services;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class AccountService {

    public static final String API_BASE_URL = "http://localhost:8080/user/account";
    private RestTemplate restTemplate = new RestTemplate();
    private String authToken = null;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public BigDecimal getUserBalance() {
        BigDecimal balance = null;
        try {
            ResponseEntity<Account> response = restTemplate.getForEntity(API_BASE_URL, Account.class, makeAuthEntity());
            balance = response.getBody().getBalance();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return balance;
    }

    public Transaction[] getAllTransactions() {
        Transaction[] userTransactions = null;
        try {
            ResponseEntity<Transaction[]> response = restTemplate.exchange(API_BASE_URL, HttpMethod.GET, makeAuthEntity(), Transaction[].class);
            userTransactions = response.getBody();
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }
        return userTransactions;
    }

    public Transaction getTransactionById(int transferId) {
        Transaction transaction = null;
        try {
            ResponseEntity<Transaction> response = restTemplate.exchange(API_BASE_URL + transferId, HttpMethod.GET, makeAuthEntity(), Transaction.class);
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
            returnedTransaction = restTemplate.postForObject(API_BASE_URL, entity, Transaction.class);
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
