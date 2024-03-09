package org.pgs.posback.service;

import org.pgs.posback.DTO.PurchaseOrder.PurchaseOrderRequestDTO;
import org.pgs.posback.DTO.PurchaseOrder.PurchaseOrderResponseDTO;

import java.util.List;

public interface PurchaseOrderService {
    List<PurchaseOrderResponseDTO> getAllPurchaseOrders();

    PurchaseOrderResponseDTO getPurchaseOrderById(Long id);

    PurchaseOrderResponseDTO createPurchaseOrder(PurchaseOrderRequestDTO purchaseOrderRequestDTO);

    PurchaseOrderResponseDTO updatePurchaseOrder(Long orderId, PurchaseOrderRequestDTO purchaseOrderRequestDTO);

    void deletePurchaseOrder(Long orderId);
}