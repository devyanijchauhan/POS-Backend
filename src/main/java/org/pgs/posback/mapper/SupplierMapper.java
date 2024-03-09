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


    @Mapping(target = "id", source = "supplierModel.id")
    @Mapping(target = "name", source = "supplierModel.name")
    @Mapping(target = "contactNumber", source = "supplierModel.contactNumber")
    SupplierResponseDTO supplierModelToSupplierResponseDTO(SupplierModel supplierModel);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    SupplierModel supplierRequestDTOToSupplierModel(SupplierRequestDTO supplierRequestDTO);
}
