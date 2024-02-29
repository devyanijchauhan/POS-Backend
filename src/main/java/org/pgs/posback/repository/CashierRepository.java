package org.pgs.posback.repository;

import org.pgs.posback.model.CashierModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashierRepository extends JpaRepository<CashierModel, Long> {
}
