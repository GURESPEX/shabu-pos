package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Admin;
import com.example.model.Course;
import com.example.model.CustomerTableOrder;
import com.example.model.Employee;
import com.example.model.Payment;
import com.example.service.CourseService;
import com.example.service.CustomerTableOrderService;
import com.example.service.CustomerTableService;
import com.example.service.PaymentService;
import com.example.service.UserService;

@Controller
@RequestMapping("/api/init")
public class InitializeController {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private CustomerTableService customerTableService;
    @Autowired
    private CustomerTableOrderService customerTableOrderService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    @ResponseBody
    public String init() {
        System.out.println(this.initUser());
        // System.out.println(this.initCourse());
        // System.out.println(this.initCustomerTable());
        // System.out.println(this.initCustomerTableOrder());
        // System.out.println(this.initPayment());
        return "Initialized!";
    }

    public String initUser() {
        Admin admin = new Admin();
        admin.setSID("1456969696969");
        admin.setFirstname("Admin");
        admin.setLastname("Derja");
        admin.setTel("0811111111");
        admin.setAddress("29 หมู่ 55");
        admin.setCity("ขอนแก่น");
        admin.setDistrict("เมือง");
        admin.setSubDistrict("ในเมือง");
        admin.setPostCode("33333");
        admin.setPassword("HelloKitty");
        userService.createOne(admin);

        // Employee employee1 = new Employee();
        // employee1.setSID("2222222222222");
        // employee1.setFirstname("FB");
        // employee1.setLastname("LB");
        // employee1.setTel("0822222222");
        // employee1.setAddress("A");
        // employee1.setCity("B");
        // employee1.setDistrict("C");
        // employee1.setSubDistrict("D");
        // employee1.setPostCode("45000");
        // employee1.setPassword("12345678");
        // userService.createOne(employee1);

        // Employee employee2 = new Employee();
        // employee2.setSID("3333333333333");
        // employee2.setFirstname("FC");
        // employee2.setLastname("LC");
        // employee2.setTel("0833333333");
        // employee2.setAddress("A");
        // employee2.setCity("B");
        // employee2.setDistrict("C");
        // employee2.setSubDistrict("D");
        // employee2.setPostCode("45000");
        // employee2.setPassword("12345678");
        // userService.createOne(employee2);

        return "User Initialized!";
    }

    public String initCourse() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Course A", null, 299d, null));
        courses.add(new Course("Course B", null, 399d, null));
        courses.add(new Course("Course C", null, 499d, null));
        courseService.createMany(courses);

        return "Course Initialized!";
    }

    public String initCustomerTable() {
        customerTableService.createMany(16);

        return "Table Initialized!";
    }

    public String initCustomerTableOrder() {
        List<CustomerTableOrder> customerTableOrder = new ArrayList<>();
        customerTableOrder
                .add(new CustomerTableOrder(3, null, customerTableService.findById(1),
                        courseService.findById(1)));
        CustomerTableOrder customerTableOrderObj = new CustomerTableOrder(2, null,
                customerTableService.findById(4),
                courseService.findById(2));
        customerTableOrderObj.setCanceled(true);
        customerTableOrder
                .add(customerTableOrderObj);
        customerTableOrder
                .add(new CustomerTableOrder(4, null, customerTableService.findById(9),
                        courseService.findById(3)));
        customerTableOrder
                .add(new CustomerTableOrder(3, null, customerTableService.findById(7),
                        courseService.findById(2)));
        customerTableOrderService.createMany(customerTableOrder);

        return "Order Initialized!";
    }

    public String initPayment() {
        List<Payment> payments = new ArrayList<>();
        payments.add(
                new Payment(userService.findById(2), customerTableOrderService.findById(1)));
        payments.add(
                new Payment(userService.findById(3), customerTableOrderService.findById(3)));
        paymentService.createMany(payments);

        return "Payment Initialized!";
    }

}
