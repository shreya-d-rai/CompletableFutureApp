package com.example.completableFutureApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.completableFutureApp.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String>{

}
