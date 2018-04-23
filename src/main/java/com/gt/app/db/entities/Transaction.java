package com.gt.app.db.entities;

import javax.persistence.*;

/**
 * Transaction
 *
 * @author yousheng
 * @since 2018/4/23
 */
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Integer id;

    private Double amount;

    private Long datetime;

    @Column(name = "account_from")
    private Integer from;

    @Column(name = "account_to")
    private Integer to;

    private String type;

    public Integer getId() {
        return id;
    }

    public Transaction setId(Integer id) {
        this.id = id;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public Transaction setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Long getDatetime() {
        return datetime;
    }

    public Transaction setDatetime(Long datetime) {
        this.datetime = datetime;
        return this;
    }

    public String getType() {
        return type;
    }

    public Transaction setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getFrom() {
        return from;
    }

    public Transaction setFrom(Integer from) {
        this.from = from;
        return this;
    }

    public Integer getTo() {
        return to;
    }

    public Transaction setTo(Integer to) {
        this.to = to;
        return this;
    }
}
