package com.fiaschetti.bankdemo.mapper;

import com.fiaschetti.bankdemo.model.Customer;
import com.fiaschetti.bankdemo.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toCustomer(CustomerDTO customerDTO);

    CustomerDTO toCustomerDTO(Customer customer);
}