package com.fiaschetti.bankdemo.web.controller;

import com.fiaschetti.bankdemo.exception.BankRequestException;
import com.fiaschetti.bankdemo.mapper.CustomerMapper;
import com.fiaschetti.bankdemo.model.Customer;
import com.fiaschetti.bankdemo.dto.CustomerDTO;
import com.fiaschetti.bankdemo.service.interfaces.CustomerService;
import com.fiaschetti.bankdemo.web.response.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@Controller
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseBase> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            Customer c = customerService.newCustomer(CustomerMapper.INSTANCE.toCustomer(customerDTO));
            return ResponseEntity.ok(new ResponseBase(true, c, "Customer was successfully created", null));
        } catch (BankRequestException e) {
            return ResponseEntity.badRequest().body(new ResponseBase(true, null, "Error creating customer", Collections.singletonList(e.getMessage())));
        }
    }

    @RequestMapping(value = "/{customerID}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseBase> deleteCustomer(@PathVariable("customerID") Long customerID) {
        try {
            customerService.removeCustomer(customerID);
            return ResponseEntity.ok(new ResponseBase(true, null, "Customer was successfully deleted", null));
        } catch (BankRequestException e) {
            return ResponseEntity.badRequest().body(new ResponseBase(true, null, "Error deleting customer", Collections.singletonList(e.getMessage())));
        }
    }
}
