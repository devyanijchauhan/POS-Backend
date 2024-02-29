package org.pgs.posback.controller;

import org.pgs.posback.model.PurchaseOrderModel;
import org.pgs.posback.repository.AdminRepository;
import org.pgs.posback.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    public PurchaseOrderController(PurchaseOrderRepository purchaseOrderRepository){
        this.purchaseOrderRepository=purchaseOrderRepository;
    }

    @GetMapping("/all")
    public List<PurchaseOrderModel> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    @GetMapping("/{Id}")
    public PurchaseOrderModel getPurchaseOrderById(@PathVariable Long Id) {
        return purchaseOrderRepository.findById(Id).orElse(null);
    }

    @PostMapping
    public PurchaseOrderModel createPurchaseOrder(@RequestBody PurchaseOrderModel purchaseOrder) {
        purchaseOrder.setCreatedAt(new Date());
        purchaseOrder.setUpdatedAt(new Date());
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @PutMapping("/{orderId}")
    public PurchaseOrderModel updatePurchaseOrder(@PathVariable Long orderId, @RequestBody PurchaseOrderModel purchaseOrderDetails) {
        PurchaseOrderModel purchaseOrder = purchaseOrderRepository.findById(orderId).orElse(null);
        if (purchaseOrder != null) {
            purchaseOrder.setDate(purchaseOrderDetails.getDate());
            purchaseOrder.setSupplier(purchaseOrderDetails.getSupplier());
            purchaseOrder.setStatus(purchaseOrderDetails.getStatus());
            purchaseOrder.setStore(purchaseOrderDetails.getStore());
            purchaseOrder.setUpdatedAt(new Date());
            return purchaseOrderRepository.save(purchaseOrder);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{Ido}")
    public void deletePurchaseOrder(@PathVariable Long Ido) {
        PurchaseOrderModel purchaseOrder = purchaseOrderRepository.findById(Ido).orElse(null);
        if (purchaseOrder != null) {
            purchaseOrderRepository.delete(purchaseOrder);
        }
    }
}
