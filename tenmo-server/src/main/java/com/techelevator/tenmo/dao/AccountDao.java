package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface AccountDao {

    Account getAccountByUserId(int userId);
    double getBalance(int userId) throws SQLException;
    void updateBalance(Double balance, int userId);



}
