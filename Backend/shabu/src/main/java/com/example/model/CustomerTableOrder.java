package com.example.model;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class CustomerTableOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(nullable = false)
    private Integer CustomerAmount;
    @Column(nullable = false)
    private Boolean Canceled;
    @Column(nullable = false)
    private Date CreateDate;
    @Column(nullable = true)
    private Date UpdateDate;

    @OneToOne(mappedBy = "CustomerTableOrder", cascade = CascadeType.ALL)
    private Payment Payment;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE })
    @JoinColumn(name = "CustomerTable_Id", nullable = false)
    private CustomerTable CustomerTable;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE })
    @JoinColumn(name = "Course_Id", nullable = false)
    private Course Course;

    /* ----- Method ----- */

    public CustomerTableOrder() {
        CreateDate = new Date();
    }

    public CustomerTableOrder(Integer customerAmount,
            Payment payment, CustomerTable customerTable,
            Course course) {
        CustomerAmount = customerAmount;
        Canceled = false;
        Payment = payment;
        CustomerTable = customerTable;
        Course = course;
        CreateDate = new Date();
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getCustomerAmount() {
        return CustomerAmount;
    }

    public void setCustomerAmount(Integer customerAmount) {
        CustomerAmount = customerAmount;
    }

    public Boolean getCanceled() {
        return Canceled;
    }

    public void setCanceled(Boolean canceled) {
        Canceled = canceled;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public Date getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(Date updateDate) {
        UpdateDate = updateDate;
    }

    public Payment getPayment() {
        return Payment;
    }

    public void setPayment(Payment payment) {
        Payment = payment;
    }

    public CustomerTable getCustomerTable() {
        return CustomerTable;
    }

    public void setCustomerTable(CustomerTable customerTable) {
        CustomerTable = customerTable;
    }

    public Course getCourse() {
        return Course;
    }

    public void setCourse(Course course) {
        Course = course;
    }

}
