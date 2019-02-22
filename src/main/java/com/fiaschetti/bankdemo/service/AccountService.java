package com.fiaschetti.bankdemo.service;

import com.fiaschetti.bankdemo.model.Account;
import com.fiaschetti.bankdemo.model.Operation;
import com.fiaschetti.bankdemo.repository.AccountRepository;
import com.fiaschetti.bankdemo.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    CustomerService customerService;

    public Account addAccount(Account account) {
        account.setOwner(customerService.getCustomerById(account.getOwner().getCustomerId()));
        return accountRepository.save(account);
    }

    public void removeAccount(Long accountID) {
        accountRepository.deleteById(accountID);
    }

    public Account getAccountById(Long accountID) {
        return accountRepository.findById(accountID).get();
    }

    public void increaseMoney(Long accountID, Double amount) {
        Account account = getAccountById(accountID);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void decreaseMoney(Long accountID, Double amount) {
        Account account = getAccountById(accountID);
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    public List<Operation> getAccountOperation(Long accountID) {
        return operationRepository.getAccountOperations(accountID);
    }
}
