package com.fiaschetti.bankdemo.repository;

import com.fiaschetti.bankdemo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByMail(String mail);
}