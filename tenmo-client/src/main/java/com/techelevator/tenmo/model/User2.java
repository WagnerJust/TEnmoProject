//package com.techelevator.tenmo.model;
//
//import java.util.Objects;
//
//public class User2 {
//
//    private int id;
//    private Account account;
//    private String username;
//
//    public Account getAccount() {
//        return account;
//    }
//
//    public void setAccount(Account account) {
//        this.account = account;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other instanceof User) {
//            User otherUser = (User) other;
//            return otherUser.getId() == id
//                    && otherUser.getUsername().equals(username);
//        } else {
//            return false;
//        }
//    }
//    @Override
//    public String toString() {
//
//        return "User Details: \n\t" +
//                "User id: " + getId() +
//                "\n\tUser Balance: " +  getAccount();
//    }
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, username);
//    }
//}
//
//
