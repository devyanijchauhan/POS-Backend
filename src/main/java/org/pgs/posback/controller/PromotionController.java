package org.pgs.posback.controller;

import org.pgs.posback.model.PromotionModel;
import org.pgs.posback.repository.AdminRepository;
import org.pgs.posback.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionController {
    private PromotionRepository promotionRepository;

    @Autowired
    public PromotionController(PromotionRepository promotionRepository){
        this.promotionRepository=promotionRepository;
    }

    @GetMapping("all")
    public List<PromotionModel> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @GetMapping("/{id}")
    public PromotionModel getPromotionById(@PathVariable Long id) {
        return promotionRepository.findById(id).orElse(null);
    }

    @PostMapping
    public PromotionModel createPromotion(@RequestBody PromotionModel promotion) {
        promotion.setCreatedAt(new Date());
        promotion.setUpdatedAt(new Date());
        return promotionRepository.save(promotion);
    }

    @PutMapping("/{promotionid}")
    public PromotionModel updatePromotion(@PathVariable Long promotionid, @RequestBody PromotionModel promotionDetails) {
        PromotionModel promotion = promotionRepository.findById(promotionid).orElse(null);
        if (promotion != null) {
            promotion.setName(promotionDetails.getName());
            promotion.setStartDate(promotionDetails.getStartDate());
            promotion.setEndDate(promotionDetails.getEndDate());
            promotion.setDiscountPercentage(promotionDetails.getDiscountPercentage());
            promotion.setScheduledAmount(promotionDetails.getScheduledAmount());
            promotion.setProductCombination(promotionDetails.getProductCombination());
            promotion.setUpdatedAt(new Date());
            return promotionRepository.save(promotion);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{idp}")
    public void deletePromotion(@PathVariable Long idp) {
        PromotionModel promotion = promotionRepository.findById(idp).orElse(null);
        if (promotion != null) {
            promotionRepository.delete(promotion);
        }
    }
}
