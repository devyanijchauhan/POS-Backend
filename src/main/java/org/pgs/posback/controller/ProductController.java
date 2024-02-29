package org.pgs.posback.controller;

import org.pgs.posback.model.ProductModel;
import org.pgs.posback.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{Id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable Long Id) {
        Optional<ProductModel> productData = productRepository.findById(Id);
        return productData.map(productModel -> new ResponseEntity<>(productModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel productModel) {
        try {
            productModel.setCreatedAt(new Date());
            productModel.setUpdatedAt(new Date());
            ProductModel createdProduct = productRepository.save(productModel);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable Long productId, @RequestBody ProductModel productModel) {
        Optional<ProductModel> productData = productRepository.findById(productId);

        if (productData.isPresent()) {
            ProductModel updatedProduct = productData.get();
            updatedProduct.setName(productModel.getName());
            updatedProduct.setDescription(productModel.getDescription());
            updatedProduct.setBuyingPrice(productModel.getBuyingPrice());
            updatedProduct.setSellingPrice(productModel.getSellingPrice());
            updatedProduct.setQuantityInStock(productModel.getQuantityInStock());
            updatedProduct.setReorderLevel(productModel.getReorderLevel());
            updatedProduct.setCategory(productModel.getCategory());
            // Set other fields as needed
            updatedProduct.setUpdatedAt(new Date());
            return new ResponseEntity<>(productRepository.save(updatedProduct), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{Idp}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long Idp) {
        try {
            productRepository.deleteById(Idp);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
