package com.fiaschetti.bankdemo.web.controller;

import com.fiaschetti.bankdemo.dto.AccountDTO;
import com.fiaschetti.bankdemo.exception.BankRequestException;
import com.fiaschetti.bankdemo.mapper.AccountMapper;
import com.fiaschetti.bankdemo.model.Account;
import com.fiaschetti.bankdemo.service.interfaces.AccountService;
import com.fiaschetti.bankdemo.web.response.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseBase> saveAccount(@RequestBody AccountDTO accountDTO) {
        try {
            Account a = accountService.addAccount(AccountMapper.INSTANCE.toAccount(accountDTO));
            return ResponseEntity.ok(new ResponseBase(true, AccountMapper.INSTANCE.toAccountDTO(a), "Account was successfully created", null));
        } catch (BankRequestException e) {
            return ResponseEntity.badRequest().body(new ResponseBase(true, accountDTO, "Error creating account", Collections.singletonList(e.getMessage())));
        }
    }

    @RequestMapping(value = "/{accountID}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseBase> deleteAccount(@PathVariable("accountID") Long accountID) {
        try {
            accountService.removeAccount(accountID);
            return ResponseEntity.ok(new ResponseBase(true, null, "Account was successfully deleted", null));
        } catch (BankRequestException e) {
            return ResponseEntity.badRequest().body(new ResponseBase(true, null, "Error deleting account", Collections.singletonList(e.getMessage())));
        }
    }

    @RequestMapping(value = "/customer/{customerID}", method = RequestMethod.GET)
    public ResponseEntity<Object> getCustomerAccounts(@PathVariable("customerID") Long customerID) {
        try {
            List<Account> customerAccount = accountService.getAccountfromCustomerId(customerID);
            return ResponseEntity.ok(AccountMapper.INSTANCE.toListAccountDTO(customerAccount));
        } catch (BankRequestException e) {
            return ResponseEntity.badRequest().body(new ResponseBase(true, null, "Error returning accounts", Collections.singletonList(e.getMessage())));
        }
    }
}
