package com.fiaschetti.bankdemo.web.controller;

import com.fiaschetti.bankdemo.dto.AccountDTO;
import com.fiaschetti.bankdemo.dto.OperationDTO;
import com.fiaschetti.bankdemo.exception.BankRequestException;
import com.fiaschetti.bankdemo.mapper.AccountMapper;
import com.fiaschetti.bankdemo.mapper.OperationMapper;
import com.fiaschetti.bankdemo.model.Operation;
import com.fiaschetti.bankdemo.service.*;
import com.fiaschetti.bankdemo.web.request.DepositForm;
import com.fiaschetti.bankdemo.web.request.WithdrawallForm;
import com.fiaschetti.bankdemo.web.response.ResponseBase;
import com.fiaschetti.bankdemo.web.response.StateAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("bank")
public class BankController {

    @Autowired
    BankService bankService;

    @Autowired
    CustomerService customerService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public ResponseEntity<ResponseBase> deposit(@RequestBody DepositForm depositForm) {
        try {
            Operation o = bankService.depositToAccount(customerService.getLoggedInCustomer(), depositForm.getAccountId(), depositForm.getAmount());
            return ResponseEntity.ok(new ResponseBase(true, OperationMapper.INSTANCE.toDto(o), "Successful Operation", null));
        } catch (BankRequestException e) {
            return ResponseEntity.badRequest().body(new ResponseBase(false, null, e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public ResponseEntity<ResponseBase> withdraw(@RequestBody WithdrawallForm withdrawallForm) {
        try {
            Operation o = bankService.withdrawFromAccount(customerService.getLoggedInCustomer(), withdrawallForm.getAccountId(), withdrawallForm.getAmount());
            return ResponseEntity.ok(new ResponseBase(true, OperationMapper.INSTANCE.toDto(o), "Successful Operation", null));
        } catch (BankRequestException e) {
            return ResponseEntity.badRequest().body(new ResponseBase(false, null, e.getMessage(), null));
        }
    }

    @RequestMapping(value = "/state/{accountID}", method = RequestMethod.GET)
    public ResponseEntity<ResponseBase> state(@PathVariable("accountID") Long accountID) {
        try {
            AccountDTO accountDTO = AccountMapper.INSTANCE.toAccountDTO(accountService.getAccountById(accountID));
            List<OperationDTO> operations = OperationMapper.INSTANCE.toListDto(bankService.getAccountOperation(customerService.getLoggedInCustomer(), accountID));
            return ResponseEntity.ok(new StateAccountResponse(true, accountDTO, operations));
        } catch (BankRequestException e) {
            return ResponseEntity.badRequest().body(new ResponseBase(false, null, e.getMessage(), null));
        }
    }
}
