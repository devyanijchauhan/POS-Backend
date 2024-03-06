package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Cashier.CashierRequestDTO;
import org.pgs.posback.DTO.Cashier.CashierResponseDTO;
import org.pgs.posback.mapper.CashierMapper;
import org.pgs.posback.model.CashierModel;
import org.pgs.posback.repository.CashierRepository;
import org.pgs.posback.service.CashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CashierServiceImpl implements CashierService {

    private final CashierRepository cashierRepository;

    @Autowired
    public CashierServiceImpl(CashierRepository cashierRepository) {
        this.cashierRepository = cashierRepository;
    }

    @Override
    public List<CashierResponseDTO> getAllCashiers() {
        List<CashierModel> cashiers = cashierRepository.findAll();
        return cashiers.stream()
                .map(CashierMapper.INSTANCE::cashierModelToCashierResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CashierResponseDTO getCashierById(Long id) {
        Optional<CashierModel> optionalCashier = cashierRepository.findById(id);
        return optionalCashier.map(CashierMapper.INSTANCE::cashierModelToCashierResponseDTO).orElse(null);
    }

    @Override
    public CashierResponseDTO createCashier(CashierRequestDTO cashierRequestDTO) {
        CashierModel cashier = CashierMapper.INSTANCE.cashierRequestDTOToCashierModel(cashierRequestDTO);
        cashier.setCreatedAt(new Date());
        cashier.setUpdatedAt(new Date());
        CashierModel savedCashier = cashierRepository.save(cashier);
        return CashierMapper.INSTANCE.cashierModelToCashierResponseDTO(savedCashier);
    }

    @Override
    public CashierResponseDTO updateCashier(Long cashierId, CashierRequestDTO cashierRequestDTO) {
        Optional<CashierModel> optionalCashier = cashierRepository.findById(cashierId);
        if (optionalCashier.isPresent()) {
            CashierModel cashier = optionalCashier.get();
            cashier.setName(cashierRequestDTO.getName());
            cashier.setRole(cashierRequestDTO.getRole());
            cashier.setManager(cashierRequestDTO.getManager()); // Assuming manager ID is directly set here
            cashier.setUpdatedAt(new Date());
            CashierModel updatedCashier = cashierRepository.save(cashier);
            return CashierMapper.INSTANCE.cashierModelToCashierResponseDTO(updatedCashier);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCashier(Long cashierId) {
        cashierRepository.deleteById(cashierId);
    }
}
