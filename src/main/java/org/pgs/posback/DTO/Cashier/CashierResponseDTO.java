package org.pgs.posback.DTO.Cashier;

import lombok.Data;
import org.pgs.posback.model.AccountModel;
import org.pgs.posback.model.ManagerModel;
import org.pgs.posback.model.StoreModel;

import java.util.Date;

@Data
public class CashierResponseDTO {

    private Long id;

    private AccountModel account;

    private String name;

    private String role;

    private ManagerModel manager;

    private StoreModel store;

    private Date createdAt;

    private Date updatedAt;

}
