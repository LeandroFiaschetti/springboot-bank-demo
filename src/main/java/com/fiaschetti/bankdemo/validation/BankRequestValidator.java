package com.fiaschetti.bankdemo.validation;

import com.fiaschetti.bankdemo.exception.BankRequestException;
import com.fiaschetti.bankdemo.model.Account;
import com.fiaschetti.bankdemo.model.Customer;
import com.fiaschetti.bankdemo.model.Operation;
import org.springframework.stereotype.Component;

@Component
public class BankRequestValidator {

    public void validateCustomerAccount(Customer customer, Account account) throws BankRequestException {
        if (!account.getOwner().getCustomerId().equals(customer.getCustomerId())) {
            throw new BankRequestException("The selected account does not belong to the user");
        }
    }

    public void validateDeposit(Customer customer, Operation operation) throws BankRequestException {
        if (operation.getAmount() < 0) {
            throw new BankRequestException("Can not deposit negative amount");
        }
    }

    public void validateWithdrawall(Customer customer, Operation operation) throws BankRequestException {
        if (!customer.getCustomerId().equals(operation.getTargetAccount().getOwner().getCustomerId())) {
            throw new BankRequestException("The selected account does not belong to the user");
        }

        if (operation.getTargetAccount().getBalance() - operation.getAmount() < 0) {
            throw new BankRequestException("Does not have enough money");
        }
    }

}
