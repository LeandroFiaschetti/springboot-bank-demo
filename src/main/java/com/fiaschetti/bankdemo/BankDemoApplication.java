package com.fiaschetti.bankdemo;

import com.fiaschetti.bankdemo.model.Account;
import com.fiaschetti.bankdemo.model.Customer;
import com.fiaschetti.bankdemo.service.AccountService;
import com.fiaschetti.bankdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankDemoApplication {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(BankDemoApplication.class, args);
	}

	@Bean
	public void demodata() {
		Customer c = new Customer();
		c.setFirstName("Leandro");
		c.setLastName("Fiaschetti");
		c.setMail("leandrofiaschetti@gmail.com");
		c.setPassword("admin");
		customerService.newCustomer(c);

		Account a = new Account();
		a.setOwner(c);
		a.setBalance(0);
		a.setType("S");
		accountService.addAccount(a);
	}

}
