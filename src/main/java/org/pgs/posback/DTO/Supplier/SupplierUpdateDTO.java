package org.pgs.posback.DTO.Supplier;

import lombok.Data;

import java.util.Date;

@Data
public class SupplierUpdateDTO {

    private Long id;

    private String name;

    private Long contactNumber;

    private Date createdAt;

    private Date updatedAt;

}
