package com.fiaschetti.bankdemo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class WithdrawalOperationDTO extends OperationDTO {

    public WithdrawalOperationDTO() {
        super();
    }

    public WithdrawalOperationDTO(Date operationDate, String description, BigDecimal amount) {
        super(operationDate, description, amount);
    }


}
