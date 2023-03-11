package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcAccountDao implements AccountDao {
    private JdbcTemplate jdbcTemplate;


    public JdbcAccountDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
//    public double getBalance(int userId) throws SQLException {
//        double balance = null;
//        String sql = "SELECT balance FROM account WHERE user_id = ?";
//      SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
////        balance = jdbcTemplate.queryForObject(sql, double.class, userId);
////        if (balance.equals(null)) {
////            return double.valueOf(Long.parseLong("1000"));
////        }
//        while (result.next()) {
//            balance = result.getdouble("balance");
//        }
//        balance = getdoubleSafe(result, "balance");
//        return balance;
//    }

    public double getBalance(int userId) throws SQLException {
        double balance = 0;
        String sql = "SELECT balance FROM account WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
//        balance = jdbcTemplate.queryForObject(sql, double.class, userId);
////        if (balance.equals(null)) {
////            return double.valueOf(Long.parseLong("1000"));
//        }
        while (result.next()) {
            balance = result.getDouble("balance");
        }
//        balance = getdoubleSafe(result, "balance");
        return balance;
    }


    @Override
    public Account getAccountByUserId(int userId) {
        Account account = null;

        String sql = "SELECT * FROM account WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        while (result.next()) {
            account = mapRowToAccount(result);
        }
        return account;
    }

    @Override
    public void updateBalance(Double balance, int userId){
        String sql = "UPDATE account SET balance = ? WHERE user_id = ?;";
        jdbcTemplate.update(sql, balance, userId);
    }

    private Account mapRowToAccount(SqlRowSet rowSet) {
        Account account = new Account();
        account.setAccountId(rowSet.getInt("account_id"));
        account.setBalance(rowSet.getDouble("balance"));
        return account;
    }
}
