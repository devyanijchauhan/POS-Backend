package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.Supplier.SupplierRequestDTO;
import org.pgs.posback.DTO.Supplier.SupplierResponseDTO;
import org.pgs.posback.model.SupplierModel;

@Mapper
public interface SupplierMapper {
    SupplierMapper INSTANCE = Mappers.getMapper(SupplierMapper.class);

    SupplierResponseDTO supplierModelToSupplierResponseDTO(SupplierModel supplierModel);

    SupplierModel supplierRequestDTOToSupplierModel(SupplierRequestDTO supplierRequestDTO);
}
