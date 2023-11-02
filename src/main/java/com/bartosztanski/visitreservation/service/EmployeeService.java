package com.bartosztanski.visitreservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bartosztanski.visitreservation.entity.EmployeeEntity;
import com.bartosztanski.visitreservation.model.Employee;

@Service
public interface EmployeeService {
	
	public Employee add(Employee employee);
	public void deleteById(String employeeId);
	public Employee update(Employee employee);
	public Employee getById(String employeeId);
	public Employee getByName(String fName, String lName);
	public List<Employee> getAll();
	public EmployeeEntity getEntityById(String employeeId);
	public Employee getByPhoneNr(Long _phoneNumber);
}
