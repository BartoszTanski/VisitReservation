package com.bartosztanski.visitreservation.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.bartosztanski.visitreservation.entity.ClientEntity;
import com.bartosztanski.visitreservation.entity.EmployeeEntity;
import com.bartosztanski.visitreservation.model.Client;
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
	public Employee add(Employee employee) {
		if (employee.getId()!=null) throw new IllegalArgumentException("EMPLOYEE ALREADY HAS ID!");
		EmployeeEntity employeeEntity = ObjectMapperUtils.map(employee, EmployeeEntity.class);
		employeeEntity = employeeRepository.save(employeeEntity);
		employee = ObjectMapperUtils.map(employeeEntity, Employee.class);
		return employee;
	}
	
	@Override
	public Employee getById(String employeeId) {
		
		Employee employee = null;
		UUID id = UUID.fromString(employeeId);
		EmployeeEntity employeeEntity = employeeRepository
				.findById(id)
				.orElseThrow(
					()-> new NoSuchElementException(
						"NO EMPLOYEE PRESENT WITH ID = "+employeeId));
		
		employee = ObjectMapperUtils.map(employeeEntity, Employee.class);
		return employee;
	}

	@Override
	public void deleteById(String employeeId) {
		UUID id = UUID.fromString(employeeId);
		if (!employeeRepository.existsById(id))
			throw new NoSuchElementException(
					"NO EMPLOYEE PRESENT WITH ID = "+employeeId);
					
		employeeRepository.deleteById(id);		
	}

	@Override
	public Employee update(Employee employee) {
		
		if (!employeeRepository.existsById(employee.getId()))
			throw new NoSuchElementException(
					"NO EMPLOYEE PRESENT WITH ID = "+ employee.getId());
		
		EmployeeEntity employeeEntity = ObjectMapperUtils.map(employee, EmployeeEntity.class);
		employeeEntity = employeeRepository.save(employeeEntity);
		employee = ObjectMapperUtils.map(employeeEntity, Employee.class);
		return employee;
	}

	@Override
	public Employee getByName(String fName, String lName) {
		EmployeeEntity employeeEntity = employeeRepository
				.findByFirstNameAndLastName(fName, lName)
				.orElseThrow(
					()-> new NoSuchElementException(
						"NO EMPLOYEE PRESENT WITH NAME = "+fName+" "+lName));
		
		Employee employee = ObjectMapperUtils.map(employeeEntity, Employee.class);
		return employee;
	}
	
	@Override
	public List<Employee> getAll() {
		List<EmployeeEntity> employeeEntities = employeeRepository
				.findAll();
		
		List<Employee> employees = ObjectMapperUtils.mapAll(employeeEntities, Employee.class);
		return employees;
	}
	
	@Override
	public EmployeeEntity getEntityById(String employeeId) {

		UUID id = UUID.fromString(employeeId);
		EmployeeEntity employeeEntity = employeeRepository
				.findById(id)
				.orElseThrow(
					()-> new NoSuchElementException(
						"NO EMPLOYEE PRESENT WITH ID = "+employeeId));
		return employeeEntity;
	}

	@Override
	public Employee getByPhoneNr(Long phoneNumber) {
		EmployeeEntity employeeEntity = employeeRepository
				.findByPhoneNumber(phoneNumber)
				.orElseThrow(
					()-> new NoSuchElementException(
		               "NO EMPLOYEE PRESENT WITH PHONE NUMBER = " + phoneNumber));
		
		Employee employee = ObjectMapperUtils.map(employeeEntity, Employee.class);
		return employee;
	}
	

}
