package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.InventoryAdjustment.InventoryAdjustmentRequestDTO;
import org.pgs.posback.DTO.InventoryAdjustment.InventoryAdjustmentResponseDTO;
import org.pgs.posback.mapper.InventoryAdjustmentMapper;
import org.pgs.posback.model.InventoryAdjustmentModel;
import org.pgs.posback.repository.InventoryAdjustmentRepository;
import org.pgs.posback.service.InventoryAdjustmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryAdjustmentServiceImpl implements InventoryAdjustmentService {

    private final InventoryAdjustmentRepository inventoryAdjustmentRepository;

    @Autowired
    public InventoryAdjustmentServiceImpl(InventoryAdjustmentRepository inventoryAdjustmentRepository) {
        this.inventoryAdjustmentRepository = inventoryAdjustmentRepository;
    }

    @Override
    public List<InventoryAdjustmentResponseDTO> getAllInventoryAdjustments() {
        List<InventoryAdjustmentModel> inventoryAdjustments = inventoryAdjustmentRepository.findAll();
        return inventoryAdjustments.stream()
                .map(InventoryAdjustmentMapper.INSTANCE::inventoryAdjustmentModelToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryAdjustmentResponseDTO getInventoryAdjustmentById(Long id) {
        Optional<InventoryAdjustmentModel> inventoryAdjustment = inventoryAdjustmentRepository.findById(id);
        return inventoryAdjustment.map(InventoryAdjustmentMapper.INSTANCE::inventoryAdjustmentModelToResponseDTO).orElse(null);
    }

    @Override
    public InventoryAdjustmentResponseDTO createInventoryAdjustment(InventoryAdjustmentRequestDTO inventoryAdjustmentRequestDTO) {
        InventoryAdjustmentModel inventoryAdjustmentModel = InventoryAdjustmentMapper.INSTANCE.inventoryAdjustmentRequestDTOToModel(inventoryAdjustmentRequestDTO);
        inventoryAdjustmentModel.setCreatedAt(new Date());
        inventoryAdjustmentModel.setUpdatedAt(new Date());
        InventoryAdjustmentModel savedInventoryAdjustment = inventoryAdjustmentRepository.save(inventoryAdjustmentModel);
        return InventoryAdjustmentMapper.INSTANCE.inventoryAdjustmentModelToResponseDTO(savedInventoryAdjustment);
    }

    @Override
    public InventoryAdjustmentResponseDTO updateInventoryAdjustment(Long adjustmentId, InventoryAdjustmentRequestDTO inventoryAdjustmentRequestDTO) {
        Optional<InventoryAdjustmentModel> optionalInventoryAdjustment = inventoryAdjustmentRepository.findById(adjustmentId);
        if (optionalInventoryAdjustment.isPresent()) {
            InventoryAdjustmentModel inventoryAdjustmentModel = optionalInventoryAdjustment.get();
            InventoryAdjustmentMapper.INSTANCE.updateInventoryAdjustmentFromRequestDTO(inventoryAdjustmentRequestDTO, inventoryAdjustmentModel);
            inventoryAdjustmentModel.setUpdatedAt(new Date());
            InventoryAdjustmentModel updatedInventoryAdjustment = inventoryAdjustmentRepository.save(inventoryAdjustmentModel);
            return InventoryAdjustmentMapper.INSTANCE.inventoryAdjustmentModelToResponseDTO(updatedInventoryAdjustment);
        }
        return null;
    }

    @Override
    public void deleteInventoryAdjustment(Long Id) {
        inventoryAdjustmentRepository.deleteById(Id);
    }
}