package com.fiaschetti.bankdemo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DepositOperationDTO extends OperationDTO {

    public DepositOperationDTO() {
        super();
    }

    public DepositOperationDTO(Date operationDate, String description, BigDecimal amount) {
        super(operationDate, description, amount);
    }


}
