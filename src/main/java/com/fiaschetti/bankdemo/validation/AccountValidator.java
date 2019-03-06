package com.fiaschetti.bankdemo.validation;

import com.fiaschetti.bankdemo.exception.BankRequestException;
import com.fiaschetti.bankdemo.model.Account;
import com.fiaschetti.bankdemo.model.Customer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountValidator {

    public void validateNewAccount(Account account) throws BankRequestException {
        validatetype(account.getType());
        validateOwner(account.getOwner());
        validateBalance(account.getBalance());
        validateBalanceZero(account.getBalance());
    }

    public void validateUpdateAccount(Account account) throws BankRequestException {
        validatetype(account.getType());
        validateOwner(account.getOwner());
        validateBalance(account.getBalance());
    }

    public void validatetype(String type) throws BankRequestException {
        if (type == null)
            throw new BankRequestException("Account type cannot be null");
    }

    public void validateOwner(Customer owner) throws BankRequestException {
        if (owner == null)
            throw new BankRequestException("Account must have an owner");
    }

    public void validateBalance(BigDecimal balance) throws BankRequestException {
        if (balance == null)
            throw new BankRequestException("Balance cannot be null");

        if (balance.compareTo(BigDecimal.ZERO) < 0)
            throw new BankRequestException("New account must have balance equals to zero");
    }

    public void validateBalanceZero(BigDecimal balance) throws BankRequestException {
        if (balance.compareTo(BigDecimal.ZERO) != 0)
            throw new BankRequestException("New account must have balance equals to zero");
    }

    public void validateAccountId(Long accountId) throws BankRequestException {
        if (accountId == null)
            throw new BankRequestException("Id must be not null");
    }

    public void validateOwnerId(Long customerId) throws BankRequestException {
        if (customerId == null)
            throw new BankRequestException("Id owner must be not null");
    }

}
