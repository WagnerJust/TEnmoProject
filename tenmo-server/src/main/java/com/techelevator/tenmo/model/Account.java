package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private BigDecimal balance;
    private int accountId;


    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Account(BigDecimal balance, int accountId) {
        this.balance = balance;
        this.accountId = accountId;
    }

    public Account(int accountId) {
        this.balance = new BigDecimal("1000.00");
        this.accountId = accountId;
    }

    public Account() {

    };
}
