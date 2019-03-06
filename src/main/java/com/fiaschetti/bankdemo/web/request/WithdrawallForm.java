package com.fiaschetti.bankdemo.web.request;

import java.math.BigDecimal;

public class WithdrawallForm {

    private Long accountId;
    private BigDecimal amount;

    public WithdrawallForm(Long accountId, BigDecimal amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
