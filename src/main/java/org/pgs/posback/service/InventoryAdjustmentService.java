package org.pgs.posback.service;


import org.pgs.posback.DTO.InventoryAdjustment.InventoryAdjustmentRequestDTO;
import org.pgs.posback.DTO.InventoryAdjustment.InventoryAdjustmentResponseDTO;

import java.util.List;

public interface InventoryAdjustmentService {
    List<InventoryAdjustmentResponseDTO> getAllInventoryAdjustments();
    InventoryAdjustmentResponseDTO getInventoryAdjustmentById(Long id);
    InventoryAdjustmentResponseDTO createInventoryAdjustment(InventoryAdjustmentRequestDTO inventoryAdjustmentRequestDTO);
    InventoryAdjustmentResponseDTO updateInventoryAdjustment(Long adjustmentId, InventoryAdjustmentRequestDTO inventoryAdjustmentRequestDTO);
    void deleteInventoryAdjustment(Long Id);
}