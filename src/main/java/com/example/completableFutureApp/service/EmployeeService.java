package com.example.completableFutureApp.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.completableFutureApp.entity.EmployeeEntity;
import com.example.completableFutureApp.model.Employee;
import com.example.completableFutureApp.repository.EmployeeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public Void saveEmployees(File jsonFile) throws InterruptedException, ExecutionException {
		Executor exceutor = Executors.newFixedThreadPool(5);
		ObjectMapper mapper = new ObjectMapper();
		CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(
				() -> {
				try {
					List<Employee> employeeList = mapper.readValue(jsonFile, new TypeReference<List<Employee>>(){});
					List<EmployeeEntity> employeeEntityList = new ArrayList<>();
					employeeList.forEach((employee)->{
						EmployeeEntity e = EmployeeEntity.builder().employeeId(employee.getEmployeeId())
								.firstName(employee.getFirstName()).lastName(employee.getLastName())
								.email(employee.getEmail()).gender(employee.getGender()).newJoiner(employee.getNewJoiner())
								.learningPending(employee.getLearningPending()).salary(employee.getSalary()).rating(employee.getRating()).build();
						employeeEntityList.add(e);
								
					});
					System.out.println("Thread :"+Thread.currentThread().getName());
					System.out.println(employeeEntityList.size());
					employeeRepository.saveAll(employeeEntityList);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}, exceutor);
		return runAsyncFuture.get();
	}
	
	public Void trainingReminder() throws InterruptedException, ExecutionException {
		CompletableFuture<Void> supplyAsyncFuture = CompletableFuture.supplyAsync(()->{
			System.out.println("Thread :"+Thread.currentThread().getName());
			return employeeRepository.findAll();
		}).thenApplyAsync((employees)->{
			System.out.println("Thread :"+Thread.currentThread().getName());
			return employees.stream().filter(e -> "TRUE".equals(e.getNewJoiner())).collect(Collectors.toList());
		}).thenApplyAsync((employees)->{
			System.out.println("Thread :"+Thread.currentThread().getName());
			return employees.stream().filter(e -> "TRUE".equals(e.getLearningPending())).collect(Collectors.toList());
		}).thenApplyAsync((employees)->{
			System.out.println("Thread :"+Thread.currentThread().getName());
			return employees.stream().map(e -> e.getEmail()).collect(Collectors.toList());
		}).thenAcceptAsync((emails)->{
			System.out.println("Thread :"+Thread.currentThread().getName());
			emails.forEach(EmployeeService::sendMail);			
		});
		return supplyAsyncFuture.get();
		
	}
	
	public static void sendMail(String email) {
		System.out.println("Sending training reminder mail to: "+email);
	}

}
