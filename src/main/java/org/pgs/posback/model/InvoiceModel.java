package org.pgs.posback.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "invoice")
public class InvoiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Long id;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "date_time")
    private Date dateTime;

    @Column(name = "invoice_price")
    private BigDecimal invoicePrice;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "tax_amount")
    private BigDecimal taxAmount;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private StoreModel store;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    // Constructors

    public InvoiceModel() {
    }

    public InvoiceModel(String invoiceNumber, Date dateTime, BigDecimal invoicePrice, BigDecimal totalAmount, BigDecimal taxAmount, BigDecimal discountAmount, String paymentMethod, Customer customer, StoreModel store, Date createdAt, Date updatedAt) {
        this.invoiceNumber = invoiceNumber;
        this.dateTime = dateTime;
        this.invoicePrice = invoicePrice;
        this.totalAmount = totalAmount;
        this.taxAmount = taxAmount;
        this.discountAmount = discountAmount;
        this.paymentMethod = paymentMethod;
        this.customer = customer;
        this.store = store;
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

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getInvoicePrice() {
        return invoicePrice;
    }

    public void setInvoicePrice(BigDecimal invoicePrice) {this.invoicePrice = invoicePrice;}

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public StoreModel getStore() {
        return store;
    }

    public void setStore(StoreModel store) {
        this.store = store;
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
