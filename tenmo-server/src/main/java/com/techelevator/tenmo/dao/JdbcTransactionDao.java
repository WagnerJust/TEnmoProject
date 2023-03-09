package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransactionDao implements TransactionDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Transaction> listTranscationByUserId(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transfer WHERE transfer_status_id = 2 AND (account_from = ? OR account_to = ?);";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        while (result.next()){
            Transaction transaction = mapRowToTransfer(result);
            transactions.add(transaction);
        }
        return transactions;
    }

    @Override
    public List<Transaction> listAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transfer;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while(result.next()) {
            transactions.add(mapRowToTransfer(result));
        }
        return transactions;
    }

    @Override
    public Transaction getTransactionByTransactionId(int transactionId) {
        Transaction transaction = new Transaction();
        String sql = "SELECT * FROM transfer WHERE transfer_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transactionId);
        if (results.next()) {
            transaction = mapRowToTransfer(results);
        } else {
            System.out.println("Invalid transfer_id");
        }
        return transaction;
    }
    @Override
    public void createTransfer(Transaction transaction) {
        String sql = "INSERT INTO transfer(transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, transaction.getTransferTypeId(), transaction.getTransferStatusId(), transaction.getAccountFrom(),
                transaction.getAccountTo(), transaction.getAmount());
    }
    @Override
    public void updateTransferStatus(int transferId, int transferStatusId) {
        String updateStatus = "UPDATE transfers "
                + "SET transfer_status_id = ? "
                + "WHERE transfer_id = ? ";

        jdbcTemplate.update(updateStatus, transferStatusId, transferId);

    }


    private Transaction mapRowToTransfer(SqlRowSet rowSet) {
        Transaction transaction = new Transaction();
        transaction.setTransferId(rowSet.getInt("transfer_id"));
        transaction.setTransferTypeId(rowSet.getInt("transfer_type_id"));
        transaction.setTransferStatusId(rowSet.getInt("transfer_status_id"));
        transaction.setAccountFrom(rowSet.getInt("account_from"));
        transaction.setAccountTo(rowSet.getInt("account_to"));
        transaction.setAmount(rowSet.getBigDecimal("amount"));

        return transaction;
    }


}
