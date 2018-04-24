package com.gt.app.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

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

    @Column(unique = true)
    private String name;

    private BigDecimal balance;

    public Integer getId() {
        return id;
    }

    public Account setId(Integer id) {
        this.id = id;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Account setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public String getName() {
        return name;
    }

    public Account setName(String name) {
        this.name = name;
        return this;
    }
}
