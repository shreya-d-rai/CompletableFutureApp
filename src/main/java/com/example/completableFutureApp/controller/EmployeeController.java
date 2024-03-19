package com.example.completableFutureApp.controller;

import java.io.File;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.completableFutureApp.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeservice;
	
	@GetMapping("/data")
	public ResponseEntity<String> addEmployees() throws InterruptedException, ExecutionException{
		employeeservice.saveEmployees(new File("employees.json"));
		return new ResponseEntity<String>("Records added to DB",HttpStatus.OK);
		
	}
	
	@GetMapping("/trainingstatusdata")
	public ResponseEntity<String> traingStatusEmployees() throws InterruptedException, ExecutionException{
		employeeservice.trainingReminder();
		return new ResponseEntity<String>("Mail sent",HttpStatus.OK);
		
	}

}
