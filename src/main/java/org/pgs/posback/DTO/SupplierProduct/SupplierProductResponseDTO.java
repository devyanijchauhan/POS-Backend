package org.pgs.posback.DTO.SupplierProduct;

import lombok.Data;
import org.pgs.posback.model.ProductModel;
import org.pgs.posback.model.SupplierModel;

import java.util.Date;

@Data
public class SupplierProductResponseDTO {

    private Long id;

    private SupplierModel supplier;

    private ProductModel product;

    private Double unitPrice;

    private Date createdAt;

    private Date updatedAt;

}
