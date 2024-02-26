package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.CustomerTableOrder;
import com.example.repository.CustomerTableOrderRepository;

@Service
public class CustomerTableOrderService {

    @Autowired
    private CustomerTableOrderRepository repository;

    // Create
    public CustomerTableOrder createOne(CustomerTableOrder entity) {
        entity.setId(null);
        entity.setCreateDate(new Date());
        return repository.save(entity);
    }

    public List<CustomerTableOrder> createMany(List<CustomerTableOrder> entityList) {
        List<CustomerTableOrder> foundedEntityList = new ArrayList<>();
        for (CustomerTableOrder entity : entityList) {
            entity.setId(null);
            entity.setCreateDate(new Date());
            foundedEntityList.add(entity);
        }
        return (List<CustomerTableOrder>) repository.saveAll(foundedEntityList);
    }

    // Read
    public List<CustomerTableOrder> findAll() {
        return (List<CustomerTableOrder>) repository.findAll();
    }

    public CustomerTableOrder findById(Integer Id) {
        return this.existsById(Id) ? repository.findById(Id).get() : null;
    }

    // Update
    public CustomerTableOrder update(CustomerTableOrder entity) {
        if (this.existsById(entity.getId())) {
            entity.setUpdateDate(new Date());
            return repository.save(entity);
        }
        return null;
    }

    // Delete
    public CustomerTableOrder delete(Integer Id) {
        CustomerTableOrder foundedEntity = this.findById(Id);
        if (this.existsById(Id)) {
            repository.deleteById(Id);
        }
        return foundedEntity;
    }

    // Exist
    public boolean existsById(Integer Id) {
        return repository.existsById(Id);
    }

    // ----- Other Service ----- //

    public CustomerTableOrder cancelOrder(Integer Id) {
        if (this.existsById(Id)) {
            CustomerTableOrder customerTableOrder = this.findById(Id);
            customerTableOrder.setCanceled(true);
            this.update(customerTableOrder);
            return customerTableOrder;
        }
        return null;
    }

}
