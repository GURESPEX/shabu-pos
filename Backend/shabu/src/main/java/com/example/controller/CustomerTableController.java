package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.CustomerTable;
import com.example.service.CustomerTableService;

@Controller
@RequestMapping("/api/customerTable")
public class CustomerTableController {

    @Autowired
    private CustomerTableService service;

    // Create
    @PostMapping("/create")
    @ResponseBody
    public List<CustomerTable> create(@RequestParam(required = false) Integer amount) {
        return service.createMany(amount = 1);
    }

    // Read
    @GetMapping("/")
    @ResponseBody
    public List<CustomerTable> read() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CustomerTable readById(@PathVariable Integer id) {
        return service.findById(id);
    }

    // Delete
    @PostMapping("/delete")
    @ResponseBody
    public CustomerTable delete() {
        return service.delete();
    }

}
