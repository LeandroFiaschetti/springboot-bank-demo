package com.fiaschetti.bankdemo.web.controller;

import com.fiaschetti.bankdemo.dto.AccountDTO;
import com.fiaschetti.bankdemo.mapper.AccountMapper;
import com.fiaschetti.bankdemo.mapper.CustomerMapper;
import com.fiaschetti.bankdemo.model.Account;
import com.fiaschetti.bankdemo.model.Customer;
import com.fiaschetti.bankdemo.service.CustomerService;
import com.fiaschetti.bankdemo.dto.CustomerDTO;
import com.fiaschetti.bankdemo.web.response.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseBase> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer c = customerService.newCustomer(CustomerMapper.INSTANCE.toCustomer(customerDTO));
        return ResponseEntity.ok(new ResponseBase(true, c, "Customer was successfully created", null));
    }

    @RequestMapping(value = "/{customerID}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseBase> deleteCustomer(@PathVariable("customerID") Long customerID) {
        customerService.removeCustomer(customerID);
        return ResponseEntity.ok(new ResponseBase(true, null, "Customer was successfully deleted", null));
    }

    @RequestMapping(value = "/accounts/{customerID}", method = RequestMethod.GET)
    public ResponseEntity<List<AccountDTO>> getCustomerAccounts(@PathVariable("customerID") Long customerID) {
        List<Account> customerAccount = customerService.getAccountfromCustomerId(customerID);
        return ResponseEntity.ok(AccountMapper.INSTANCE.toListAccountDTO(customerAccount));
    }
}
