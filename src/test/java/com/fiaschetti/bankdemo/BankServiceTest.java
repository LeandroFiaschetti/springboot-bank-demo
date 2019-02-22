package com.fiaschetti.bankdemo;

import com.fiaschetti.bankdemo.exception.BankRequestException;
import com.fiaschetti.bankdemo.model.*;
import com.fiaschetti.bankdemo.service.AccountService;
import com.fiaschetti.bankdemo.service.BankService;
import com.fiaschetti.bankdemo.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
    public void setUp() {
        Customer c1 = new Customer();
        c1.setFirstName("N1");
        c1.setLastName("L1");
        c1.setMail("N1@gmail.com");
        c1.setPassword("N1");
        customerService.newCustomer(c1);

        Account a1 = new Account();
        a1.setOwner(c1);
        a1.setBalance(1000);
        a1.setType("S");
        accountService.addAccount(a1);

        Customer c2 = new Customer();
        c2.setFirstName("N2");
        c2.setLastName("L2");
        c2.setMail("N2@gmail.com");
        c2.setPassword("N2");
        customerService.newCustomer(c2);

        Account a2 = new Account();
        a2.setOwner(c2);
        a2.setBalance(1000);
        a2.setType("S");
        accountService.addAccount(a2);
    }

    @Test
    public void depositMoney() {
        Customer customer = customerService.getCustomerByMail("N1@gmail.com");
        Account originalAccount = customerService.getAccountfromCustomerId(customer.getCustomerId()).get(0);
        Double balanceBefore = originalAccount.getBalance();
        try {
            bankService.depositToAccount(customer, originalAccount.getAccountId(), 10);
            Account increasedAccount = accountService.getAccountById(originalAccount.getAccountId());
            Double balanceAfter = increasedAccount.getBalance();
            Assert.assertTrue(balanceAfter == balanceBefore + 10);
        } catch (BankRequestException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test
    public void withdrawMoney() {
        Customer customer = customerService.getCustomerByMail("N1@gmail.com");
        Account originalAccount = customerService.getAccountfromCustomerId(customer.getCustomerId()).get(0);
        Double balanceBefore = originalAccount.getBalance();
        try {
            bankService.withdrawFromAccount(customer, originalAccount.getAccountId(), 10);
            Account decreasedAccount = accountService.getAccountById(originalAccount.getAccountId());
            Double balanceAfter = decreasedAccount.getBalance();
            Assert.assertTrue(balanceAfter == balanceBefore - 10);
        } catch (BankRequestException e) {
            Assert.fail();
            e.printStackTrace();
        }
    }

    @Test(expected = BankRequestException.class)
    public void withdrawFromNotOwnerAccount() throws BankRequestException {
        Customer customer = customerService.getCustomerByMail("N1@gmail.com");
        Customer otherCustomer = customerService.getCustomerByMail("N2@gmail.com");
        Account otherAccount = customerService.getAccountfromCustomerId(otherCustomer.getCustomerId()).get(0);
        bankService.withdrawFromAccount(customer, otherAccount.getAccountId(), 10);
    }

    @Test(expected = BankRequestException.class)
    public void notEnoughMoney() throws BankRequestException {
        Customer customer = customerService.getCustomerByMail("N2@gmail.com");
        Account account = customerService.getAccountfromCustomerId(customer.getCustomerId()).get(0);
        bankService.withdrawFromAccount(customer, account.getAccountId(), account.getBalance() + 10);
    }

    @Test(expected = BankRequestException.class)
    public void depositNegativeMoney() throws BankRequestException {
        Customer customer = customerService.getCustomerByMail("N2@gmail.com");
        Account account = customerService.getAccountfromCustomerId(customer.getCustomerId()).get(0);
        bankService.depositToAccount(customer, account.getAccountId(), -10);
    }

    @Test
    public void accountOperations() throws BankRequestException {
        Customer customer = customerService.getCustomerByMail("N2@gmail.com");
        Account account = customerService.getAccountfromCustomerId(customer.getCustomerId()).get(0);
        bankService.depositToAccount(customer, account.getAccountId(), 10);
        bankService.depositToAccount(customer, account.getAccountId(), 10);
        bankService.withdrawFromAccount(customer, account.getAccountId(), 10);

        List<Operation> operations = bankService.getAccountOperation(customer,account.getAccountId());
        Assert.assertEquals(operations.size(),3);
        List<Operation> depositOperations = operations.stream().filter(o -> o instanceof DepositOperation).collect(Collectors.toList());
        Assert.assertEquals(depositOperations .size(),2);
        List<Operation> withdrawOperations = operations.stream().filter(o -> o instanceof WithdrawOperation).collect(Collectors.toList());
        Assert.assertEquals(withdrawOperations.size(),1);
    }

}