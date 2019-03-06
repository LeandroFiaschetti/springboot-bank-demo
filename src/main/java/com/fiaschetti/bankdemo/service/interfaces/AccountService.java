package com.fiaschetti.bankdemo.service.interfaces;

import com.fiaschetti.bankdemo.exception.BankRequestException;
import com.fiaschetti.bankdemo.model.Account;
import com.fiaschetti.bankdemo.model.Operation;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account addAccount(Account account) throws BankRequestException;

    void removeAccount(Long accountID) throws BankRequestException;

    Account getAccountById(Long accountID) throws BankRequestException;

    void increaseMoney(Long accountID, BigDecimal amount) throws BankRequestException;

    void decreaseMoney(Long accountID, BigDecimal amount) throws BankRequestException;

    List<Operation> getAccountOperation(Long accountID) throws BankRequestException;

    List<Account> getAccountfromCustomerId(Long customerID) throws BankRequestException;
}
