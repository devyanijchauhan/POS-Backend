package org.pgs.posback.repository;

import org.pgs.posback.model.BarcodeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarcodeRepository extends JpaRepository<BarcodeModel, Long> {
}
