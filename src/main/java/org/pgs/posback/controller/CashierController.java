package org.pgs.posback.controller;

import org.pgs.posback.model.CashierModel;
import org.pgs.posback.repository.CashierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cashiers")
public class CashierController {

    private CashierRepository cashierRepository;

    @Autowired
    public CashierController(CashierRepository cashierRepository) {
        this.cashierRepository = cashierRepository;
    }

    @GetMapping("/all")
    public List<CashierModel> getAllCashiers() {
        return cashierRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CashierModel> getCashierById(@PathVariable("id") Long id) {
        Optional<CashierModel> cashierData = cashierRepository.findById(id);
        return cashierData.map(cashierModel -> new ResponseEntity<>(cashierModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CashierModel> createCashier(@RequestBody CashierModel cashierModel) {
        try {
            CashierModel createdCashier = cashierRepository.save(cashierModel);
            return new ResponseEntity<>(createdCashier, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{cashierid}")
    public ResponseEntity<CashierModel> updateCashier(@PathVariable("cashierid") Long cashierid, @RequestBody CashierModel cashierModel) {
        Optional<CashierModel> cashierData = cashierRepository.findById(cashierid);

        if (cashierData.isPresent()) {
            CashierModel updatedCashier = cashierData.get();
            updatedCashier.setName(cashierModel.getName());
            updatedCashier.setRole(cashierModel.getRole());

            // Assuming manager ID is stored in the manager object of CashierModel
            // If it's not, adjust accordingly based on your data model
            if (cashierModel.getManager() != null) {
                updatedCashier.setManager(cashierModel.getManager());
            }

            updatedCashier.setCreatedAt(cashierModel.getCreatedAt());
            updatedCashier.setUpdatedAt(new Date());
            return new ResponseEntity<>(cashierRepository.save(updatedCashier), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<HttpStatus> deleteCashier(@PathVariable("ids") Long ids) {
        try {
            cashierRepository.deleteById(ids);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
