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

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/user")

public class UserController {


    private final UserDao userDao;
    private final TransactionDao transactionDao;
    private final AccountDao accountDao;

    public UserController(UserDao userDao, TransactionDao transactionDao, AccountDao accountDao) {
        this.userDao = userDao;
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
    }
//User Functions

//    public void printList(List<User> users) {
//        try {
////            for (User user : users) {
////                System.out.println(user.getId());
////            }
//            System.out.println("UserController");
//        } catch (Exception e) {
//            System.out.println("UserController");
//        }
//    }

    @PreAuthorize("permitAll")
    @RequestMapping(method = RequestMethod.GET)
    public User[] findAll(){
        User[] usersArray = new User[userDao.findAll().size()];
//        printList(userDao.findAll());
        for (int i = 0; i < userDao.findAll().size(); i++) {
            usersArray[i] = userDao.findAll().get(i);
        }
        return usersArray;
    }

//    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
//    public User findByUsername(@PathVariable("username") @RequestParam String username){
//        User user = userDao.findByUsername(username);
//        if(user == null) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
//        } else return user;
//    }

    public int findIdByUsername(String username){
        int id = 0;
        id = userDao.findIdByUsername(username);
        if(id == 0 ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else return id;
    }


    @PreAuthorize("permitAll")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") int id){
        User user = userDao.getUserById(id);
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else return user;
    }

    @RequestMapping(path = "/id?={id}", method = RequestMethod.GET)
    public User getAnyUserById(@RequestParam int id) {
        User user = userDao.getUserById(id);
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else return user;
    }

    @PreAuthorize("permitAll")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public boolean create(@RequestParam String username, @RequestParam String password){
        return userDao.create(username,password);
    }

    //Account Functions
    public Account getAccountByUserId(int userId){
        Account account = accountDao.getAccountByUserId(userId);
        if(account == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        } else return account;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/{userId}/balance", method = RequestMethod.GET)
    public double getBalance(@PathVariable("userId") int userId){
        double balance = 0;
        try {
            balance = accountDao.getBalance(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "/{userId}/balance", method = RequestMethod.PUT)
    public void updateBalance(@RequestBody Double balance, @PathVariable("userId") int userId){
        accountDao.updateBalance(balance, userId);
    }

    //Transaction Functions


    public List<Transaction> listAllTransactions(){
        return transactionDao.listAllTransactions();
    }

    @RequestMapping(path = "/{id}/transaction" ,method = RequestMethod.GET)
    public List<Transaction> listTransactionByUserid(@PathVariable int id){
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
