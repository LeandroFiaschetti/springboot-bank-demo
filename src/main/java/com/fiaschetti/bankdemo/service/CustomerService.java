package com.fiaschetti.bankdemo.service;

import com.fiaschetti.bankdemo.model.Account;
import com.fiaschetti.bankdemo.model.Customer;
import com.fiaschetti.bankdemo.repository.AccountRepository;
import com.fiaschetti.bankdemo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityService securityService;

    public Customer newCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    public void removeCustomer(Long customerID) {
        getAccountfromCustomerId(customerID).forEach((a) -> accountRepository.delete(a));
        customerRepository.deleteById(customerID);
    }

    public Customer getCustomerById(Long customerID) {
        return customerRepository.findById(customerID).get();
    }

    public Customer getCustomerByMail(String mail) {
        return customerRepository.findByMail(mail);
    }

    public Customer getLoggedInCustomer() {
        return getCustomerByMail(securityService.findLoggedInUsername());
    }

    public List<Account> getAccountfromCustomerId(Long customerID) {
        return accountRepository.findByOwner_customerId(customerID);
    }
}
