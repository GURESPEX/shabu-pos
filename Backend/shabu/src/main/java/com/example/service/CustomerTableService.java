package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.CustomerTable;
import com.example.repository.CustomerTableRepository;

@Service
public class CustomerTableService {

    @Autowired
    private CustomerTableRepository repository;

    // Create
    public CustomerTable createOne() {
        Integer nextId = this.findAll().size() + 1;
        CustomerTable entity = new CustomerTable();
        entity.setId(nextId);
        return repository.save(entity);
    }

    public List<CustomerTable> createMany(Integer amount) {
        List<CustomerTable> entityList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            entityList.add(this.createOne());
        }
        return (List<CustomerTable>) repository.saveAll(entityList);
    }

    // Read
    public List<CustomerTable> findAll() {
        return (List<CustomerTable>) repository.findAll();
    }

    public CustomerTable findById(Integer Id) {
        return this.existsById(Id) ? repository.findById(Id).get() : null;
    }

    // Update
    public CustomerTable update(CustomerTable entity) {
        if (this.existsById(entity.getId())) {
            return repository.save(entity);
        }
        return null;
    }

    // Delete
    public CustomerTable delete() {
        Integer lastId = this.findAll().size();
        System.out.println(lastId);
        CustomerTable foundedEntity = this.findById(lastId);
        if (this.existsById(lastId)) {
            repository.deleteById(lastId);
        }
        return foundedEntity;
    }

    // Exist
    public boolean existsById(Integer Id) {
        return repository.existsById(Id);
    }

    // ----- Other Service ----- //

}
