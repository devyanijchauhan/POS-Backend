package org.pgs.posback.DTO.InventoryAdjustment;

import lombok.Data;
import org.pgs.posback.model.ProductModel;
import org.pgs.posback.model.StoreModel;

import java.util.Date;

@Data
public class InventoryAdjustmentRequestDTO {

    private Long id;

    private ProductModel product;

    private StoreModel store;

    private Integer adjustedQuantity;

    private String reason;

    private Date adjustmentDate;

    private Date createdAt;

    private Date updatedAt;

}
