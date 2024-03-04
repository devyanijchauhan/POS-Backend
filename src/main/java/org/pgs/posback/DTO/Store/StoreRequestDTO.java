package org.pgs.posback.DTO.Store;

import lombok.Data;

import java.util.Date;

@Data
public class StoreRequestDTO {

    private Long id;

    private String name;

    private String address;

    private String contactInformation;

    private String openingHours;

    private Date createdAt;

    private Date updatedAt;
}