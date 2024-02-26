package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Admin;
import com.example.model.User;
import com.example.service.UserService;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService service;

    // Create
    @PostMapping("/create")
    @ResponseBody
    public List<User> create(@RequestBody List<Admin> entityList) {
        List<User> createList = new ArrayList<>();
        for (User entity : entityList) {
            createList.add(entity);
        }
        return service.createMany(createList);
    }

    // Read
    @GetMapping("/")
    @ResponseBody
    public List<User> read() {
        return service.findAll().stream().filter(e -> e instanceof Admin).map(e -> (Admin) e)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public User readById(@PathVariable Integer id) {
        return service.findById(id);
    }

    // Update
    @PostMapping("/update/{id}")
    @ResponseBody
    public User Admin(@PathVariable Integer id, @RequestBody Admin entity) {
        entity.setId(id);
        return service.update(entity);
    }

    // Delete
    @PostMapping("/delete/{id}")
    @ResponseBody
    public User delete(@PathVariable Integer id) {
        return service.delete(id);
    }

}
