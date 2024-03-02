package org.pgs.posback.dto.Customer;
import lombok.Data;
import java.util.Date;

@Data
public class CustomerResponseDTO {

    private Long id;

    private String name;

    private String contactInformation;

    private int loyaltyPoints;

    private Date dateOfBirth;

    private String email;

    private String address;

    private Date createdAt;

    private Date updatedAt;
}
