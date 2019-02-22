package com.fiaschetti.bankdemo.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "deposit_operation")
@DiscriminatorValue(value = "DO")
public class DepositOperation extends Operation {

    public DepositOperation() {
        super();
    }

    public DepositOperation(Date operationDate, String description, double amount, Account account) {
        super(operationDate, description, amount, account);
    }


}
