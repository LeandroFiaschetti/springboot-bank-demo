package com.fiaschetti.bankdemo.service.implementations;

import com.fiaschetti.bankdemo.exception.BankRequestException;
import com.fiaschetti.bankdemo.model.Account;
import com.fiaschetti.bankdemo.model.Customer;
import com.fiaschetti.bankdemo.repository.CustomerRepository;
import com.fiaschetti.bankdemo.service.interfaces.AccountService;
import com.fiaschetti.bankdemo.service.interfaces.CustomerService;
import com.fiaschetti.bankdemo.validation.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CustomerValidator customerValidator;

    public Customer newCustomer(Customer customer) throws BankRequestException {
        customerValidator.validateNewCustomer(customer);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @Transactional
    public void removeCustomer(Long customerID) throws BankRequestException {
        List<Account> accounts = accountService.getAccountfromCustomerId(customerID);

        for (Account a : accounts) {
            accountService.removeAccount(a.getAccountId());
        }

        customerRepository.deleteById(customerID);
    }

    public Customer getCustomerById(Long customerID) throws BankRequestException {
        customerValidator.validateCustomerId(customerID);
        return customerRepository.findById(customerID).get();
    }

    public Customer getCustomerByMail(String mail) throws BankRequestException {
        customerValidator.validateMail(mail);
        return customerRepository.findByMail(mail);
    }

    public Customer getLoggedInCustomer() throws BankRequestException {
        return getCustomerByMail(securityService.findLoggedInUsername());
    }

}
