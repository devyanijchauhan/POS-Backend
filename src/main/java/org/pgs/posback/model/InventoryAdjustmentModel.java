package org.pgs.posback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "inventory_adjustment")
public class InventoryAdjustmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adjustment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    private StoreModel store;

    @Column(name = "adjusted_quantity")
    private Integer adjustedQuantity;

    @Column(name = "reason")
    private String reason;

    @Column(name = "adjustment_date")
    private Date adjustmentDate;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public InventoryAdjustmentModel() {
    }

    public InventoryAdjustmentModel(ProductModel product, StoreModel store, Integer adjustedQuantity, String reason, Date adjustmentDate, Date createdAt, Date updatedAt) {
        this.product = product;
        this.store = store;
        this.adjustedQuantity = adjustedQuantity;
        this.reason = reason;
        this.adjustmentDate = adjustmentDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public StoreModel getStore() {
        return store;
    }

    public void setStore(StoreModel store) {
        this.store = store;
    }

    public Integer getAdjustedQuantity() {
        return adjustedQuantity;
    }

    public void setAdjustedQuantity(Integer adjustedQuantity) {
        this.adjustedQuantity = adjustedQuantity;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getAdjustmentDate() {
        return adjustmentDate;
    }

    public void setAdjustmentDate(Date adjustmentDate) {
        this.adjustmentDate = adjustmentDate;
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

}
