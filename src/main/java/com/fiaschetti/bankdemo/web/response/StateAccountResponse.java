package com.fiaschetti.bankdemo.web.response;

import com.fiaschetti.bankdemo.dto.AccountDTO;
import com.fiaschetti.bankdemo.dto.OperationDTO;

import java.util.ArrayList;
import java.util.List;

public class StateAccountResponse extends ResponseBase {
    AccountDTO accountDTO;
    List<OperationDTO> operations;

    public StateAccountResponse(Boolean response, AccountDTO accountDTO, List<OperationDTO> operations) {
        super(response, null, "", new ArrayList<>());
        this.accountDTO = accountDTO;
        this.operations = operations;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    public List<OperationDTO> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationDTO> operations) {
        this.operations = operations;
    }
}
