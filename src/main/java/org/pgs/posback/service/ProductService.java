package org.pgs.posback.service;

import org.pgs.posback.DTO.Product.ProductRequestDTO;
import org.pgs.posback.DTO.Product.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProductById(Long id);

    ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO, Long id);

    void deleteProduct(Long id);
}
