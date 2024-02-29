package org.pgs.posback.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "manager")
public class ManagerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private AccountModel account;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", referencedColumnName = "admin_id")
    private AdminModel admin;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    private StoreModel store;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    // Constructors
    public ManagerModel() {
    }

    public ManagerModel(AccountModel account, String name, String role, AdminModel admin, StoreModel store, Date createdAt, Date updatedAt) {
        this.account = account;
        this.name = name;
        this.role = role;
        this.admin = admin;
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

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(AccountModel account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AdminModel getAdmin() {
        return admin;
    }

    public void setAdmin(AdminModel admin) {
        this.admin = admin;
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
}
