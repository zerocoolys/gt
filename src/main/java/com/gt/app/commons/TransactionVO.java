package com.gt.app.commons;

/**
 * Transaction
 *
 * @author yousheng
 * @since 2018/4/23
 */
public class TransactionVO {
    private int id;
    private String from;
    private String to;
    private double amount;
    private String type;
    private String datetime;

    public int getId() {
        return id;
    }

    public TransactionVO setId(int id) {
        this.id = id;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public TransactionVO setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public TransactionVO setTo(String to) {
        this.to = to;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionVO setAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public String getType() {
        return type;
    }

    public TransactionVO setType(String type) {
        this.type = type;
        return this;
    }

    public String getDatetime() {
        return datetime;
    }

    public TransactionVO setDatetime(String datetime) {
        this.datetime = datetime;
        return this;
    }
}
