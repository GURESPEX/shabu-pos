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

import com.example.model.CustomerTableOrder;
import com.example.service.CustomerTableOrderService;

@Controller
@RequestMapping("/api/customerTableOrder")
public class CustomerTableOrderController {

    @Autowired
    private CustomerTableOrderService service;

    // Create
    @PostMapping("/create")
    @ResponseBody
    public List<CustomerTableOrder> create(@RequestBody List<CustomerTableOrder> entityList) {
        return service.createMany(entityList);
    }

    // Read
    @GetMapping("/")
    @ResponseBody
    public List<CustomerTableOrder> read() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CustomerTableOrder readById(@PathVariable Integer id) {
        return service.findById(id);
    }

    // Update
    @PostMapping("/update/{id}")
    @ResponseBody
    public CustomerTableOrder TableOrder(@PathVariable Integer id, @RequestBody CustomerTableOrder entity) {
        entity.setId(id);
        return service.update(entity);
    }

    // Delete
    @PostMapping("/delete/{id}")
    @ResponseBody
    public CustomerTableOrder delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    // Delete
    @PostMapping("/cancelOrder/{id}")
    @ResponseBody
    public CustomerTableOrder cancelOrder(@PathVariable Integer id) {
        return service.cancelOrder(id);
    }

}
