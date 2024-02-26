package com.example.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(nullable = false, length = 100)
    private String Name;
    @Column(nullable = true, length = 500)
    private String Description;
    @Column(nullable = false)
    private Double Price;

    @OneToMany(mappedBy = "Course", cascade = { CascadeType.ALL })
    private List<CustomerTableOrder> CustomerTableOrders;

    /* ----- Method ----- */

    public Course() {
    }

    public Course(String name, String description, Double price,
            List<CustomerTableOrder> customerTableOrders) {
        Name = name;
        Description = description;
        Price = price;
        CustomerTableOrders = customerTableOrders;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    @JsonIgnore
    public List<CustomerTableOrder> getCustomerTableOrders() {
        return CustomerTableOrders;
    }

    public void setCustomerTableOrders(List<CustomerTableOrder> customerTableOrders) {
        CustomerTableOrders = customerTableOrders;
    }

}
