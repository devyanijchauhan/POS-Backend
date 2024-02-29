package org.pgs.posback.controller;

import org.pgs.posback.model.SaleModel;
import org.pgs.posback.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private SaleRepository saleRepository;

    @Autowired
    public SaleController(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @GetMapping("/all")
    public List<SaleModel> getAllSales() {
        return saleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleModel> getSaleById(@PathVariable("id") Long id) {
        Optional<SaleModel> saleData = saleRepository.findById(id);
        return saleData.map(saleModel -> new ResponseEntity<>(saleModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<SaleModel> createSale(@RequestBody SaleModel saleModel) {
        try {
            SaleModel createdSale = saleRepository.save(saleModel);
            return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{saleid}")
    public ResponseEntity<SaleModel> updateSale(@PathVariable("saleid") Long saleid, @RequestBody SaleModel saleModel) {
        Optional<SaleModel> saleData = saleRepository.findById(saleid);

        if (saleData.isPresent()) {
            SaleModel updatedSale = saleData.get();
            // Update the sale details here
            return new ResponseEntity<>(saleRepository.save(updatedSale), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<HttpStatus> deleteSale(@PathVariable("ids") Long ids) {
        try {
            saleRepository.deleteById(ids);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
