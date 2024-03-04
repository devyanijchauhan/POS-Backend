package org.pgs.posback.DTO.Customer;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerResponseDTO {

    private Long id;

    private String name;

    private int contactNumber;

    private int loyaltyPoints;

    private Date dateOfBirth;

    private String email;

    private String address;

    private Date createdAt;

    private Date updatedAt;

}