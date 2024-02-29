package org.pgs.posback.repository;

import org.pgs.posback.model.PromotionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<PromotionModel, Long> {
}
