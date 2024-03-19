package com.example.completableFutureApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class EmployeeEntity {
	
	@Id
	String employeeId;
	String firstName;
	String lastName;
	String email;
	String gender;
	String newJoiner;
	String learningPending;
	Integer salary;
	Integer rating;

}
