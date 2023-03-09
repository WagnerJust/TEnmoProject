package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;

import java.math.BigDecimal;

public interface AccountDao {

    Account getAccountByUserId(int userId);
    BigDecimal getBalance(int userId);
    void updateBalance(BigDecimal balance, int userId);



}
