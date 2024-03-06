package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Supplier.SupplierRequestDTO;
import org.pgs.posback.DTO.Supplier.SupplierResponseDTO;
import org.pgs.posback.mapper.SupplierMapper;
import org.pgs.posback.model.SupplierModel;
import org.pgs.posback.repository.SupplierRepository;
import org.pgs.posback.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierResponseDTO> getAllSuppliers() {
        List<SupplierModel> suppliers = supplierRepository.findAll();
        return suppliers.stream()
                .map(SupplierMapper.INSTANCE::supplierModelToSupplierResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierResponseDTO getSupplierById(Long id) {
        SupplierModel supplier = supplierRepository.findById(id).orElse(null);
        if (supplier != null) {
            return SupplierMapper.INSTANCE.supplierModelToSupplierResponseDTO(supplier);
        }
        return null;
    }

    @Override
    public SupplierResponseDTO createSupplier(SupplierRequestDTO supplierRequestDTO) {
        SupplierModel supplier = SupplierMapper.INSTANCE.supplierRequestDTOToSupplierModel(supplierRequestDTO);
        SupplierModel savedSupplier = supplierRepository.save(supplier);
        return SupplierMapper.INSTANCE.supplierModelToSupplierResponseDTO(savedSupplier);
    }

    @Override
    public SupplierResponseDTO updateSupplier(Long supplierId, SupplierRequestDTO supplierRequestDTO) {
        // Implement the update logic here
        return null;
    }

    @Override
    public void deleteSupplier(Long supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}
