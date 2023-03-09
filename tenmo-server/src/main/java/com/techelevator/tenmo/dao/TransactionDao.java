package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionDao {
    List<Transaction> listAllTransactions();
    List<Transaction> listTranscationByUserId(int userId);
    Transaction getTransactionByTransactionId(int transactionId);
    void createTransfer(Transaction transaction);
    void updateTransferStatus(int transfeId, int transferStatusId);

}
