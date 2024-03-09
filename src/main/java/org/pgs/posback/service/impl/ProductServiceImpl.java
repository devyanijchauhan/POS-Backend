package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Product.ProductRequestDTO;
import org.pgs.posback.DTO.Product.ProductResponseDTO;
import org.pgs.posback.mapper.ProductMapper;
import org.pgs.posback.model.ProductModel;
import org.pgs.posback.repository.ProductRepository;
import org.pgs.posback.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        ProductModel productModel = ProductMapper.INSTANCE.productRequestDTOToProductModel(productRequestDTO);
        productModel.setCreatedAt(new Date());
        productModel.setUpdatedAt(new Date());
        return ProductMapper.INSTANCE.productModelToProductResponseDTO(productRepository.save(productModel));
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<ProductModel> productModels = productRepository.findAll();
        return ProductMapper.INSTANCE.productModelsToProductResponseDTOList(productModels);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Optional<ProductModel> productModelOptional = productRepository.findById(id);
        if (productModelOptional.isPresent()) {
            return ProductMapper.INSTANCE.productModelToProductResponseDTO(productModelOptional.get());
        } else {
            throw new RuntimeException("Product with id " + id + " not found");
        }
    }

    @Override
    public ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO, Long id) {
        Optional<ProductModel> productModelOptional = productRepository.findById(id);
        if (productModelOptional.isPresent()) {
            ProductModel productModel = productModelOptional.get();
            productModel.setName(productRequestDTO.getName());
            productModel.setDescription(productRequestDTO.getDescription());
            productModel.setBuyingPrice(productRequestDTO.getBuyingPrice());
            productModel.setSellingPrice(productRequestDTO.getSellingPrice());
            productModel.setQuantityInStock(productRequestDTO.getQuantityInStock());
            productModel.setReorderLevel(productRequestDTO.getReorderLevel());
            productModel.setCategory(productRequestDTO.getCategory());
            productModel.setUpdatedAt(new Date());
            return ProductMapper.INSTANCE.productModelToProductResponseDTO(productRepository.save(productModel));
        } else {
            throw new RuntimeException("Product with id " + id + " not found");
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<ProductModel> productModelOptional = productRepository.findById(id);
        if (productModelOptional.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product with id " + id + " not found");
        }
    }
}
