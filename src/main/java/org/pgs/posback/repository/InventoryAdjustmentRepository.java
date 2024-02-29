package org.pgs.posback.repository;

import org.pgs.posback.model.InventoryAdjustmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryAdjustmentRepository extends JpaRepository<InventoryAdjustmentModel, Long> {
}
