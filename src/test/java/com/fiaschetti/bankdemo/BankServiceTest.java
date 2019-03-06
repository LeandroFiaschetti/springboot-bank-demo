package com.fiaschetti.bankdemo;

import com.fiaschetti.bankdemo.exception.BankRequestException;
import com.fiaschetti.bankdemo.model.*;
import com.fiaschetti.bankdemo.service.interfaces.AccountService;
import com.fiaschetti.bankdemo.service.interfaces.BankService;
import com.fiaschetti.bankdemo.service.interfaces.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
@Transactional
public class BankServiceTest {

    @Autowired
    BankService bankService;

    @Autowired
    AccountService accountService;

    @Autowired
    CustomerService customerService;

    @Before
    public void setUp() throws BankRequestException {
        Customer c1 = new Customer();
        c1.setFirstName("N1");
        c1.setLastName("L1");
        c1.setMail("N1@gmail.com");
        c1.setPassword("N1");
        customerService.newCustomer(c1);

        Account a1 = new Account();
        a1.setOwner(c1);
        a1.setBalance(new BigDecimal(0));
        a1.setType("S");
        a1 = accountService.addAccount(a1);
        bankService.depositToAccount(c1, a1.getAccountId(), new BigDecimal(1000));

        Customer c2 = new Customer();
        c2.setFirstName("N2");
        c2.setLastName("L2");
        c2.setMail("N2@gmail.com");
        c2.setPassword("N2");
        customerService.newCustomer(c2);

        Account a2 = new Account();
        a2.setOwner(c2);
        a2.setBalance(new BigDecimal(0));
        a2.setType("S");
        a2 = accountService.addAccount(a2);
        bankService.depositToAccount(c2, a2.getAccountId(), new BigDecimal(1000));
    }

    @Test
    public void depositMoney() throws BankRequestException {
        Customer customer = customerService.getCustomerByMail("N1@gmail.com");
        Account originalAccount = accountService.getAccountfromCustomerId(customer.getCustomerId()).get(0);
        BigDecimal balanceBefore = originalAccount.getBalance();
        try {
            bankService.depositToAccount(customer, originalAccount.getAccountId(), new BigDecimal(10));
            Account increasedAccount = accountService.getAccountById(originalAccount.getAccountId());
            BigDecimal balanceAfter = increasedAccount.getBalance();
            Assert.assertTrue(balanceAfter.equals(balanceBefore.add(new BigDecimal(10))));
        } catch (BankRequestException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void withdrawMoney() throws BankRequestException {
        Customer customer = customerService.getCustomerByMail("N1@gmail.com");
        Account originalAccount = accountService.getAccountfromCustomerId(customer.getCustomerId()).get(0);
        BigDecimal balanceBefore = originalAccount.getBalance();
        try {
            bankService.withdrawFromAccount(customer, originalAccount.getAccountId(), new BigDecimal(10));
            Account decreasedAccount = accountService.getAccountById(originalAccount.getAccountId());
            BigDecimal balanceAfter = decreasedAccount.getBalance();
            Assert.assertTrue(balanceAfter.equals(balanceBefore.subtract(new BigDecimal(10))));
        } catch (BankRequestException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test(expected = BankRequestException.class)
    public void withdrawFromNotOwnerAccount() throws BankRequestException {
        Customer customer = customerService.getCustomerByMail("N1@gmail.com");
        Customer otherCustomer = customerService.getCustomerByMail("N2@gmail.com");
        Account otherAccount = accountService.getAccountfromCustomerId(otherCustomer.getCustomerId()).get(0);
        bankService.withdrawFromAccount(customer, otherAccount.getAccountId(), new BigDecimal(10));
    }

    @Test(expected = BankRequestException.class)
    public void notEnoughMoney() throws BankRequestException {
        Customer customer = customerService.getCustomerByMail("N2@gmail.com");
        Account account = accountService.getAccountfromCustomerId(customer.getCustomerId()).get(0);
        bankService.withdrawFromAccount(customer, account.getAccountId(), account.getBalance().add(new BigDecimal(10)));
    }

    @Test(expected = BankRequestException.class)
    public void depositNegativeMoney() throws BankRequestException {
        Customer customer = customerService.getCustomerByMail("N2@gmail.com");
        Account account = accountService.getAccountfromCustomerId(customer.getCustomerId()).get(0);
        bankService.depositToAccount(customer, account.getAccountId(), new BigDecimal(-10));
    }

    @Test
    public void accountOperations() throws BankRequestException {
        Customer customer = customerService.getCustomerByMail("N2@gmail.com");
        Account account = accountService.getAccountfromCustomerId(customer.getCustomerId()).get(0);
        bankService.depositToAccount(customer, account.getAccountId(), new BigDecimal(10));
        bankService.depositToAccount(customer, account.getAccountId(), new BigDecimal(10));
        bankService.withdrawFromAccount(customer, account.getAccountId(), new BigDecimal(10));

        List<Operation> operations = bankService.getAccountOperation(customer, account.getAccountId());
        // In the setUp method there is a first deposit operation of 1000
        // Total operation: 3 deposits + 1 withdraw
        Assert.assertEquals(4, operations.size());
        List<Operation> depositOperations = operations.stream().filter(o -> o instanceof DepositOperation).collect(Collectors.toList());
        Assert.assertEquals(3, depositOperations.size());
        List<Operation> withdrawOperations = operations.stream().filter(o -> o instanceof WithdrawalOperation).collect(Collectors.toList());
        Assert.assertEquals(1, withdrawOperations.size());
    }

}