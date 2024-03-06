package org.pgs.posback.controller;

import org.pgs.posback.DTO.Barcode.BarcodeRequestDTO;
import org.pgs.posback.DTO.Barcode.BarcodeResponseDTO;
import org.pgs.posback.service.BarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barcodes")
public class BarcodeController {

    private final BarcodeService barcodeService;

    @Autowired
    public BarcodeController(BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BarcodeResponseDTO>> getAllBarcodes() {
        List<BarcodeResponseDTO> barcodes = barcodeService.getAllBarcodes();
        return ResponseEntity.ok(barcodes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarcodeResponseDTO> getBarcodeById(@PathVariable Long id) {
        BarcodeResponseDTO barcode = barcodeService.getBarcodeById(id);
        return barcode != null ? ResponseEntity.ok(barcode) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BarcodeResponseDTO> createBarcode(@RequestBody BarcodeRequestDTO barcodeRequestDTO) {
        BarcodeResponseDTO createdBarcode = barcodeService.createBarcode(barcodeRequestDTO);
        return new ResponseEntity<>(createdBarcode, HttpStatus.CREATED);
    }

    @PutMapping("/{barcodeId}")
    public ResponseEntity<BarcodeResponseDTO> updateBarcode(@PathVariable Long barcodeId, @RequestBody BarcodeRequestDTO barcodeRequestDTO) {
        BarcodeResponseDTO updatedBarcode = barcodeService.updateBarcode(barcodeId, barcodeRequestDTO);
        return updatedBarcode != null ? ResponseEntity.ok(updatedBarcode) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{Idb}")
    public ResponseEntity<Void> deleteBarcode(@PathVariable Long Idb) {
        barcodeService.deleteBarcode(Idb);
        return ResponseEntity.noContent().build();
    }
}
