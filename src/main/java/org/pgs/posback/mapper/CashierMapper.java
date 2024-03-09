package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.Cashier.CashierRequestDTO;
import org.pgs.posback.DTO.Cashier.CashierResponseDTO;
import org.pgs.posback.model.CashierModel;

@Mapper
public interface CashierMapper {

    CashierMapper INSTANCE = Mappers.getMapper(CashierMapper.class);

    @Mapping(target = "id", source = "cashierModel.id")
    @Mapping(target = "account", source = "cashierModel.account")
    @Mapping(target = "name", source = "cashierModel.name")
    @Mapping(target = "role", source = "cashierModel.role")
    @Mapping(target = "manager", source = "cashierModel.manager")
    @Mapping(target = "store", source = "cashierModel.store")
    @Mapping(target = "createdAt", source = "cashierModel.createdAt")
    @Mapping(target = "updatedAt", source = "cashierModel.updatedAt")
    CashierResponseDTO cashierModelToCashierResponseDTO(CashierModel cashierModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", source = "cashierRequestDTO.account")
    @Mapping(target = "name", source = "cashierRequestDTO.name")
    @Mapping(target = "role", constant = "cashier")
    @Mapping(target = "manager", source = "cashierRequestDTO.manager")
    @Mapping(target = "store", source = "cashierRequestDTO.manager")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CashierModel cashierRequestDTOToCashierModel(CashierRequestDTO cashierRequestDTO);
}

