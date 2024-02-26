package com.example.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class CustomerTable {

    @Id
    private Integer Id;

    @OneToMany(mappedBy = "CustomerTable", cascade = { CascadeType.DETACH, CascadeType.MERGE })
    private List<CustomerTableOrder> CustomerTableOrders;

    /* ----- Method ----- */

    public CustomerTable() {
    }

    public CustomerTable(List<CustomerTableOrder> customerTableOrders) {
        CustomerTableOrders = customerTableOrders;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    @JsonIgnore
    public List<CustomerTableOrder> getCustomerTableOrders() {
        return CustomerTableOrders;
    }

    public void setCustomerTableOrders(List<CustomerTableOrder> customerTableOrders) {
        CustomerTableOrders = customerTableOrders;
    }

}
