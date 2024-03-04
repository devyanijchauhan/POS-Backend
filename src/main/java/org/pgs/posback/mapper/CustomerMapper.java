package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.dto.Customer.CustomerRequestDTO;
import org.pgs.posback.dto.Customer.CustomerResponseDTO;
import org.pgs.posback.model.Customer;

import java.util.List;

@Mapper()
public interface CustomerMapper {
    CustomerMapper INSTANCE= Mappers.getMapper(CustomerMapper.class);

    Customer CUSTOMER_REQ_DTO_TO_CUSTOMER(CustomerRequestDTO customerRequestDTO);

    @Mappings({
            @Mapping(target = "id", source = "customer.id"),
            @Mapping(target = "name", source = "customer.name"),
            @Mapping(target = "contactInformation", source = "customer.contactInformation"),
            @Mapping(target = "loyaltyPoints", source = "customer.loyaltyPoints"),
             @Mapping(target = "dateOfBirth", dateFormat = "yourDateFormat", source = "customer.dateOfBirth"),
            @Mapping(target = "email", source = "customer.email"),
            @Mapping(target = "address", source = "customer.address"),
            @Mapping(target = "createdAt", dateFormat = "yourDateFormat", source = "customer.createdAt"),
            @Mapping(target = "updatedAt", dateFormat = "yourDateFormat", source = "customer.updatedAt")
    })

    CustomerResponseDTO CUSTOMER_TO_CUSTOMER_RES_DTO(Customer customer);

    List<CustomerResponseDTO> CUSTOMERS_TO_CUSTOMER_RES_LIST_DTO(List<Customer> customerList);




}
