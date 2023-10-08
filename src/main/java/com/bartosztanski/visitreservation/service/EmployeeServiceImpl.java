package com.bartosztanski.visitreservation.service;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bartosztanski.visitreservation.entity.EmployeeEntity;
import com.bartosztanski.visitreservation.model.Employee;
import com.bartosztanski.visitreservation.repository.EmployeeRepository;
import com.bartosztanski.visitreservation.utils.ObjectMapperUtils;

@Component
public class EmployeeServiceImpl implements EmployeeService{

	private final EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public Employee addEmployee(Employee employee) {
		EmployeeEntity employeeEntity = ObjectMapperUtils.map(employee, EmployeeEntity.class);
		UUID id = employeeRepository.save(employeeEntity).getId();
		employee.setId(id);
		return employee;
	}
	
	@Override
	public Employee getEmployeeById(String employeeId) {
		Employee employee = null;
		EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElseThrow();
		employee = ObjectMapperUtils.map(employeeEntity, Employee.class);
		return employee;
	}

	@Override
	public void deleteEmployee(String employeeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployeeByName(String fName, String lName) {
		// TODO Auto-generated method stub
		return null;
	}

}
