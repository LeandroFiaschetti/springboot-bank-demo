package com.fiaschetti.bankdemo.service.interfaces;

import com.fiaschetti.bankdemo.exception.BankRequestException;
import com.fiaschetti.bankdemo.model.Customer;

public interface CustomerService {

    Customer newCustomer(Customer customer) throws BankRequestException;

    void removeCustomer(Long customerID) throws BankRequestException;

    Customer getCustomerById(Long customerID) throws BankRequestException;

    Customer getCustomerByMail(String mail) throws BankRequestException;

    Customer getLoggedInCustomer() throws BankRequestException;
}
