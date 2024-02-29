package org.pgs.posback.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "promotion")
public class PromotionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "promotion_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @Column(name = "scheduled_amount")
    private Double scheduledAmount;

    @Column(name = "product_combination", columnDefinition = "TEXT")
    private String productCombination;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    // Constructors
    public PromotionModel() {
    }

    public PromotionModel(String name, Date startDate, Date endDate, Double discountPercentage, Double scheduledAmount, String productCombination, Date createdAt, Date updatedAt) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercentage = discountPercentage;
        this.scheduledAmount = scheduledAmount;
        this.productCombination = productCombination;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Double getScheduledAmount() {
        return scheduledAmount;
    }

    public void setScheduledAmount(Double scheduledAmount) {
        this.scheduledAmount = scheduledAmount;
    }

    public String getProductCombination() {
        return productCombination;
    }

    public void setProductCombination(String productCombination) {
        this.productCombination = productCombination;
    }

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

    @Override
    public String toString() {
        return "PromotionModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", discountPercentage=" + discountPercentage +
                ", scheduledAmount=" + scheduledAmount +
                ", productCombination='" + productCombination + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
