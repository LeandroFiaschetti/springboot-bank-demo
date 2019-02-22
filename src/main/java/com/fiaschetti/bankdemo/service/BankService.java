package com.fiaschetti.bankdemo.service;

import com.fiaschetti.bankdemo.exception.BankRequestException;
import com.fiaschetti.bankdemo.model.*;
import com.fiaschetti.bankdemo.validation.BankRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BankService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private BankRequestValidator bankRequestValidator;

    public Operation depositToAccount(Customer customer, Long accountId, double amount) throws BankRequestException {
        Account currentAcc = accountService.getAccountById(accountId);
        DepositOperation depositOperation = new DepositOperation(new Date(), "deposit by "
                + customer.getLastName() + " " + customer.getFirstName(), amount, currentAcc);
        bankRequestValidator.validateDeposit(customer, depositOperation);
        Operation o = operationService.addOperation(depositOperation);
        accountService.increaseMoney(accountId, amount);
        return o;
    }

    public Operation withdrawFromAccount(Customer customer, Long accountId, double amount) throws BankRequestException {
        Account currentAcc = accountService.getAccountById(accountId);
        WithdrawOperation withdrawalOp = new WithdrawOperation(new Date(), "withdraw from cash machine", amount, currentAcc);
        bankRequestValidator.validateWithdrawall(customer, withdrawalOp);
        Operation o = operationService.addOperation(withdrawalOp);
        accountService.decreaseMoney(accountId, amount);
        return o;
    }

    public void transfer(Customer customer, Long accountOriginId, Long accountDestId, double amount) throws BankRequestException {
        withdrawFromAccount(customer, accountOriginId, amount);
        depositToAccount(customer, accountDestId, amount);
    }

    public List<Operation> getAccountOperation(Customer customer, Long accountId) throws BankRequestException {
        Account currentAcc = accountService.getAccountById(accountId);
        bankRequestValidator.validateCustomerAccount(customer, currentAcc);
        return accountService.getAccountOperation(accountId);
    }
}
