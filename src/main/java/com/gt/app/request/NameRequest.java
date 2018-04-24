package com.gt.app.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * NameRequest
 *
 * @author yousheng
 * @since 2018/4/24
 */
public class NameRequest {
    @Email(message = "email is invalid.")
    @NotNull(message = "email is empty.")
    private String email;

    public String getEmail() {
        return email;
    }

    public NameRequest setEmail(String email) {
        this.email = email;
        return this;
    }
}
