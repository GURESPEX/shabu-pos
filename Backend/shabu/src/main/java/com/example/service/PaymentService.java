package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Payment;
import com.example.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    // Create
    public Payment createOne(Payment entity) {
        entity.setId(null);
        entity.setCreateDate(new Date());
        return repository.save(entity);
    }

    public List<Payment> createMany(List<Payment> entityList) {
        List<Payment> foundedEntityList = new ArrayList<>();
        for (Payment entity : entityList) {
            entity.setId(null);
            entity.setCreateDate(new Date());
            foundedEntityList.add(entity);
        }
        return (List<Payment>) repository.saveAll(foundedEntityList);
    }

    // Read
    public List<Payment> findAll() {
        return (List<Payment>) repository.findAll();
    }

    public Payment findById(Integer Id) {
        return this.existsById(Id) ? repository.findById(Id).get() : null;
    }

    // Update
    public Payment update(Payment entity) {
        if (this.existsById(entity.getId())) {
            return repository.save(entity);
        }
        return null;
    }

    // Delete
    public Payment delete(Integer Id) {
        Payment foundedEntity = this.findById(Id);
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

}
