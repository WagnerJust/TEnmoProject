package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionDao {
    List<Transaction> listAllTransactions();
    List<Transaction> listTranscationByUserId(Integer accountId);
    Transaction getTransactionByTransactionId(int transactionId);
    void createTransaction(Transaction transaction);
    void updateTransactionStatus(int transactionId, int transactionStatusId);

}
