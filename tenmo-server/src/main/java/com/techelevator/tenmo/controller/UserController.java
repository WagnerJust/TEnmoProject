package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class UserController {

    private UserDao dao;
    private AccountDao accountDao;

    public String retrieveUsername() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    public BigDecimal getBalance() {
        return accountDao.getAccountByUserId(dao.findIdByUsername(retrieveUsername())).getBalance();
    }
}
