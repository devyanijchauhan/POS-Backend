package org.pgs.posback.controller;

import org.pgs.posback.DTO.Promotion.PromotionRequestDTO;
import org.pgs.posback.DTO.Promotion.PromotionResponseDTO;
import org.pgs.posback.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PromotionResponseDTO>> getAllPromotions() {
        return new ResponseEntity<>(promotionService.getAllPromotions(),HttpStatus.OK);
//        List<PromotionResponseDTO> promotions = promotionService.getAllPromotions();
//        return ResponseEntity.ok(promotions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionResponseDTO> getPromotionById(@PathVariable Long id) {
        PromotionResponseDTO promotion = promotionService.getPromotionById(id);
        return promotion != null ? ResponseEntity.ok(promotion) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PromotionResponseDTO> createPromotion(@RequestBody PromotionRequestDTO promotionRequestDTO) {
        PromotionResponseDTO createdPromotion = promotionService.createPromotion(promotionRequestDTO);
        return new ResponseEntity<>(createdPromotion, HttpStatus.CREATED);
    }

    @PutMapping("/{promotionId}")
    public ResponseEntity<PromotionResponseDTO> updatePromotion(@PathVariable Long promotionId, @RequestBody PromotionRequestDTO promotionRequestDTO) {
        PromotionResponseDTO updatedPromotion = promotionService.updatePromotion(promotionId, promotionRequestDTO);
        return updatedPromotion != null ? ResponseEntity.ok(updatedPromotion) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{promotionId}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long promotionId) {
        promotionService.deletePromotion(promotionId);
        return ResponseEntity.noContent().build();
    }
}
