package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.SupplierProduct.SupplierProductRequestDTO;
import org.pgs.posback.DTO.SupplierProduct.SupplierProductResponseDTO;
import org.pgs.posback.model.SupplierProductModel;

@Mapper
public interface
SupplierProductMapper {
    SupplierProductMapper INSTANCE = Mappers.getMapper(SupplierProductMapper.class);

    @Mapping(target = "id", source = "supplierProductModel.id")
    @Mapping(target = "supplier", source = "supplierProductModel.supplier")
    @Mapping(target = "product", source = "supplierProductModel.product")
    @Mapping(target = "unitPrice", source = "supplierProductModel.unitPrice")
    SupplierProductResponseDTO supplierProductModelToSupplierProductResponseDTO(SupplierProductModel supplierProductModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "supplier", source = "supplierProductRequestDTO.supplier")
    @Mapping(target = "product", source = "supplierProductRequestDTO.product")
    @Mapping(target = "unitPrice", source = "supplierProductRequestDTO.unitPrice")
    SupplierProductModel supplierProductRequestDTOToSupplierProductModel(SupplierProductRequestDTO supplierProductRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "supplier", source = "supplierProductRequestDTO.supplier")
    @Mapping(target = "product", source = "supplierProductRequestDTO.product")
    @Mapping(target = "unitPrice", source = "supplierProductRequestDTO.unitPrice")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    SupplierProductModel updateSupplierProductFromRequestDTO(SupplierProductModel supplierProductModel, SupplierProductRequestDTO supplierProductRequestDTO);
}
