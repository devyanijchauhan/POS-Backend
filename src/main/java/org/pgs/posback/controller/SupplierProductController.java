package org.pgs.posback.controller;

import org.pgs.posback.model.SupplierProductModel;
import org.pgs.posback.repository.AdminRepository;
import org.pgs.posback.repository.SupplierProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier-products")
public class SupplierProductController {

    private SupplierProductRepository supplierProductRepository;

    @Autowired
    public SupplierProductController(SupplierProductRepository supplierProductRepository){
        this.supplierProductRepository=supplierProductRepository;
    }

    @GetMapping("/all")
    public List<SupplierProductModel> getAllSupplierProducts() {
        return supplierProductRepository.findAll();
    }

    @GetMapping("/{id}")
    public SupplierProductModel getSupplierProductById(@PathVariable Long id) {
        return supplierProductRepository.findById(id).orElse(null);
    }

    @PostMapping
    public SupplierProductModel createSupplierProduct(@RequestBody SupplierProductModel supplierProduct) {
        return supplierProductRepository.save(supplierProduct);
    }

    @PutMapping("/{spid}")
    public SupplierProductModel updateSupplierProduct(@PathVariable Long spid, @RequestBody SupplierProductModel supplierProductDetails) {
        SupplierProductModel supplierProduct = supplierProductRepository.findById(spid).orElse(null);
        if (supplierProduct != null) {
            supplierProduct.setSupplier(supplierProductDetails.getSupplier());
            supplierProduct.setProduct(supplierProductDetails.getProduct());
            supplierProduct.setUnitPrice(supplierProductDetails.getUnitPrice());
            return supplierProductRepository.save(supplierProduct);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{idsp}")
    public void deleteSupplierProduct(@PathVariable Long idsp) {
        supplierProductRepository.deleteById(idsp);
    }
}
