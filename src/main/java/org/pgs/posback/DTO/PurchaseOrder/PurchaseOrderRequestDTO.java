package org.pgs.posback.DTO.PurchaseOrder;

import lombok.Data;
import org.pgs.posback.model.StoreModel;
import org.pgs.posback.model.SupplierModel;

import java.util.Date;

@Data
public class PurchaseOrderRequestDTO {

    private Long id;

    private Date date;

    private SupplierModel supplier;

    private String status;

    private StoreModel store;

    private Date createdAt;

    private Date updatedAt;
}
