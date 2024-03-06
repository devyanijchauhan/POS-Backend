package org.pgs.posback.controller;

import org.pgs.posback.DTO.Sale.SaleRequestDTO;
import org.pgs.posback.DTO.Sale.SaleResponseDTO;
import org.pgs.posback.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SaleResponseDTO>> getAllSales() {
        List<SaleResponseDTO> sales = saleService.getAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getSaleById(@PathVariable Long id) {
        SaleResponseDTO sale = saleService.getSaleById(id);
        if (sale != null) {
            return new ResponseEntity<>(sale, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<SaleResponseDTO> createSale(@RequestBody SaleRequestDTO saleRequestDTO) {
        SaleResponseDTO createdSale = saleService.createSale(saleRequestDTO);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }

    @PutMapping("/{saleId}")
    public ResponseEntity<SaleResponseDTO> updateSale(@PathVariable Long saleId, @RequestBody SaleRequestDTO saleRequestDTO) {
        SaleResponseDTO updatedSale = saleService.updateSale(saleId, saleRequestDTO);
        if (updatedSale != null) {
            return new ResponseEntity<>(updatedSale, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{Ids}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long Ids) {
        saleService.deleteSale(Ids);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
