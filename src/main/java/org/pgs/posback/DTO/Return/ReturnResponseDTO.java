package org.pgs.posback.DTO.Return;

import lombok.Data;
import org.pgs.posback.model.InvoiceModel;
import org.pgs.posback.model.SaleModel;

import java.util.Date;

@Data
public class ReturnResponseDTO {

    private Long id;

    private Date date;

    private String reason;

    private Double refundedAmount;

    private SaleModel sale;

    private InvoiceModel invoice;

    private Date createdAt;

    private Date updatedAt;
}
