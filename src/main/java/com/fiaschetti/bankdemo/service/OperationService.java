package com.fiaschetti.bankdemo.service;

import com.fiaschetti.bankdemo.model.Operation;
import com.fiaschetti.bankdemo.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

    @Autowired
    OperationRepository operationRepository;

    public Operation addOperation(Operation operation) {
        return operationRepository.save(operation);
    }

}
