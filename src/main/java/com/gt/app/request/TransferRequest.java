package com.gt.app.request;

/**
 * TransferRequest
 *
 * @author yousheng
 * @since 2018/4/24
 */
public class TransferRequest {
    private String email;
    private String transferee;
    private double amount;

    public String getEmail() {
        return email;
    }

    public TransferRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTransferee() {
        return transferee;
    }

    public TransferRequest setTransferee(String transferee) {
        this.transferee = transferee;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public TransferRequest setAmount(double amount) {
        this.amount = amount;
        return this;
    }
}
