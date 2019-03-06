package com.fiaschetti.bankdemo.model;

import com.fiaschetti.bankdemo.enums.OperationType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "transfer_operation")
@DiscriminatorValue(value = OperationType.Types.TRANSFER_OPERATION)
public class TransferOperation extends Operation {

    @ManyToOne
    @JoinColumn(name = "source_account", referencedColumnName = "account_id")
    private Account sourceAccount;

    public TransferOperation(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public TransferOperation(Date date, String description, BigDecimal amount, Account account, Account sourceAccount) {
        super(date, description, amount, account);
        this.sourceAccount = sourceAccount;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransferOperation)) return false;
        TransferOperation that = (TransferOperation) o;
        return Objects.equals(sourceAccount, that.sourceAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceAccount);
    }

}
