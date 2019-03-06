package com.fiaschetti.bankdemo.model;

import com.fiaschetti.bankdemo.enums.OperationType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "withdrawal_operation")
@DiscriminatorValue(value = OperationType.Types.WITDRAWAL_OPERATION)
public class WithdrawalOperation extends Operation {

    public WithdrawalOperation() {
        super();
    }

    public WithdrawalOperation(Date operationDate, String description, BigDecimal amount, Account account) {
        super(operationDate, description, amount, account);
    }


}
