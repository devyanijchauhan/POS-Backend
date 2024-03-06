package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Sale.SaleRequestDTO;
import org.pgs.posback.DTO.Sale.SaleResponseDTO;
import org.pgs.posback.mapper.SaleMapper;
import org.pgs.posback.model.SaleModel;
import org.pgs.posback.repository.SaleRepository;
import org.pgs.posback.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public List<SaleResponseDTO> getAllSales() {
        List<SaleModel> sales = saleRepository.findAll();
        return sales.stream()
                .map(SaleMapper.INSTANCE::saleModelToSaleResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SaleResponseDTO getSaleById(Long id) {
        return saleRepository.findById(id)
                .map(SaleMapper.INSTANCE::saleModelToSaleResponseDTO)
                .orElse(null);
    }

    @Override
    public SaleResponseDTO createSale(SaleRequestDTO saleRequestDTO) {
        SaleModel sale = SaleMapper.INSTANCE.saleRequestDTOToSaleModel(saleRequestDTO);
        SaleModel savedSale = saleRepository.save(sale);
        return SaleMapper.INSTANCE.saleModelToSaleResponseDTO(savedSale);
    }

    @Override
    public SaleResponseDTO updateSale(Long saleId, SaleRequestDTO saleRequestDTO) {
        return saleRepository.findById(saleId)
                .map(sale -> {
                    SaleModel updatedSale = SaleMapper.INSTANCE.saleRequestDTOToSaleModel(saleRequestDTO);
                    updatedSale.setId(saleId);
                    SaleModel savedSale = saleRepository.save(updatedSale);
                    return SaleMapper.INSTANCE.saleModelToSaleResponseDTO(savedSale);
                })
                .orElse(null);
    }

    @Override
    public void deleteSale(Long saleId) {
        saleRepository.deleteById(saleId);
    }
}
