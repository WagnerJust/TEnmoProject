package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transaction {
    private int transactionId;
    private int actingId;
    private int targetId;

    private BigDecimal amount;
    private int statusId = 2;
    private int typeId;

//constructors

    public Transaction(){}

    public Transaction(int actingId, int targetId, BigDecimal amount, int statusId, int typeId) {
        this.actingId = actingId;
        this.targetId = targetId;
        this.amount = amount;
        this.statusId = statusId;
        this.typeId = typeId;
    }
    //getters and setters
    public int getActingId() {
        return actingId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setActingId(int actingId) {
        this.actingId = actingId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
    @Override
    public String toString() {
        return "Transfer Details: \n\t" +
                "transfer id: " + getTransactionId() + "|" +
                "transfer amount: " + getAmount() + "|" +
                "transfer status: " + getStatusId() + "|" +
                "transfer type: " + getTypeId() + "|" +
                "transfer from: " + getActingId() + "|" +
                "transfer to: " + getTargetId();
    }
}
