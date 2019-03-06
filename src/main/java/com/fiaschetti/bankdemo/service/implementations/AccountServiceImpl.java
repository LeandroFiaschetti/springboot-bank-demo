package com.fiaschetti.bankdemo.service.implementations;

import com.fiaschetti.bankdemo.exception.BankRequestException;
import com.fiaschetti.bankdemo.model.Account;
import com.fiaschetti.bankdemo.model.Operation;
import com.fiaschetti.bankdemo.repository.AccountRepository;
import com.fiaschetti.bankdemo.repository.OperationRepository;
import com.fiaschetti.bankdemo.service.interfaces.AccountService;
import com.fiaschetti.bankdemo.service.interfaces.CustomerService;
import com.fiaschetti.bankdemo.validation.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    AccountValidator accountValidator;

    public Account addAccount(Account account) throws BankRequestException {
        if (account.getAccountId() == null)
            accountValidator.validateNewAccount(account);
        else
            accountValidator.validateUpdateAccount(account);
        account.setOwner(customerService.getCustomerById(account.getOwner().getCustomerId()));
        return accountRepository.save(account);
    }

    public void removeAccount(Long accountID) throws BankRequestException {
        accountValidator.validateAccountId(accountID);
        accountRepository.deleteById(accountID);
    }

    public void increaseMoney(Long accountID, BigDecimal amount) throws BankRequestException {
        accountValidator.validateAccountId(accountID);
        Account account = getAccountById(accountID);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

    public void decreaseMoney(Long accountID, BigDecimal amount) throws BankRequestException {
        accountValidator.validateAccountId(accountID);
        Account account = getAccountById(accountID);
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
    }

    public Account getAccountById(Long accountID) throws BankRequestException {
        accountValidator.validateAccountId(accountID);
        return accountRepository.findById(accountID).get();
    }

    public List<Operation> getAccountOperation(Long accountID) throws BankRequestException {
        accountValidator.validateAccountId(accountID);
        return operationRepository.getAccountOperations(accountID);
    }

    public List<Account> getAccountfromCustomerId(Long customerID) throws BankRequestException {
        accountValidator.validateOwnerId(customerID);
        return accountRepository.findByOwner_customerId(customerID);
    }
}
