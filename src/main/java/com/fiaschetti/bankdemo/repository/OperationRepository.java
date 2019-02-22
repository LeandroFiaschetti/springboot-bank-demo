package com.fiaschetti.bankdemo.repository;

import com.fiaschetti.bankdemo.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

	@Query("select op from Operation op where op.targetAccount.accountId = :accountId order by op.date desc")
	public List<Operation> getAccountOperations(@Param("accountId") Long accountId);

}