package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.PurchaseOrder.PurchaseOrderRequestDTO;
import org.pgs.posback.DTO.PurchaseOrder.PurchaseOrderResponseDTO;
import org.pgs.posback.mapper.PurchaseOrderMapper;
import org.pgs.posback.model.PurchaseOrderModel;
import org.pgs.posback.repository.PurchaseOrderRepository;
import org.pgs.posback.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Override
    public List<PurchaseOrderResponseDTO> getAllPurchaseOrders() {
        List<PurchaseOrderModel> purchaseOrders = purchaseOrderRepository.findAll();
        return purchaseOrders.stream()
                .map(PurchaseOrderMapper.INSTANCE::purchaseOrderModelToPurchaseOrderResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseOrderResponseDTO getPurchaseOrderById(Long id) {
        Optional<PurchaseOrderModel> optionalPurchaseOrder = purchaseOrderRepository.findById(id);
        return optionalPurchaseOrder.map(PurchaseOrderMapper.INSTANCE::purchaseOrderModelToPurchaseOrderResponseDTO).orElse(null);
    }

    @Override
    public PurchaseOrderResponseDTO createPurchaseOrder(PurchaseOrderRequestDTO purchaseOrderRequestDTO) {
        PurchaseOrderModel purchaseOrder = PurchaseOrderMapper.INSTANCE.purchaseOrderRequestDTOToPurchaseOrderModel(purchaseOrderRequestDTO);
        purchaseOrder.setCreatedAt(new Date());
        purchaseOrder.setUpdatedAt(new Date());
        PurchaseOrderModel savedPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        return PurchaseOrderMapper.INSTANCE.purchaseOrderModelToPurchaseOrderResponseDTO(savedPurchaseOrder);
    }

    @Override
    public PurchaseOrderResponseDTO updatePurchaseOrder(Long orderId, PurchaseOrderRequestDTO purchaseOrderRequestDTO) {
        Optional<PurchaseOrderModel> optionalPurchaseOrder = purchaseOrderRepository.findById(orderId);
        if (optionalPurchaseOrder.isPresent()) {
            PurchaseOrderModel purchaseOrder = optionalPurchaseOrder.get();
            purchaseOrder.setDate(purchaseOrderRequestDTO.getDate());
            purchaseOrder.setSupplier(purchaseOrderRequestDTO.getSupplier());
            purchaseOrder.setStatus(purchaseOrderRequestDTO.getStatus());
            purchaseOrder.setStore(purchaseOrderRequestDTO.getStore());
            purchaseOrder.setUpdatedAt(new Date());
            PurchaseOrderModel updatedPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
            return PurchaseOrderMapper.INSTANCE.purchaseOrderModelToPurchaseOrderResponseDTO(updatedPurchaseOrder);
        } else {
            return null;
        }
    }

    @Override
    public void deletePurchaseOrder(Long orderId) {
        purchaseOrderRepository.deleteById(orderId);
    }
}