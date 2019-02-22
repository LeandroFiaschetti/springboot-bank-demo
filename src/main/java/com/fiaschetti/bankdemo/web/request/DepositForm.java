package com.fiaschetti.bankdemo.web.request;

public class DepositForm {

    private Long accountId;
    private Double amount;

    public DepositForm(Long accountId, Double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
