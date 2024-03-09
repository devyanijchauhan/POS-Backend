package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.PurchaseOrder.PurchaseOrderRequestDTO;
import org.pgs.posback.DTO.PurchaseOrder.PurchaseOrderResponseDTO;
import org.pgs.posback.model.PurchaseOrderModel;

@Mapper
public interface PurchaseOrderMapper {
    PurchaseOrderMapper INSTANCE = Mappers.getMapper(PurchaseOrderMapper.class);

    @Mapping(target = "id", source = "purchaseOrderModel.id")
    @Mapping(target = "date", source = "purchaseOrderModel.date")
    @Mapping(target = "supplier", source = "purchaseOrderModel.supplier")
    @Mapping(target = "status", source = "purchaseOrderModel.status")
    @Mapping(target = "store", source = "purchaseOrderModel.store")
    @Mapping(target = "createdAt", source = "purchaseOrderModel.createdAt")
    @Mapping(target = "updatedAt", source = "purchaseOrderModel.updatedAt")
    PurchaseOrderResponseDTO purchaseOrderModelToPurchaseOrderResponseDTO(PurchaseOrderModel purchaseOrderModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    PurchaseOrderModel purchaseOrderRequestDTOToPurchaseOrderModel(PurchaseOrderRequestDTO purchaseOrderRequestDTO);
}