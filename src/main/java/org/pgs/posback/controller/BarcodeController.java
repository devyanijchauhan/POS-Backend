package org.pgs.posback.controller;

import org.pgs.posback.model.BarcodeModel;
import org.pgs.posback.repository.BarcodeRepository;
import org.pgs.posback.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/barcodes")
public class BarcodeController {

    private BarcodeRepository barcodeRepository;

    @Autowired
    public BarcodeController(BarcodeRepository barcodeRepository){
        this.barcodeRepository=barcodeRepository;
    }

    @GetMapping("/all")
    public List<BarcodeModel> getAllBarcodes() {
        return barcodeRepository.findAll();
    }

    @GetMapping("/{Id}")
    public BarcodeModel getBarcodeById(@PathVariable Long Id) {
        return barcodeRepository.findById(Id).orElse(null);
    }

    @PostMapping
    public BarcodeModel createBarcode(@RequestBody BarcodeModel barcode) {
        barcode.setCreatedAt(new Date());
        barcode.setUpdatedAt(new Date());
        return barcodeRepository.save(barcode);
    }

    @PutMapping("/{barcodeId}")
    public BarcodeModel updateBarcode(@PathVariable Long barcodeId, @RequestBody BarcodeModel barcodeDetails) {
        BarcodeModel barcode = barcodeRepository.findById(barcodeId).orElse(null);
        if (barcode != null) {
            barcode.setProduct(barcodeDetails.getProduct());
            barcode.setBarcodeImage(barcodeDetails.getBarcodeImage());
            barcode.setUpdatedAt(new Date());
            return barcodeRepository.save(barcode);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{barcodesId}")
    public void deleteBarcode(@PathVariable Long barcodesId) {
        BarcodeModel barcode = barcodeRepository.findById(barcodesId).orElse(null);
        if (barcode != null) {
            barcodeRepository.delete(barcode);
        }
    }
}
