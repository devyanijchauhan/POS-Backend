package org.pgs.posback.DTO.Invoice;

import lombok.Data;
import org.pgs.posback.model.Customer;
import org.pgs.posback.model.StoreModel;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InvoiceRequestDTO {

    private Long id;

    private String invoiceNumber;

    private Date dateTime;

    private BigDecimal totalAmount;

    private BigDecimal taxAmount;

    private BigDecimal discountAmount;

    private String paymentMethod;

    private Customer customer;

    private StoreModel store;

    private Date createdAt;

    private Date updatedAt;
}
