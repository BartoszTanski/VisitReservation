package com.bartosztanski.visitreservation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bartosztanski.visitreservation.dto.EmployeeRequest;
import com.bartosztanski.visitreservation.model.Client;
import com.bartosztanski.visitreservation.model.Employee;
import com.bartosztanski.visitreservation.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/employees/")
public class EmployeeControllerThymeleaf {
		
	private final EmployeeService employeeService;
		
//	@GetMapping("/{id}")
//	public ResponseEntity<Employee> getById(
//			@PathVariable("id") String employeeId) {
//		
//		Employee _employee = employeeService.getById(employeeId);
//		if(_employee == null) throw new EntityNotFoundException();
//		return new ResponseEntity<>(_employee, HttpStatus.OK); 
//	}
	@GetMapping({"/all","/"})
	public ModelAndView getAll() {
		
		List<Employee> employees= employeeService.getAll();
		ModelAndView mav = new ModelAndView("list-employees");
		mav.addObject("employees", employees);
		return mav;  
	}
	
	@GetMapping("/addEmployeeForm")
	public ModelAndView addEmployeeForm() {	
		ModelAndView mav = new ModelAndView("add-employee-form");
		EmployeeRequest newEmployeeRequest = new EmployeeRequest();
		mav.addObject("employee", newEmployeeRequest);
		return mav;  
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployeeHandler(@ModelAttribute EmployeeRequest employeeRequest) {	
		Employee employee = Employee.builder()
				.firstName(employeeRequest.getFirstName())
				.lastName(employeeRequest.getLastName())
				.emailAddress(employeeRequest.getEmailAddress())
				.phoneNumber(employeeRequest.getPhoneNumber())
				.build();
		employeeService.add(employee);
		return "redirect:all";  
	}
	
	@GetMapping("/editEmployeeForm")
	public ModelAndView editEmployeeForm(@RequestParam("employeeId") String employeeId) {	
		log.info("editing employee: "+employeeId);
		ModelAndView mav = new ModelAndView("edit-employee-form");
		Employee employee = employeeService.getById(employeeId);
		mav.addObject("employee", employee);
		return mav;  
	}
	
	@PostMapping("/editEmployee")
	public String editEmployeeHandler(@ModelAttribute Employee employee) {	
		employeeService.update(employee);
		return "redirect:all";  
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") String employeeId) {	
		log.info("deleting employee: "+employeeId);
		employeeService.deleteById(employeeId); 
		return "redirect:/employees/all";
	}
}
