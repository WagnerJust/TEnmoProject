package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transaction;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/user/{id}/account")
public class AccountController {

    private AccountDao dao;
    private UserDao userDao;


    public AccountController(AccountDao dao) {
        this.dao = dao;
    }



    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/transaction", method = RequestMethod.GET)
    public List<Transaction> getAllTransactions() throws UsernameNotFoundException {
        UserController userController = new UserController();
        int userId = userDao.findIdByUsername(userController.retrieveUsername());
        return dao.getAccountByAccountId(userId).getTransactionHistory();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    public BigDecimal getUserBalance(Principal principal) throws UsernameNotFoundException {
        UserController userController = new UserController();
        int userId = userDao.findIdByUsername(retrieveUsername());
        BigDecimal userBalance = dao.getAccountByUserId(userId).getBalance();
        return userController.getBalance();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "")

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "to/user={id}", method = RequestMethod.POST)
    public Transaction createTransactionTo(@PathVariable int secondUserId) throws HttpClientErrorException.BadRequest{

    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "from/user={id}", method = RequestMethod.POST)
    public Transaction createTransactionFrom(@PathVariable int secondUserId) throws HttpClientErrorException.BadRequest {

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "?transaction={transactionId}", method = RequestMethod.GET)
    public Transaction getTransactionByTransactionid(@PathVariable int transactionId) throws HttpClientErrorException.BadRequest {

    }

//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(path = "?status={statusCode}", method = RequestMethod.GET)
//    public Transaction getTransactionByStatus(@PathVariable String status) throws HttpClientErrorException.BadRequest {
//
//    }



}
