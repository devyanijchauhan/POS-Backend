package org.pgs.posback.controller;

import org.pgs.posback.DTO.PurchaseOrder.PurchaseOrderRequestDTO;
import org.pgs.posback.DTO.PurchaseOrder.PurchaseOrderResponseDTO;
import org.pgs.posback.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @Autowired
    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PurchaseOrderResponseDTO>> getAllPurchaseOrders() {
        List<PurchaseOrderResponseDTO> purchaseOrders = purchaseOrderService.getAllPurchaseOrders();
        return new ResponseEntity<>(purchaseOrders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderResponseDTO> getPurchaseOrderById(@PathVariable Long id) {
        PurchaseOrderResponseDTO purchaseOrder = purchaseOrderService.getPurchaseOrderById(id);
        if (purchaseOrder != null) {
            return new ResponseEntity<>(purchaseOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PurchaseOrderResponseDTO> createPurchaseOrder(@RequestBody PurchaseOrderRequestDTO purchaseOrderRequestDTO) {
        PurchaseOrderResponseDTO createdPurchaseOrder = purchaseOrderService.createPurchaseOrder(purchaseOrderRequestDTO);
        return new ResponseEntity<>(createdPurchaseOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<PurchaseOrderResponseDTO> updatePurchaseOrder(@PathVariable Long orderId, @RequestBody PurchaseOrderRequestDTO purchaseOrderRequestDTO) {
        PurchaseOrderResponseDTO updatedPurchaseOrder = purchaseOrderService.updatePurchaseOrder(orderId, purchaseOrderRequestDTO);
        if (updatedPurchaseOrder != null) {
            return new ResponseEntity<>(updatedPurchaseOrder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{purchaseorderId}")
    public ResponseEntity<HttpStatus> deletePurchaseOrder(@PathVariable Long purchaseorderId) {
        purchaseOrderService.deletePurchaseOrder(purchaseorderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}