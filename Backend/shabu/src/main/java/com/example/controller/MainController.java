package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.CustomerTableOrder;
import com.example.model.User;
import com.example.service.UserService;

@Controller
@RequestMapping("/api")
public class MainController {
    @Autowired
    private UserService userService;

    // Login
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody String json) {
        return userService.login(json);
    }

    @PostMapping("/cookie")
    @ResponseBody
    public User getByCookie(@RequestBody(required = false) String session) {
        return userService.getByCookie(session);
    }

    @PostMapping("/checkBill")
    @ResponseBody
    public CustomerTableOrder checkBill(@RequestBody String json) {
        return userService.checkBill(json);
    }

}
