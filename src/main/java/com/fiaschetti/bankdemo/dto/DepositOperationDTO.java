package com.fiaschetti.bankdemo.dto;

import java.util.Date;

public class DepositOperationDTO extends OperationDTO {

    public DepositOperationDTO() {
        super();
    }

    public DepositOperationDTO(Date operationDate, String description, double amount) {
        super(operationDate, description, amount);
    }


}
