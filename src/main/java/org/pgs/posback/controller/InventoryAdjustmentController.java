package org.pgs.posback.controller;

import org.pgs.posback.DTO.InventoryAdjustment.InventoryAdjustmentRequestDTO;
import org.pgs.posback.DTO.InventoryAdjustment.InventoryAdjustmentResponseDTO;
import org.pgs.posback.service.InventoryAdjustmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory-adjustments")
public class InventoryAdjustmentController {

    private final InventoryAdjustmentService inventoryAdjustmentService;

    @Autowired
    public InventoryAdjustmentController(InventoryAdjustmentService inventoryAdjustmentService) {
        this.inventoryAdjustmentService = inventoryAdjustmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<InventoryAdjustmentResponseDTO>> getAllInventoryAdjustments() {
        List<InventoryAdjustmentResponseDTO> adjustments = inventoryAdjustmentService.getAllInventoryAdjustments();
        return ResponseEntity.ok(adjustments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryAdjustmentResponseDTO> getInventoryAdjustmentById(@PathVariable Long id) {
        InventoryAdjustmentResponseDTO adjustment = inventoryAdjustmentService.getInventoryAdjustmentById(id);
        return adjustment != null ? ResponseEntity.ok(adjustment) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<InventoryAdjustmentResponseDTO> createInventoryAdjustment(@RequestBody InventoryAdjustmentRequestDTO inventoryAdjustmentRequestDTO) {
        InventoryAdjustmentResponseDTO createdAdjustment = inventoryAdjustmentService.createInventoryAdjustment(inventoryAdjustmentRequestDTO);
        return new ResponseEntity<>(createdAdjustment, HttpStatus.CREATED);
    }

    @PutMapping("/{adjustmentId}")
    public ResponseEntity<InventoryAdjustmentResponseDTO> updateInventoryAdjustment(@PathVariable Long adjustmentId, @RequestBody InventoryAdjustmentRequestDTO inventoryAdjustmentRequestDTO) {
        InventoryAdjustmentResponseDTO updatedAdjustment = inventoryAdjustmentService.updateInventoryAdjustment(adjustmentId, inventoryAdjustmentRequestDTO);
        return updatedAdjustment != null ? ResponseEntity.ok(updatedAdjustment) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{Idi}")
    public ResponseEntity<Void> deleteInventoryAdjustment(@PathVariable Long Idi) {
        inventoryAdjustmentService.deleteInventoryAdjustment(Idi);
        return ResponseEntity.noContent().build();
    }
}