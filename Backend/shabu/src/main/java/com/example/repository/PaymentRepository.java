package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}
