package com.example.completableFutureApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
	
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
