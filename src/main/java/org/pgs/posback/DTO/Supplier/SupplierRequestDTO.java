package org.pgs.posback.DTO.Supplier;

import lombok.Data;

import java.util.Date;

@Data
public class SupplierRequestDTO {

    private Long id;

    private String name;

    private String contactInformation;

    private Date createdAt;

    private Date updatedAt;

}
