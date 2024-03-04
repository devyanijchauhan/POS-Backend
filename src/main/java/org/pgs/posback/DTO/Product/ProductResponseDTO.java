package org.pgs.posback.DTO.Product;

import lombok.Data;
import org.pgs.posback.model.CategoryModel;
import org.pgs.posback.model.SupplierModel;

import java.util.Date;

@Data
public class ProductResponseDTO {

    private Long id;

    private String name;

    private String description;

    private Double buyingPrice;

    private Double sellingPrice;

    private Integer quantityInStock;

    private Integer reorderLevel;

    private CategoryModel category;

    private byte[] barcode;

    private SupplierModel supplier;

    private String batchNumber;

    private Date createdAt;

    private Date updatedAt;
}
