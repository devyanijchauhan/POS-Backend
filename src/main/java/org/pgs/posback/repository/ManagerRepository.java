package org.pgs.posback.repository;

import org.pgs.posback.model.ManagerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<ManagerModel, Long> {
}
