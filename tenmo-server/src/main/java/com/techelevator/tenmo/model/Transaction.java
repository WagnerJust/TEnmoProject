package com.techelevator.tenmo.model;


public class Transaction {
    private int transactionId;
    private int account_from;
    private int account_to;

    private double amount;
    private int transfer_status_id = 2;
    private int transfer_type_id;

//constructors

    public Transaction(){}

    public Transaction(int account_from, int account_to, double amount, int transfer_status_id, int transfer_type_id) {
        this.account_from = account_from;
        this.account_to = account_to;
        this.amount = amount;
        this.transfer_status_id = transfer_status_id;
        this.transfer_type_id = transfer_type_id;
    }
//getters and setters
    public int getaccount_from() {
        return account_from;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setaccount_from(int account_from) {
        this.account_from = account_from;
    }

    public int getaccount_to() {
        return account_to;
    }

    public void setaccount_to(int account_to) {
        this.account_to = account_to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int gettransfer_status_id() {
        return transfer_status_id;
    }

    public void settransfer_status_id(int transfer_status_id) {
        this.transfer_status_id = transfer_status_id;
    }

    public int gettransfer_type_id() {
        return transfer_type_id;
    }

    public void settransfer_type_id(int transfer_type_id) {
        this.transfer_type_id = transfer_type_id;
    }
    @Override
    public String toString() {
        return "Transfer Details: \n\t" +
                "transfer id: " + getTransactionId() +
                "\n\ttransfer amount: " + getAmount() +
                "\n\ttransfer status: " + gettransfer_status_id() +
                "\n\ttransfer type: " + gettransfer_type_id() +
                "\n\ttransfer from: " + getaccount_from() +
                "\n\ttransfer to: " + getaccount_to();
    }
}
