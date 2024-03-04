package org.pgs.posback.DTO.Invoice;

import lombok.Data;
import org.pgs.posback.model.CustomerModel;
import org.pgs.posback.model.StoreModel;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InvoiceUpdateDTO {

    private Long id;

    private String invoiceNumber;

    private Date dateTime;

    private BigDecimal totalAmount;

    private BigDecimal taxAmount;

    private BigDecimal discountAmount;

    private String paymentMethod;

    private CustomerModel customer;

    private StoreModel store;

    private Date createdAt;

    private Date updatedAt;

}
