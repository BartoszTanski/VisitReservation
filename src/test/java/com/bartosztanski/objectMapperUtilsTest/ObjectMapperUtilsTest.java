package com.bartosztanski.objectMapperUtilsTest;


import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bartosztanski.visitreservation.entity.ClientEntity;
import com.bartosztanski.visitreservation.entity.EmployeeEntity;
import com.bartosztanski.visitreservation.entity.VisitEntity;
import com.bartosztanski.visitreservation.model.Client;
import com.bartosztanski.visitreservation.model.Employee;
import com.bartosztanski.visitreservation.model.Visit;
import com.bartosztanski.visitreservation.utils.ObjectMapperUtils;

public class ObjectMapperUtilsTest {
	
	Employee employee;
	EmployeeEntity employeeEntity;
	Visit visit;
	VisitEntity visitsEntity;
	Client client;
	ClientEntity clientEntity;
	Date date = new Date(2023, 10, 10, 10, 10);
	@BeforeEach
	void init() {
		
		clientEntity = new ClientEntity().builder()
				.id("1C")
				.firstName("Jan")
				.lastName("Kowalski")
				.phoneNumber(Long.valueOf(234567890))
				.emailAddress("jan.kowalski@gmail.com")
				.visits(List.of(visitsEntity))
				.build();
		
		client = new Client().builder()
				.id("1C")
				.firstName("Jan")
				.lastName("Kowalski")
				.phoneNumber(Long.valueOf(234567890))
				.emailAddress("jan.kowalski@gmail.com")
				.visits(List.of(visit))
				.build();
				
		
		visit = new Visit().builder()
				.id(Long.valueOf(100001))
				.startTime(date)
				.available(false)
				.duration(15)
				.employeeFirstName("John")
				.employeeLastName("Snow")
				.client(client)
				.build();
		
		visitsEntity = new VisitEntity().builder()
				.id(Long.valueOf(100001))
				.startTime(date)
				.available(false)
				.duration(15)
				.employee(employeeEntity)
				.client(clientEntity)
				.build();
		
		employee = new Employee().builder()
				.id("1E")
				.firstName("John")
				.lastName("Snow")
				.phoneNumber(Long.valueOf(123456789))
				.emailAddress("john.snow@gmail.com")
				.visits(List.of(visit))
				.build();
		
		employeeEntity = new EmployeeEntity().builder()
				.id("1E")
				.firstName("John")
				.lastName("Snow")
				.phoneNumber(Long.valueOf(123456789))
				.emailAddress("john.snow@gmail.com")
				.visits(List.of(visitsEntity))
				.build();
	}
	
	@Test
	@DisplayName("Employee entity mapped correctly to DTO")
	public void ShouldMapEmployeeEntityToDTOCorrectly() {
		
		Employee _employee = ObjectMapperUtils.map(employeeEntity, Employee.class);
		
	}
	
	
	
}
