package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcTransactionDao implements TransactionDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Transaction> listTranscationByUserId(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transfer WHERE transfer_status_id = 2 AND (account_from = ? OR account_to = ?);";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId,userId);
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
    public void createTransaction(Transaction transaction) {
        String sql = "INSERT INTO transfer(transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, transaction.gettransfer_type_id(), transaction.gettransfer_status_id(), transaction.getaccount_from(),
                transaction.getaccount_to(), transaction.getAmount());
    }
    @Override
    public void updateTransactionStatus(int transactionId, int transactiontransfer_status_id) {
        String updateStatus = "UPDATE transfers "
                + "SET transfer_status_id = ? "
                + "WHERE transfer_id = ? ";
        jdbcTemplate.update(updateStatus, transactionId, transactiontransfer_status_id);

    }


    private Transaction mapRowToTransfer(SqlRowSet rowSet) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(rowSet.getInt("transfer_id"));
        transaction.settransfer_type_id(rowSet.getInt("transfer_type_id"));
        transaction.settransfer_status_id(rowSet.getInt("transfer_status_id"));
        transaction.setaccount_from(rowSet.getInt("account_from"));
        transaction.setaccount_to(rowSet.getInt("account_to"));
        transaction.setAmount(rowSet.getDouble("amount"));

        return transaction;
    }


}
