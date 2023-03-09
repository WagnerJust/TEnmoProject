package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transaction {
    private int transferId;
    private User acting;
    private User target;
    private BigDecimal amount;
    private String status;
    private String type;
    private int transferTypeId;
    private int transferStatusId;
    private int accountFrom;
    private int accountTo;


    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public User getActing() {
        return acting;
    }

    public void setActing(User acting) {
        this.acting = acting;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTransferTypeId() {
        return transferTypeId;
    }

    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }

    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public int getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }


    public Transaction(int transferId, User acting, User target, BigDecimal amount, String status, String type) {
        this.transferId = transferId;
        this.acting = acting;
        this.target = target;
        this.amount = amount;
        this.status = status;
        this.type = type;
    }
    public Transaction(){

    }
}
