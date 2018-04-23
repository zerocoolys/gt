package com.gt.app.commons;

/**
 * Account
 *
 * @author yousheng
 * @since 2018/4/23
 */
public class AccountVO {
    private int id;
    private String name;
    private double balance;

    public int getId() {
        return id;
    }

    public AccountVO setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AccountVO setName(String name) {
        this.name = name;
        return this;
    }

    public double getBalance() {
        return balance;
    }

    public AccountVO setBalance(double balance) {
        this.balance = balance;
        return this;
    }
}
