package com.fiaschetti.bankdemo.repository;

import com.fiaschetti.bankdemo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findByOwner_customerId(Long customerId);
}