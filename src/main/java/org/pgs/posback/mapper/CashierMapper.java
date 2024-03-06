package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.Cashier.CashierRequestDTO;
import org.pgs.posback.DTO.Cashier.CashierResponseDTO;
import org.pgs.posback.model.CashierModel;

@Mapper
public interface CashierMapper {

    CashierMapper INSTANCE = Mappers.getMapper(CashierMapper.class);

    CashierResponseDTO cashierModelToCashierResponseDTO(CashierModel cashierModel);

    CashierModel cashierRequestDTOToCashierModel(CashierRequestDTO cashierRequestDTO);
}
