package org.pgs.posback.repository;

import org.pgs.posback.model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Long> {
}
