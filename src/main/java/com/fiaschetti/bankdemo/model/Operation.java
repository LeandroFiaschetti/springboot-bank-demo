package com.fiaschetti.bankdemo.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "operation")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING, length = 2)
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOperation;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "target_account", referencedColumnName = "account_id")
    private Account targetAccount;

    public Operation() {

    }

    public Operation(Date date, String description, double amount, Account account) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.targetAccount = account;
    }

    public Long getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
