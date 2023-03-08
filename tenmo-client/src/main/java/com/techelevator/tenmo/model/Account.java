package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {
     private BigDecimal balance;

     private List<Transaction> transactionHistory = new ArrayList<>();



     //getters setters


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public Account(List<Transaction> transactionHistory) {
        this.balance = new BigDecimal("1000.00");
        this.transactionHistory = transactionHistory;
    }

    @Override
    public String toString() {
        return "";
    }


}
