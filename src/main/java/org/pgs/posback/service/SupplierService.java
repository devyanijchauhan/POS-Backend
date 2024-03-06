package org.pgs.posback.service;

import org.pgs.posback.DTO.Supplier.SupplierRequestDTO;
import org.pgs.posback.DTO.Supplier.SupplierResponseDTO;

import java.util.List;

public interface SupplierService {
    List<SupplierResponseDTO> getAllSuppliers();

    SupplierResponseDTO getSupplierById(Long id);

    SupplierResponseDTO createSupplier(SupplierRequestDTO supplierRequestDTO);

    SupplierResponseDTO updateSupplier(Long supplierId, SupplierRequestDTO supplierRequestDTO);

    void deleteSupplier(Long supplierId);
}
