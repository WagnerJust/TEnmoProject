package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionDao {
    List<Transaction> listAllTransactions();
    List<Transaction> listTranscationsByUserId(int userId);

    void updateTransaction(Transaction transaction);


}
