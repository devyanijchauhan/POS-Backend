package org.pgs.posback.controller;

import org.pgs.posback.model.SupplierModel;
import org.pgs.posback.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private SupplierRepository supplierRepository;

    @Autowired
    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @GetMapping("/all")
    public List<SupplierModel> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierModel> getSupplierById(@PathVariable("id") Long id) {
        Optional<SupplierModel> supplierData = supplierRepository.findById(id);
        return supplierData.map(supplierModel -> new ResponseEntity<>(supplierModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<SupplierModel> createSupplier(@RequestBody SupplierModel supplierModel) {
        try {
            SupplierModel createdSupplier = supplierRepository.save(supplierModel);
            return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{supplierid}")
    public ResponseEntity<SupplierModel> updateSupplier(@PathVariable("supplierid") Long supplierid, @RequestBody SupplierModel supplierModel) {
        Optional<SupplierModel> supplierData = supplierRepository.findById(supplierid);

        if (supplierData.isPresent()) {
            SupplierModel updatedSupplier = supplierData.get();
            updatedSupplier.setName(supplierModel.getName());
            updatedSupplier.setContactInformation(supplierModel.getContactInformation());
            updatedSupplier.setCreatedAt(supplierModel.getCreatedAt());
            updatedSupplier.setUpdatedAt(supplierModel.getUpdatedAt());
            return new ResponseEntity<>(supplierRepository.save(updatedSupplier), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<HttpStatus> deleteSupplier(@PathVariable("ids") Long ids) {
        try {
            supplierRepository.deleteById(ids);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
