package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Barcode.BarcodeRequestDTO;
import org.pgs.posback.DTO.Barcode.BarcodeResponseDTO;
import org.pgs.posback.mapper.BarcodeMapper;
import org.pgs.posback.model.BarcodeModel;
import org.pgs.posback.repository.BarcodeRepository;
import org.pgs.posback.service.BarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BarcodeServiceImpl implements BarcodeService {

    private final BarcodeRepository barcodeRepository;

    @Autowired
    public BarcodeServiceImpl(BarcodeRepository barcodeRepository) {
        this.barcodeRepository = barcodeRepository;
    }

    @Override
    public List<BarcodeResponseDTO> getAllBarcodes() {
        List<BarcodeModel> barcodes = barcodeRepository.findAll();
        return barcodes.stream()
                .map(BarcodeMapper.INSTANCE::barcodeModelToBarcodeResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BarcodeResponseDTO getBarcodeById(Long id) {
        Optional<BarcodeModel> optionalBarcode = barcodeRepository.findById(id);
        return optionalBarcode.map(BarcodeMapper.INSTANCE::barcodeModelToBarcodeResponseDTO).orElse(null);
    }

    @Override
    public BarcodeResponseDTO createBarcode(BarcodeRequestDTO barcodeRequestDTO) {
        BarcodeModel barcode = BarcodeMapper.INSTANCE.barcodeRequestDTOToBarcodeModel(barcodeRequestDTO);
        barcode.setCreatedAt(new Date());
        barcode.setUpdatedAt(new Date());
        BarcodeModel savedBarcode = barcodeRepository.save(barcode);
        return BarcodeMapper.INSTANCE.barcodeModelToBarcodeResponseDTO(savedBarcode);
    }

    @Override
    public BarcodeResponseDTO updateBarcode(Long barcodeId, BarcodeRequestDTO barcodeRequestDTO) {
        Optional<BarcodeModel> optionalBarcode = barcodeRepository.findById(barcodeId);
        if (optionalBarcode.isPresent()) {
            BarcodeModel barcode = optionalBarcode.get();
            barcode.setProduct(barcodeRequestDTO.getProduct());
            barcode.setBarcodeImage(barcodeRequestDTO.getBarcodeImage());
            barcode.setUpdatedAt(new Date());
            BarcodeModel updatedBarcode = barcodeRepository.save(barcode);
            return BarcodeMapper.INSTANCE.barcodeModelToBarcodeResponseDTO(updatedBarcode);
        } else {
            return null;
        }
    }

    @Override
    public void deleteBarcode(Long barcodeId) {
        barcodeRepository.deleteById(barcodeId);
    }
}
