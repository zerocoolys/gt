package com.gt.app.db.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Account
 *
 * @author yousheng
 * @since 2018/4/23
 */
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Integer id;
    private Double balance;

    public Integer getId() {
        return id;
    }

    public Account setId(Integer id) {
        this.id = id;
        return this;
    }

    public Double getBalance() {
        return balance;
    }

    public Account setBalance(Double balance) {
        this.balance = balance;
        return this;
    }
}
