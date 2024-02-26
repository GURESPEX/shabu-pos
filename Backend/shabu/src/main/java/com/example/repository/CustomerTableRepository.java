package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.CustomerTable;

@Repository
public interface CustomerTableRepository extends CrudRepository<CustomerTable, Integer> {

}
