package org.pgs.posback.DTO.Admin;

import lombok.Data;
import org.pgs.posback.model.AccountModel;
import org.pgs.posback.model.ManagerModel;

import java.util.Date;

@Data
public class AdminUpdateDTO {

    private Long id;

    private AccountModel account;

    private String name;

    private String role;

    private ManagerModel manager;

    private Date createdAt;

    private Date updatedAt;
}
