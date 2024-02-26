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

import com.example.model.Payment;
import com.example.service.PaymentService;

@Controller
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    // Create
    @PostMapping("/create")
    @ResponseBody
    public List<Payment> create(@RequestBody List<Payment> entityList) {
        return service.createMany(entityList);
    }

    // Read
    @GetMapping("/")
    @ResponseBody
    public List<Payment> read() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Payment readById(@PathVariable Integer id) {
        return service.findById(id);
    }

}
