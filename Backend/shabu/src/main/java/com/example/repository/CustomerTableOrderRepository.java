package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.CustomerTableOrder;

@Repository
public interface CustomerTableOrderRepository extends CrudRepository<CustomerTableOrder, Integer> {

}
