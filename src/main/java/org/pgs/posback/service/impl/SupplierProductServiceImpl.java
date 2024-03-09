package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.SupplierProduct.SupplierProductRequestDTO;
import org.pgs.posback.DTO.SupplierProduct.SupplierProductResponseDTO;
import org.pgs.posback.mapper.SupplierProductMapper;
import org.pgs.posback.model.SupplierProductModel;
import org.pgs.posback.repository.SupplierProductRepository;
import org.pgs.posback.service.SupplierProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierProductServiceImpl implements SupplierProductService {

    private final SupplierProductRepository supplierProductRepository;

    @Autowired
    public SupplierProductServiceImpl(SupplierProductRepository supplierProductRepository) {
        this.supplierProductRepository = supplierProductRepository;
    }

    @Override
    public List<SupplierProductResponseDTO> getAllSupplierProducts() {
        return supplierProductRepository.findAll().stream()
                .map(SupplierProductMapper.INSTANCE::supplierProductModelToSupplierProductResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierProductResponseDTO getSupplierProductById(Long id) {
        return supplierProductRepository.findById(id)
                .map(SupplierProductMapper.INSTANCE::supplierProductModelToSupplierProductResponseDTO)
                .orElse(null);
    }

    @Override
    public SupplierProductResponseDTO createSupplierProduct(SupplierProductRequestDTO supplierProductRequestDTO) {
        SupplierProductModel supplierProduct = SupplierProductMapper.INSTANCE.supplierProductRequestDTOToSupplierProductModel(supplierProductRequestDTO);
        SupplierProductModel savedSupplierProduct = supplierProductRepository.save(supplierProduct);
        return SupplierProductMapper.INSTANCE.supplierProductModelToSupplierProductResponseDTO(savedSupplierProduct);
    }

    @Override
    public SupplierProductResponseDTO updateSupplierProduct(Long supplierProductId, SupplierProductRequestDTO supplierProductRequestDTO) {
        return supplierProductRepository.findById(supplierProductId)
                .map(supplierProduct -> {
                    SupplierProductModel updatedSupplierProduct = SupplierProductMapper.INSTANCE.updateSupplierProductFromRequestDTO(supplierProduct, supplierProductRequestDTO);
                    return SupplierProductMapper.INSTANCE.supplierProductModelToSupplierProductResponseDTO(supplierProductRepository.save(updatedSupplierProduct));
                })
                .orElse(null);
    }

    @Override
    public void deleteSupplierProduct(Long id) {
        supplierProductRepository.deleteById(id);
    }
}
