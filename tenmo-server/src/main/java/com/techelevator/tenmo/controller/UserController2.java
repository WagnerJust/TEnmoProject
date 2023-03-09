package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransactionDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/user")
public class UserController2 {

    private final UserDao userDao;
    private final TransactionDao transactionDao;
    private final AccountDao accountDao;

    public UserController2(UserDao userDao, TransactionDao transactionDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
    }
//User Functions
    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll(){
        return userDao.findAll();
    }

    @RequestMapping(method = RequestMethod.GET)
    public User findByUsername(@RequestParam String username){
        User user = userDao.findByUsername(username);
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    public int findIdByUsername(@RequestParam String username){
        int id = 0;
        id = userDao.findIdByUsername(username);
        if(id == 0 ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else return id;
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id){
        User user = userDao.getUserById(id);
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else return user;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public boolean create(@RequestParam String username, @RequestParam String password){
        return userDao.create(username,password);
    }

    //Account Functions
    @RequestMapping(method = RequestMethod.GET)
    public Account getAccountByUserId(@RequestParam int userId){
        Account account = accountDao.getAccountByUserId(userId);
        if(account == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else return account;
    }

    @RequestMapping(method = RequestMethod.GET)
    public BigDecimal getBalance(@RequestParam int userId){
        return accountDao.getBalance(userId);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateBalance(@RequestParam BigDecimal balance, @RequestParam int userId){
        accountDao.updateBalance(balance,userId);
    }

    //Transaction Functions

    @RequestMapping(method = RequestMethod.GET)
    public List<Transaction> listAllTransactions(){
        return transactionDao.listAllTransactions();
    }

    @RequestMapping(path = "/{id}/transactions" ,method = RequestMethod.GET)
    public List<Transaction> listTransactionsByUserid(@PathVariable int id){
        return transactionDao.listTranscationByUserId(id);
    }

    @RequestMapping( path = "/{id}/transaction/{transactionId}",method = RequestMethod.GET)
    public Transaction getTransactionByTransactionId(@PathVariable("id") int id, @PathVariable("transactionId") int transactionId){
        return transactionDao.getTransactionByTransactionId(id);
    }

    @RequestMapping(path = "/{id}/transaction" , method = RequestMethod.POST)
    public void createTransaction(@PathVariable int id, @RequestParam Transaction transaction){
        transactionDao.createTransaction(transaction);
    }

//    @RequestMapping( path = "/transaction/{id}" , method = RequestMethod.PUT)
//    public void updateTransactionStatus(@PathVariable int id, @RequestParam int statusId){
//        transactionDao.updateTransactionStatus(id, statusId);
//    }

}
