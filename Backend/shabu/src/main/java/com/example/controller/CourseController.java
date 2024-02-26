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

import com.example.model.Course;
import com.example.service.CourseService;

@Controller
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService service;

    // Create
    @PostMapping("/create")
    @ResponseBody
    public List<Course> create(@RequestBody List<Course> entityList) {
        return service.createMany(entityList);
    }

    // Read
    @GetMapping("/")
    @ResponseBody
    public List<Course> read() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Course readById(@PathVariable Integer id) {
        return service.findById(id);
    }

    // Update
    @PostMapping("/update/{id}")
    @ResponseBody
    public Course Course(@PathVariable Integer id, @RequestBody Course entity) {
        entity.setId(id);
        return service.update(entity);
    }

    // Delete
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Course delete(@PathVariable Integer id) {
        return service.delete(id);
    }

}
