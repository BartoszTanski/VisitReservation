package com.bartosztanski.visitreservation.service;

import org.springframework.stereotype.Service;

import com.bartosztanski.visitreservation.model.Employee;

@Service
public interface EmployeeService {
	
	public Employee addEmployee(Employee employee);
	public void deleteEmployee(String employeeId);
	public Employee updateEmployee(Employee employee);
	public Employee getEmployeeById(String employeeId);
	public Employee getEmployeeByName(String fName, String lName);
}
