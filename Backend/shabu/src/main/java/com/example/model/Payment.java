package com.example.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
public class Payment {

    @Id
    @Column(unique = true, nullable = false)
    private Integer Id;
    @Column(nullable = false)
    private Date CreateDate;

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "User_Id", nullable = false)
    private User User;

    @OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE })
    @MapsId
    @JoinColumn(name = "CustomerTableOrder_Id", nullable = false)
    private CustomerTableOrder CustomerTableOrder;

    /* ----- Method ----- */

    public Payment() {
        CreateDate = new Date();
    }

    public Payment(User user, CustomerTableOrder customerTableOrder) {
        CreateDate = new Date();
        User = user;
        CustomerTableOrder = customerTableOrder;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }

    @JsonIgnore
    public CustomerTableOrder getCustomerTableOrder() {
        return CustomerTableOrder;
    }

    public void setCustomerTableOrder(CustomerTableOrder customerTableOrder) {
        CustomerTableOrder = customerTableOrder;
    }

}
