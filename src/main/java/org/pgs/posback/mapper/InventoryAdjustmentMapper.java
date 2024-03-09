package org.pgs.posback.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.pgs.posback.DTO.InventoryAdjustment.InventoryAdjustmentRequestDTO;
import org.pgs.posback.DTO.InventoryAdjustment.InventoryAdjustmentResponseDTO;
import org.pgs.posback.model.InventoryAdjustmentModel;

@Mapper
public interface InventoryAdjustmentMapper {
    InventoryAdjustmentMapper INSTANCE = Mappers.getMapper(InventoryAdjustmentMapper.class);

    InventoryAdjustmentResponseDTO inventoryAdjustmentModelToResponseDTO(InventoryAdjustmentModel inventoryAdjustmentModel);

    InventoryAdjustmentModel inventoryAdjustmentRequestDTOToModel(InventoryAdjustmentRequestDTO inventoryAdjustmentRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateInventoryAdjustmentFromRequestDTO(InventoryAdjustmentRequestDTO inventoryAdjustmentRequestDTO, @MappingTarget InventoryAdjustmentModel inventoryAdjustmentModel);
}