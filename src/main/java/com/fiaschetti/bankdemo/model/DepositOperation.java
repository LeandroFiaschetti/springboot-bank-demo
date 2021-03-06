package com.fiaschetti.bankdemo.model;

import com.fiaschetti.bankdemo.enums.OperationType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "deposit_operation")
@DiscriminatorValue(value = OperationType.Types.DEPOSIT_OPERATION)
public class DepositOperation extends Operation {

    public DepositOperation() {
        super();
    }

    public DepositOperation(Date operationDate, String description, BigDecimal amount, Account account) {
        super(operationDate, description, amount, account);
    }


}
