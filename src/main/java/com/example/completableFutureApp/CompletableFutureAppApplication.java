package com.example.completableFutureApp;

import java.io.File;
import java.util.concurrent.ExecutionException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.completableFutureApp.service.EmployeeService;

@SpringBootApplication
public class CompletableFutureAppApplication {
	
	

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		SpringApplication.run(CompletableFutureAppApplication.class, args);
		
//		EmployeeService employeeservice = new EmployeeService();
//		employeeservice.saveEmployees(new File("employees.json"));
	}

}
