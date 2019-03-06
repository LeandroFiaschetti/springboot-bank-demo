package com.fiaschetti.bankdemo.validation;

import com.fiaschetti.bankdemo.exception.BankRequestException;
import com.fiaschetti.bankdemo.model.Customer;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class CustomerValidator {

    private static final Pattern pattern = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", Pattern.CASE_INSENSITIVE);

    public void validateNewCustomer(Customer customer) throws BankRequestException {
        validateFirstname(customer.getFirstName());
        validateLastname(customer.getLastName());
        validateMail(customer.getMail());
        validatePassword(customer.getPassword());
    }

    public void validateFirstname(String firstname) throws BankRequestException {
        if (Strings.isBlank(firstname)) {
            throw new BankRequestException("First name cannot be null");
        }
    }

    public void validateLastname(String lastname) throws BankRequestException {
        if (Strings.isBlank(lastname)) {
            throw new BankRequestException("Last name cannot be null");
        }
    }

    public void validateMail(String mail) throws BankRequestException {
        if (Strings.isBlank(mail)) {
            throw new BankRequestException("Mail cannot be null");
        }

        if (!pattern.matcher(mail).matches()) {
            throw new BankRequestException("Invalid mail");
        }
    }

    public void validatePassword(String password) throws BankRequestException {
        if (Strings.isBlank(password)) {
            throw new BankRequestException("Password cannot be null");
        }
    }

    public void validateCustomerId(Long customerId) throws BankRequestException {
        if (customerId == null) {
            throw new BankRequestException("Customer id must be not null");
        }

    }

}
