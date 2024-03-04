package org.pgs.posback.DTO.Account;

import lombok.Data;

import java.util.Date;

@Data
public class AccountUpdateDTO {

    private Long id;

    private String username;

    private String password;

    private String role;

    private Date createdAt;

    private Date updatedAt;
}
