package org.pgs.posback.controller;

import org.pgs.posback.DTO.Product.ProductRequestDTO;
import org.pgs.posback.DTO.Product.ProductResponseDTO;
import org.pgs.posback.mapper.ProductMapper;
import org.pgs.posback.model.ProductModel;
import org.pgs.posback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        ProductResponseDTO product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO createdProduct = productService.createProduct(productRequestDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{ids}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long ids, @RequestBody ProductRequestDTO productRequestDTO) {
        ProductResponseDTO updatedProduct = productService.updateProduct(productRequestDTO, ids);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{idp}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long idp) {
        productService.deleteProduct(idp);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
