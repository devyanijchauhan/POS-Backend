package org.pgs.posback.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "buying_price")
    private Double buyingPrice;

    @Column(name = "selling_price")
    private Double sellingPrice;

    @Column(name = "quantity_in_stock")
    private Integer quantityInStock;

    @Column(name = "reorder_level")
    private Integer reorderLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private CategoryModel category;

    // Add other relations as needed

    @Column(name = "barcode", columnDefinition = "BYTEA")
    private byte[] barcode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", referencedColumnName = "supplier_id")
    private SupplierModel supplier;

    @Column(name = "batch_number")
    private String batchNumber;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    // Constructors
    public ProductModel() {
    }

    public ProductModel(String name, String description, Double buyingPrice, Double sellingPrice, Integer quantityInStock, Integer reorderLevel, CategoryModel category, byte[] barcode, SupplierModel supplier, String batchNumber, Date createdAt, Date updatedAt) {
        this.name = name;
        this.description = description;
        this.buyingPrice = buyingPrice;
        this.sellingPrice = sellingPrice;
        this.quantityInStock = quantityInStock;
        this.reorderLevel = reorderLevel;
        this.category = category;
        this.barcode = barcode;
        this.supplier = supplier;
        this.batchNumber = batchNumber;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(Double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public byte[] getBarcode() {
        return barcode;
    }

    public void setBarcode(byte[] barcode) {
        this.barcode = barcode;
    }

    public SupplierModel getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierModel supplier) {
        this.supplier = supplier;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
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
}
