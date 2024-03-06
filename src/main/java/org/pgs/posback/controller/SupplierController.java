package org.pgs.posback.controller;

import org.pgs.posback.DTO.Supplier.SupplierRequestDTO;
import org.pgs.posback.DTO.Supplier.SupplierResponseDTO;
import org.pgs.posback.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {
        List<SupplierResponseDTO> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplierById(@PathVariable Long id) {
        SupplierResponseDTO supplier = supplierService.getSupplierById(id);
        return supplier != null ? ResponseEntity.ok(supplier) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> createSupplier(@RequestBody SupplierRequestDTO supplierRequestDTO) {
        SupplierResponseDTO createdSupplier = supplierService.createSupplier(supplierRequestDTO);
        return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(@PathVariable Long supplierId, @RequestBody SupplierRequestDTO supplierRequestDTO) {
        SupplierResponseDTO updatedSupplier = supplierService.updateSupplier(supplierId, supplierRequestDTO);
        return updatedSupplier != null ? ResponseEntity.ok(updatedSupplier) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{Ids}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long Ids) {
        supplierService.deleteSupplier(Ids);
        return ResponseEntity.noContent().build();
    }
}
