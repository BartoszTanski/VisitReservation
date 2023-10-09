package com.bartosztanski.visitreservation.service;

import org.springframework.stereotype.Service;

import com.bartosztanski.visitreservation.model.Employee;

@Service
public interface EmployeeService {
	
	public Employee add(Employee employee);
	public void delete(String employeeId);
	public Employee update(Employee employee);
	public Employee getById(String employeeId);
	public Employee getByName(String fName, String lName);
}
