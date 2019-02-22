package com.fiaschetti.bankdemo.dto;

import java.util.Date;

public class WithdrawOperationDTO extends OperationDTO {

    public WithdrawOperationDTO() {
        super();
    }

    public WithdrawOperationDTO(Date operationDate, String description, double amount) {
        super(operationDate, description, amount);
    }


}
