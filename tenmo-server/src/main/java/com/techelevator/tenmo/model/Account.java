package com.techelevator.tenmo.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private double balance;
    private int accountId;


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Account(double balance, int accountId) {
        this.balance = balance;
        this.accountId = accountId;
    }

    public Account(int accountId) {
        this.balance = 1000.00;
        this.accountId = accountId;
    }

    public Account() {

    };
}
