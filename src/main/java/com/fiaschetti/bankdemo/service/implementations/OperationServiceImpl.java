package com.fiaschetti.bankdemo.service.implementations;

import com.fiaschetti.bankdemo.model.Operation;
import com.fiaschetti.bankdemo.repository.OperationRepository;
import com.fiaschetti.bankdemo.service.interfaces.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    OperationRepository operationRepository;

    public Operation addOperation(Operation operation) {
        return operationRepository.save(operation);
    }

}
