package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.LoginHistory;
import com.example.service.LoginHistoryService;

@Controller
@RequestMapping("/api/loginHistory")
public class LoginHistoryController {

    @Autowired
    private LoginHistoryService service;

    // Create
    @PostMapping("/create")
    @ResponseBody
    public List<LoginHistory> create(@RequestBody List<LoginHistory> entityList) {
        return service.createMany(entityList);
    }

    // Read
    @GetMapping("/")
    @ResponseBody
    public List<LoginHistory> read() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public LoginHistory readById(@PathVariable Integer id) {
        return service.findById(id);
    }

}
