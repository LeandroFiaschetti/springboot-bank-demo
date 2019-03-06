package com.fiaschetti.bankdemo.service.interfaces;

import com.fiaschetti.bankdemo.exception.BankRequestException;
import com.fiaschetti.bankdemo.model.*;

import java.math.BigDecimal;
import java.util.List;

public interface BankService {

    Operation depositToAccount(Customer customer, Long accountId, BigDecimal amount) throws BankRequestException;

    Operation withdrawFromAccount(Customer customer, Long accountId, BigDecimal amount) throws BankRequestException;

    void transfer(Customer customer, Long accountOriginId, Long accountDestId, BigDecimal amount) throws BankRequestException;

    List<Operation> getAccountOperation(Customer customer, Long accountId) throws BankRequestException;
}
