package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.model.CustomerTableOrder;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private CustomerTableOrderService customerTableOrderService;

    // Create
    public User createOne(User entity) {
        entity.setId(null);
        entity.setCreateDate(new Date());
        return repository.save(entity);
    }

    public List<User> createMany(List<User> entityList) {
        List<User> foundedEntityList = new ArrayList<>();
        for (User entity : entityList) {
            entity.setId(null);
            entity.setCreateDate(new Date());
            foundedEntityList.add(entity);
        }
        return (List<User>) repository.saveAll(foundedEntityList);
    }

    // Read
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    public User findById(Integer Id) {
        return this.existsById(Id) ? repository.findById(Id).get() : null;
    }

    // Update
    public User update(User entity) {
        if (this.existsById(entity.getId())) {
            User user = this.findById(entity.getId());
            user.setSID(entity.getSID());
            user.setFirstname(entity.getFirstname());
            user.setLastname(entity.getLastname());
            user.setEmail(entity.getEmail());
            user.setTel(entity.getTel());
            user.setAddress(entity.getAddress());
            user.setCity(entity.getCity());
            user.setDistrict(entity.getDistrict());
            user.setSubDistrict(entity.getSubDistrict());
            user.setPostCode(entity.getPostCode());
            user.setPassword(entity.getPassword());
            user.setPayments(entity.getPayments());
            user.setLoginHistories(entity.getLoginHistories());
            user.setUpdateDate(new Date());
            return repository.save(user);
        }
        return null;
    }

    // Delete
    public User delete(Integer Id) {
        User foundedEntity = this.findById(Id);
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

    public String login(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            var map = mapper.readValue(json, Map.class);
            String userID = (String) map.get("userID");
            String password = (String) map.get("password");

            for (User user : this.findAll()) {
                User temp = user.login(userID, password);
                if (temp != null) {
                    user = this.update(user);
                    return user.getSession();
                }
            }

            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CustomerTableOrder checkBill(@RequestBody String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            var map = mapper.readValue(json, Map.class);
            String userID = (String) map.get("userID");
            String password = (String) map.get("password");
            Integer customerOrderId = (Integer) map.get("customerOrderId");

            for (User user : this.findAll()) {
                if (user.isExist(userID, password)) {
                    CustomerTableOrder temp = customerTableOrderService.findById(customerOrderId);
                    CustomerTableOrder customerTableOrder = user.checkBill(userID, password, temp);
                    if (temp != null) {
                        this.update(user);
                        return temp;
                    }
                    return customerTableOrder;
                }
            }

            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getByCookie(String session) {
        System.out.println(session);
        if (!(session == null)) {
            for (User user : this.findAll()) {
                if (session.equals(user.getSession())) {
                    return user;
                }
                return null;
            }
        }
        return null;
    }

}
