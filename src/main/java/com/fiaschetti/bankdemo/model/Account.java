package com.fiaschetti.bankdemo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @Column(name = "type")
    private String type;

    @Column(name = "balance")
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer owner;

    public Account() {

    }

    public Account(String type, double balance, Customer owner) {
        this.type = type;
        this.balance = balance;
        this.owner = owner;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 &&
                Objects.equals(accountId, account.accountId) &&
                Objects.equals(type, account.type) &&
                Objects.equals(owner, account.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, type, balance, owner);
    }
}
