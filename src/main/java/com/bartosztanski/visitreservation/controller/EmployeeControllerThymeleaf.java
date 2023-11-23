package com.bartosztanski.visitreservation.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bartosztanski.visitreservation.dto.EmployeeRequest;
import com.bartosztanski.visitreservation.model.Employee;
import com.bartosztanski.visitreservation.model.KeyValuePair;
import com.bartosztanski.visitreservation.model.Visit;
import com.bartosztanski.visitreservation.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/employees/")
public class EmployeeControllerThymeleaf {
		
	private final EmployeeService employeeService;
		
	@GetMapping("/employee")
	public ModelAndView singleEmployeeForm(@RequestParam("employeeId") String employeeId) {	
		log.info("showing employee: "+employeeId);
		ModelAndView mav = new ModelAndView("single-employee-view");
		Employee employee = employeeService.getById(employeeId);
		List<KeyValuePair> keyValuePairs = new ArrayList<>();
		keyValuePairs.add(new KeyValuePair("Id",employee.getId()));
		keyValuePairs.add(new KeyValuePair("First Name",employee.getFirstName()));
		keyValuePairs.add(new KeyValuePair("Last Name",employee.getLastName()));
		keyValuePairs.add(new KeyValuePair("Email",employee.getEmailAddress()));
		keyValuePairs.add(new KeyValuePair("Phone number",employee.getPhoneNumber()));
		employee.getVisits()
			.sort((Visit v1, Visit v2) -> v1.getStartTime()
						.compareTo(v2.getStartTime()));
		
		List<Visit> visits = employee.getVisits();				
		mav.addObject("keyValuePairs", keyValuePairs);
		mav.addObject("employeeId", employeeId);
		mav.addObject("visits", visits);
		return mav;  
	}
	
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
