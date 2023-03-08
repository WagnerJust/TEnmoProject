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
    public List<Transaction> listTranscationsByUserId(int userId) {
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



    private Transaction mapRowToTransfer(SqlRowSet rowSet) {
    }


}
