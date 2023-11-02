package com.bartosztanski.visitreservation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bartosztanski.visitreservation.model.Client;
import com.bartosztanski.visitreservation.model.Employee;
import com.bartosztanski.visitreservation.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/employeethyme/")
public class EmployeeControllerThymeleaf {
		
	private final EmployeeService employeeService;
	
	@PostMapping("/add")
	public ResponseEntity<Employee> add(@RequestBody Employee employee) {
		
		log.info("Inside ClientController.addClient");
		Employee _employee = employeeService.add(employee);
		return new ResponseEntity<>(_employee,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") String employeeId) {
		
		employeeService.deleteById(employeeId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		
		Employee _employee = employeeService.update(employee);
		return new ResponseEntity<>(_employee,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getById(
			@PathVariable("id") String employeeId) {
		
		Employee _employee = employeeService.getById(employeeId);
		if(_employee == null) throw new EntityNotFoundException();
		return new ResponseEntity<>(_employee, HttpStatus.OK); 
	}
	@GetMapping("/all")
	public ModelAndView getAll() {
		
		List<Employee> employees= employeeService.getAll();
		ModelAndView mav = new ModelAndView("list-employees");
		mav.addObject("employees", employees);
		return mav;  
	}

	@GetMapping("/search/name")
	public ResponseEntity<Employee> getByName(
			@RequestParam("firstName") Optional<String> fName,
			@RequestParam("lastName") Optional<String> lName) {
		
		String firstName = fName.orElse(null);
		String lastName = lName.orElse(null);
		if(lastName==firstName&&firstName==null) throw new EntityNotFoundException();
		Employee _employee = employeeService.getByName(firstName, lastName);
		if(_employee == null) throw new EntityNotFoundException();
		return new ResponseEntity<>(_employee, HttpStatus.OK); 
	}
	
	@GetMapping("/search/phone")
	public ResponseEntity<Employee> getByPhoneNr(
			@RequestParam("phoneNumber") Optional<Long> phoneNumber) {
		
		Long _phoneNumber = phoneNumber.orElse(null); 
		if (_phoneNumber == null) throw new EntityNotFoundException();
		Employee _employee = employeeService.getByPhoneNr(_phoneNumber);
		if (_employee == null) throw new EntityNotFoundException();
		return new ResponseEntity<>(_employee,HttpStatus.OK);
	}
}
