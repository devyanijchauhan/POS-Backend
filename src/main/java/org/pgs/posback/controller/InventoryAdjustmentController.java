package org.pgs.posback.controller;

import org.pgs.posback.model.InventoryAdjustmentModel;
import org.pgs.posback.repository.EmployeeRepository;
import org.pgs.posback.repository.InventoryAdjustmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/inventory-adjustments")
public class InventoryAdjustmentController {
    private InventoryAdjustmentRepository inventoryAdjustmentRepository;

    @Autowired
    public InventoryAdjustmentController(InventoryAdjustmentRepository inventoryAdjustmentRepository){
        this.inventoryAdjustmentRepository=inventoryAdjustmentRepository;
    }

    @GetMapping("/all")
    public List<InventoryAdjustmentModel> getAllInventoryAdjustments() {
        return inventoryAdjustmentRepository.findAll();
    }

    @GetMapping("/{Id}")
    public InventoryAdjustmentModel getInventoryAdjustmentById(@PathVariable Long Id) {
        return inventoryAdjustmentRepository.findById(Id).orElse(null);
    }

    @PostMapping
    public InventoryAdjustmentModel createInventoryAdjustment(@RequestBody InventoryAdjustmentModel inventoryAdjustment) {
        inventoryAdjustment.setCreatedAt(new Date());
        inventoryAdjustment.setUpdatedAt(new Date());
        return inventoryAdjustmentRepository.save(inventoryAdjustment);
    }

    @PutMapping("/{adjustmentId}")
    public InventoryAdjustmentModel updateInventoryAdjustment(@PathVariable Long adjustmentId, @RequestBody InventoryAdjustmentModel inventoryAdjustmentDetails) {
        InventoryAdjustmentModel inventoryAdjustment = inventoryAdjustmentRepository.findById(adjustmentId).orElse(null);
        if (inventoryAdjustment != null) {
            inventoryAdjustment.setProduct(inventoryAdjustmentDetails.getProduct());
            inventoryAdjustment.setStore(inventoryAdjustmentDetails.getStore());
            inventoryAdjustment.setAdjustedQuantity(inventoryAdjustmentDetails.getAdjustedQuantity());
            inventoryAdjustment.setReason(inventoryAdjustmentDetails.getReason());
            inventoryAdjustment.setAdjustmentDate(inventoryAdjustmentDetails.getAdjustmentDate());
            inventoryAdjustment.setUpdatedAt(new Date());
            return inventoryAdjustmentRepository.save(inventoryAdjustment);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{Idi}")
    public void deleteInventoryAdjustment(@PathVariable Long Idi) {
        InventoryAdjustmentModel inventoryAdjustment = inventoryAdjustmentRepository.findById(Idi).orElse(null);
        if (inventoryAdjustment != null) {
            inventoryAdjustmentRepository.delete(inventoryAdjustment);
        }
    }
}
