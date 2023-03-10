package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class JdbcAccountDao implements AccountDao {
    private JdbcTemplate jdbcTemplate;


    public JdbcAccountDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
//    public BigDecimal getBalance(int userId) throws SQLException {
//        BigDecimal balance = null;
//        String sql = "SELECT balance FROM account WHERE user_id = ?";
//      SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
////        balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);
////        if (balance.equals(null)) {
////            return BigDecimal.valueOf(Long.parseLong("1000"));
////        }
//        while (result.next()) {
//            balance = result.getBigDecimal("balance");
//        }
//        balance = getBigDecimalSafe(result, "balance");
//        return balance;
//    }

    public double getBalance(int userId) throws SQLException {
        double balance = 0;
        String sql = "SELECT balance FROM account WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
//        balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);
//        if (balance.equals(null)) {
//            return BigDecimal.valueOf(Long.parseLong("1000"));
//        }
        while (result.next()) {
            balance = result.getDouble("balance");
        }
//        balance = getBigDecimalSafe(result, "balance");
        return balance;
    }

    public BigDecimal getBigDecimalSafe(SqlRowSet resultSet, String columnName) throws SQLException {
        BigDecimal bigDecimal = null;
        if(resultSet.getString(columnName) != null){
            bigDecimal = new BigDecimal(resultSet.getString(columnName));
        }
        return bigDecimal;
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
    public void updateBalance(BigDecimal balance, int userId){
        String sql = "Update account set balance = ? where accountId = ?";
        jdbcTemplate.update(sql, balance, userId);
    }

    private Account mapRowToAccount(SqlRowSet rowSet) {
        Account account = new Account();
        account.setAccountId(rowSet.getInt("account_id"));
        account.setBalance(rowSet.getBigDecimal("balance"));
        return account;
    }
}
