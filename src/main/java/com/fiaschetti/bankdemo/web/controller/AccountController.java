package com.fiaschetti.bankdemo.web.controller;

import com.fiaschetti.bankdemo.dto.AccountDTO;
import com.fiaschetti.bankdemo.mapper.AccountMapper;
import com.fiaschetti.bankdemo.model.Account;
import com.fiaschetti.bankdemo.service.AccountService;
import com.fiaschetti.bankdemo.web.response.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseBase> saveAccount(@RequestBody AccountDTO accountDTO) {
        Account a = accountService.addAccount(AccountMapper.INSTANCE.toAccount(accountDTO));
        return ResponseEntity.ok(new ResponseBase(true, AccountMapper.INSTANCE.toAccountDTO(a), "Account was successfully created", null));
    }

    @RequestMapping(value = "/{accountID}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseBase> deleteAccount(@PathVariable("accountID") Long accountID) {
        accountService.removeAccount(accountID);
        return ResponseEntity.ok(new ResponseBase(true, null,"Account was successfully deleted", null));
    }
}
