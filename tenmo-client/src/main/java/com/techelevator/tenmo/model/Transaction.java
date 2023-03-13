package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transaction {
    private int transactionId;
    private int actingId;
    private int targetId;

    private Double amount;
    private int statusId = 2;
    private int typeId;

//constructors

    public Transaction(){}

    public Transaction(Integer actingId, Integer targetId, Double amount, Integer statusId, Integer typeId) {
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

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public void setActingId(Integer actingId) {
        this.actingId = actingId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    @Override
    public String toString() {
        String type = null;
        String status;

        if (getTypeId() == 1) {
            type = "Request";
        } else if (getTypeId() == 2) {
            type = "Send";
        }
        
        if (getStatusId() == 1) {
            status = "Pending";
        } else if (getStatusId() == 2) {
            status = "Approved";
        } else {
            status = "Rejected";
        }

        if (type.equals("Send")) {

        }
        return "Transfer Details: \n\t" +
                "Id: " + getTransactionId() + " | " +
                "Amount: $" + getAmount() + " | " +
                "Status: " + status + " | " +
                "Type: " + type + " | " +
                "From: " + getActingId() + " | " +
                "To: " + getTargetId();
    }

    public String toStringDetails() {
        String type = null;
        String status;

        if (getTypeId() == 1) {
            type = "Request";
        } else if (getTypeId() == 2) {
            type = "Send";
        }

        if (getStatusId() == 1) {
            status = "Pending";
        } else if (getStatusId() == 2) {
            status = "Approved";
        } else {
            status = "Rejected";
        }
        return "Transfer Details: \n\t" +
                "Id: " + getTransactionId() + " | " +
                "Amount: $" + getAmount() + " | " +
                "Status: " + status + " | " +
                "Type: " + type + " | " +
                "From: " + getActingId() + " | " +
                "To: " + getTargetId();
    }
}
