package org.pgs.posback.DTO.Sale;

import lombok.Data;
import org.pgs.posback.model.InvoiceModel;
import org.pgs.posback.model.ProductModel;

import java.util.Date;

@Data
public class SaleRequestDTO {

    private Long id;

    private InvoiceModel invoice;

    private ProductModel product;

    private int quantitySold;

    private double unitPrice;

    private double discount;

    private Date createdAt;

    private Date updatedAt;
}
