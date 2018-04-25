package com.gt.app.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import static com.gt.app.constant.MessageConstant.*;

/**
 * TransferRequest
 *
 * @author yousheng
 * @since 2018/4/24
 */
public class TransferRequest {

    @Email(message = MSG_ERR_TRANSFER_SRC_ACCOUNT_INVALID)
    @NotNull(message = MSG_ERR_TRANSFER_SRC_ACCOUNT_EMPTY)
    private String email;

    @Email(message = MSG_ERR_TRANSFER_DST_ACCOUNT_INVALID)
    @NotNull(message = MSG_ERR_TRANSFER_DST_ACCOUNT_EMPTY)
    private String transferee;

    @Positive(message = MSG_ERR_TRANSFER_AMOUNT_INVALID)
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
