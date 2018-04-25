package com.gt.app.request;

import com.gt.app.constant.MessageConstant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * NameRequest
 *
 * @author yousheng
 * @since 2018/4/24
 */
public class NameRequest {
    @Email(message = MessageConstant.MSG_ERR_EMAIL_INVALID)
    @NotNull(message = MessageConstant.MSG_ERR_EMAIL_EMPTY)
    private String email;

    public String getEmail() {
        return email;
    }

    public NameRequest setEmail(String email) {
        this.email = email;
        return this;
    }
}
