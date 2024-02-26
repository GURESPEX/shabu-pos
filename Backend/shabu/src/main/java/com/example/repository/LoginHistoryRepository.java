package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.LoginHistory;

@Repository
public interface LoginHistoryRepository extends CrudRepository<LoginHistory, Integer> {

}
