package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Course;
import com.example.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    // Create
    public Course createOne(Course entity) {
        entity.setId(null);
        return repository.save(entity);
    }

    public List<Course> createMany(List<Course> entityList) {
        List<Course> foundedEntityList = new ArrayList<>();
        for (Course entity : entityList) {
            entity.setId(null);
            foundedEntityList.add(entity);
        }
        return (List<Course>) repository.saveAll(foundedEntityList);
    }

    // Read
    public List<Course> findAll() {
        return (List<Course>) repository.findAll();
    }

    public Course findById(Integer Id) {
        return this.existsById(Id) ? repository.findById(Id).get() : null;
    }

    // Update
    public Course update(Course entity) {
        if (this.existsById(entity.getId())) {
            return repository.save(entity);
        }
        return null;
    }

    // Delete
    public Course delete(Integer Id) {
        Course foundedEntity = this.findById(Id);
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
