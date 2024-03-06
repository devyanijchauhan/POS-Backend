package org.pgs.posback.service;

import org.pgs.posback.DTO.Cashier.CashierRequestDTO;
import org.pgs.posback.DTO.Cashier.CashierResponseDTO;

import java.util.List;

public interface CashierService {

    List<CashierResponseDTO> getAllCashiers();

    CashierResponseDTO getCashierById(Long id);

    CashierResponseDTO createCashier(CashierRequestDTO cashierRequestDTO);

    CashierResponseDTO updateCashier(Long cashierId, CashierRequestDTO cashierRequestDTO);

    void deleteCashier(Long cashierId);
}
