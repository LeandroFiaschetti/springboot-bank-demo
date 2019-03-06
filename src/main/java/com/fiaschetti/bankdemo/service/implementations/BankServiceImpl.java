package com.fiaschetti.bankdemo.service.implementations;

import com.fiaschetti.bankdemo.exception.BankRequestException;
import com.fiaschetti.bankdemo.model.*;
import com.fiaschetti.bankdemo.service.interfaces.AccountService;
import com.fiaschetti.bankdemo.service.interfaces.BankService;
import com.fiaschetti.bankdemo.service.interfaces.OperationService;
import com.fiaschetti.bankdemo.validation.BankRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private BankRequestValidator bankRequestValidator;

    @Transactional
    public Operation depositToAccount(Customer customer, Long accountId, BigDecimal amount) throws BankRequestException {
        Account currentAcc = accountService.getAccountById(accountId);
        DepositOperation depositOperation = new DepositOperation(new Date(), "deposit by "
                + customer.getLastName() + " " + customer.getFirstName(), amount, currentAcc);
        bankRequestValidator.validateDeposit(customer, depositOperation);
        Operation o = operationService.addOperation(depositOperation);
        accountService.increaseMoney(accountId, amount);
        return o;
    }

    @Transactional
    public Operation withdrawFromAccount(Customer customer, Long accountId, BigDecimal amount) throws BankRequestException {
        Account currentAcc = accountService.getAccountById(accountId);
        WithdrawalOperation withdrawalOperation = new WithdrawalOperation(new Date(), "withdraw from cash machine", amount, currentAcc);
        bankRequestValidator.validateWithdrawall(customer, withdrawalOperation);
        Operation o = operationService.addOperation(withdrawalOperation);
        accountService.decreaseMoney(accountId, amount);
        return o;
    }

    @Transactional
    public void transfer(Customer customer, Long accountOriginId, Long accountDestId, BigDecimal amount) throws BankRequestException {
        withdrawFromAccount(customer, accountOriginId, amount);
        depositToAccount(customer, accountDestId, amount);
    }

    public List<Operation> getAccountOperation(Customer customer, Long accountId) throws BankRequestException {
        Account currentAcc = accountService.getAccountById(accountId);
        bankRequestValidator.validateCustomerAccount(customer, currentAcc);
        return accountService.getAccountOperation(accountId);
    }
}
