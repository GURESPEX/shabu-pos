package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.LoginHistory;
import com.example.repository.LoginHistoryRepository;

@Service
public class LoginHistoryService {

    @Autowired
    private LoginHistoryRepository repository;

    // Create
    public LoginHistory createOne(LoginHistory entity) {
        entity.setId(null);
        entity.setCreateDate(new Date());
        return repository.save(entity);
    }

    public List<LoginHistory> createMany(List<LoginHistory> entityList) {
        List<LoginHistory> foundedEntityList = new ArrayList<>();
        for (LoginHistory entity : entityList) {
            entity.setId(null);
            entity.setCreateDate(new Date());
            foundedEntityList.add(entity);
        }
        return (List<LoginHistory>) repository.saveAll(foundedEntityList);
    }

    // Read
    public List<LoginHistory> findAll() {
        return (List<LoginHistory>) repository.findAll();
    }

    public LoginHistory findById(Integer Id) {
        return this.existsById(Id) ? repository.findById(Id).get() : null;
    }

    // Update
    public LoginHistory update(LoginHistory entity) {
        if (this.existsById(entity.getId())) {
            return repository.save(entity);
        }
        return null;
    }

    // Delete
    public LoginHistory delete(Integer Id) {
        LoginHistory foundedEntity = this.findById(Id);
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
