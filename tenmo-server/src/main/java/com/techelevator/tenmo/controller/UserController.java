package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

@RestController
public class UserController {

    private UserDao dao;
    private AccountDao accountDao;


    @PreAuthorize("isAuthenticated()")
    public String retrieveUsername() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    @PreAuthorize("isAuthenticated()")
    public BigDecimal getBalance() {
        return accountDao.getAccountByUserId(dao.findIdByUsername(retrieveUsername())).getBalance();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(name = "/register", method = RequestMethod.POST)
    public boolean createUser(@RequestParam String username, @RequestParam String password) throws HttpClientErrorException.BadRequest {

        return dao.create(username, password);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(name = "/login", method = RequestMethod.GET)
    public User getUser(@RequestParam String username) throws HttpClientErrorException.BadRequest {
        return dao.findByUsername(username);
    }
}
