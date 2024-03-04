package org.pgs.posback.DTO.Transaction;

import lombok.Data;
import org.pgs.posback.model.StoreModel;

import java.util.Date;

@Data
public class TransactionResponseDTO {

    private Long id;

    private String type;

    private Double amount;

    private Date dateTime;

    private StoreModel store;

    private Date createdAt;

    private Date updatedAt;

}
