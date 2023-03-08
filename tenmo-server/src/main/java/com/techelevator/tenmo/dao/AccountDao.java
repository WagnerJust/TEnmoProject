package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;

import java.math.BigDecimal;

public interface AccountDao {

    Account getAccountByUserId(int userId);
    Account getAccountByAccountId(int accountId);
    BigDecimal getBalance(int userId);


}
