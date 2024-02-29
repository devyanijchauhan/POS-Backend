package org.pgs.posback.repository;

import org.pgs.posback.model.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceModel, Long> {
}
