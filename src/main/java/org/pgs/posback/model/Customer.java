package org.pgs.posback.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact_number")
    private Long contactNumber;

    @Column(name = "loyalty_points")
    private int loyaltyPoints;

    @JsonFormat(pattern = "dd-MM-yyyy") // Specify the custom date format
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "gender")
    private Boolean gender; // Changed gender attribute to Boolean

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    // Constructors

    public Customer() {
    }

    public CustomerModel(String name, Long contactNumber, int loyaltyPoints, Date dateOfBirth, String email, String address, Boolean gender, Date createdAt, Date updatedAt) {
        this.name = name;
        this.contactNumber = (Long) getContactNumber();
        this.loyaltyPoints = loyaltyPoints;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Long contactNumber) {this.contactNumber = contactNumber;}

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getGender() {return gender;}

    public void setGender(Boolean gender) {this.gender = gender;}

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Automatically set createdAt and updatedAt before persisting or updating the entity
    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    @JsonGetter("gender")
    public String getGenderAsString() {
        return gender != null && gender ? "Male" : "Female";
    }
}
