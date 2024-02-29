package org.pgs.posback.repository;

import org.pgs.posback.model.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<StoreModel, Long> {
}
