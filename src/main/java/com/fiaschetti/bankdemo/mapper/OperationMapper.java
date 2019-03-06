package com.fiaschetti.bankdemo.mapper;

import com.fiaschetti.bankdemo.dto.DepositOperationDTO;
import com.fiaschetti.bankdemo.dto.OperationDTO;
import com.fiaschetti.bankdemo.dto.WithdrawalOperationDTO;
import com.fiaschetti.bankdemo.model.DepositOperation;
import com.fiaschetti.bankdemo.model.Operation;
import com.fiaschetti.bankdemo.model.WithdrawalOperation;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OperationMapper {

    OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);

    @Mapping(target = "description", source = "description")
    DepositOperationDTO depositOperationToDepositOperationDto(DepositOperation depositOperation);

    @Mapping(target = "description", source = "description")
    WithdrawalOperationDTO withdrawalOperationToWithdrawOperationDto(WithdrawalOperation withdrawOperation);

    @Named("mapOperationDTO")
    default OperationDTO toDto(Operation o) {
        if (o instanceof DepositOperation)
            return depositOperationToDepositOperationDto((DepositOperation) o);
        return withdrawalOperationToWithdrawOperationDto((WithdrawalOperation) o);
    }

    @IterableMapping(qualifiedByName = "mapOperationDTO")
    List<OperationDTO> toListDto(List<Operation> o);
}