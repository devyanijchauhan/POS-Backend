package org.pgs.posback.controller;

import org.pgs.posback.DTO.Cashier.CashierRequestDTO;
import org.pgs.posback.DTO.Cashier.CashierResponseDTO;
import org.pgs.posback.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cashiers")
public class CashierController {

    private final CashierService cashierService;

    @Autowired
    public CashierController(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CashierResponseDTO>> getAllCashiers() {
        List<CashierResponseDTO> cashiers = cashierService.getAllCashiers();
        return ResponseEntity.ok(cashiers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CashierResponseDTO> getCashierById(@PathVariable Long id) {
        CashierResponseDTO cashier = cashierService.getCashierById(id);
        return cashier != null ? ResponseEntity.ok(cashier) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CashierResponseDTO> createCashier(@RequestBody CashierRequestDTO cashierRequestDTO) {
        CashierResponseDTO createdCashier = cashierService.createCashier(cashierRequestDTO);
        return new ResponseEntity<>(createdCashier, HttpStatus.CREATED);
    }

    @PutMapping("/{cashierId}")
    public ResponseEntity<CashierResponseDTO> updateCashier(@PathVariable Long cashierId, @RequestBody CashierRequestDTO cashierRequestDTO) {
        CashierResponseDTO updatedCashier = cashierService.updateCashier(cashierId, cashierRequestDTO);
        return updatedCashier != null ? ResponseEntity.ok(updatedCashier) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{Idc}")
    public ResponseEntity<Void> deleteCashier(@PathVariable Long Idc) {
        cashierService.deleteCashier(Idc);
        return ResponseEntity.noContent().build();
    }
}
