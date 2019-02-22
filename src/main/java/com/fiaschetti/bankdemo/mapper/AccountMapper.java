package com.fiaschetti.bankdemo.mapper;

import com.fiaschetti.bankdemo.dto.AccountDTO;
import com.fiaschetti.bankdemo.model.Account;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(target = "ownerId", source = "owner.customerId")
    @Named("mapAcountDTO")
    AccountDTO toAccountDTO(Account account);

    @Mapping(target = "owner.customerId", source = "ownerId")
    Account toAccount(AccountDTO accountDTO);

    @IterableMapping(qualifiedByName="mapAcountDTO")
    List<AccountDTO> toListAccountDTO(List<Account> accounts);

}