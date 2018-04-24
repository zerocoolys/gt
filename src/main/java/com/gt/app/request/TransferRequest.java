package com.gt.app.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * TransferRequest
 *
 * @author yousheng
 * @since 2018/4/24
 */
public class TransferRequest {

    @Email(message = "source account is invalid.")
    @NotNull(message = "source account is empty.")
    private String email;

    @Email(message = "transferee account is invalid.")
    @NotNull(message = "transferee account is empty.")
    private String transferee;

    @Positive(message = "amount is invalid.")
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
