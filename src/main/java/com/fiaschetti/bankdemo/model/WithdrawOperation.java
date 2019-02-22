package com.fiaschetti.bankdemo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "withdraw_operation")
@DiscriminatorValue(value = "WO")
public class WithdrawOperation extends Operation {

    public WithdrawOperation() {
        super();
    }

    public WithdrawOperation(Date operationDate, String description, double amount, Account account) {
        super(operationDate, description, amount, account);
    }


}
