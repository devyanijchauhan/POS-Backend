package org.pgs.posback.service.impl;

import org.pgs.posback.DTO.Promotion.PromotionRequestDTO;
import org.pgs.posback.DTO.Promotion.PromotionResponseDTO;
import org.pgs.posback.mapper.PromotionMapper;
import org.pgs.posback.model.PromotionModel;
import org.pgs.posback.repository.PromotionRepository;
import org.pgs.posback.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;


    @Autowired
    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;

    }

    @Override
    public List<PromotionResponseDTO> getAllPromotions() {
        List<PromotionModel> promotions = promotionRepository.findAll();
        return promotions.stream()
                .map(PromotionMapper.INSTANCE::promotionModelToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public PromotionResponseDTO getPromotionById(Long id) {
        PromotionModel promotion = promotionRepository.findById(id).orElse(null);
        return promotion != null ? PromotionMapper.INSTANCE.promotionModelToResponseDto(promotion) : null;
    }

    @Override
    public PromotionResponseDTO createPromotion(PromotionRequestDTO promotionRequestDTO) {
        PromotionModel promotion = PromotionMapper.INSTANCE.promotionRequestDtoToModel(promotionRequestDTO);
        promotion.setCreatedAt(new Date());
        promotion.setUpdatedAt(new Date());
        PromotionModel savedPromotion = promotionRepository.save(promotion);
        return PromotionMapper.INSTANCE.promotionModelToResponseDto(savedPromotion);
    }

    @Override
    public PromotionResponseDTO updatePromotion(Long promotionId, PromotionRequestDTO promotionRequestDTO) {
        PromotionModel promotion = promotionRepository.findById(promotionId).orElse(null);
        if (promotion != null) {
            PromotionMapper.INSTANCE.promotionModelToResponseDto(promotion);
            promotion.setUpdatedAt(new Date());
            PromotionModel updatedPromotion = promotionRepository.save(promotion);
            return PromotionMapper.INSTANCE.promotionModelToResponseDto(updatedPromotion);
        } else {
            return null;
        }
    }

    @Override
    public void deletePromotion(Long promotionId) {
        PromotionModel promotion = promotionRepository.findById(promotionId).orElse(null);
        if (promotion != null) {
            promotionRepository.delete(promotion);
        }
    }
}
