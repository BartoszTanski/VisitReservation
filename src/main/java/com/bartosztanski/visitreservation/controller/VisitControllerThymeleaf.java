package com.bartosztanski.visitreservation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bartosztanski.visitreservation.dto.EmployeeRequest;
import com.bartosztanski.visitreservation.dto.VisitBookingRequest;
import com.bartosztanski.visitreservation.dto.VisitCreateRequest;
import com.bartosztanski.visitreservation.error.ClientDetailsNotMatchesException;
import com.bartosztanski.visitreservation.error.VisitNotAvailableException;
import com.bartosztanski.visitreservation.model.Client;
import com.bartosztanski.visitreservation.model.Employee;
import com.bartosztanski.visitreservation.model.Visit;
import com.bartosztanski.visitreservation.model.VisitsTableCell;
import com.bartosztanski.visitreservation.model.VisitsTableRow;
import com.bartosztanski.visitreservation.service.DateService;
import com.bartosztanski.visitreservation.service.EmployeeService;
import com.bartosztanski.visitreservation.service.VisitService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/visits/")
public class VisitControllerThymeleaf {
	
	private final VisitService visitService;
	private final EmployeeService employeeService;
	private final DateService dateService;
	
	@GetMapping("/visit")
	public ModelAndView getVisitById(@RequestParam("visitId") Long id) {
		
		ModelAndView mav = new ModelAndView("single-visit-view");
		Visit visit = visitService.getById(id);
		visit.getEmployee().setVisits(null);
		mav.addObject("visit", visit);
		return mav;  
		
	}
	
	@GetMapping("/addVisitForm")
	public ModelAndView addVisitsForm() {	
		ModelAndView mav = new ModelAndView("add-visit-form");
		VisitCreateRequest visitCreateRequest = new VisitCreateRequest();
		mav.addObject("visitCreateRequest", visitCreateRequest);
		List<Employee> employees =  employeeService.getAll();
		mav.addObject("employees", employees);
		return mav;  
	}
	
	@PostMapping("/saveVisit") 
	public String saveVisitHandler(@ModelAttribute VisitCreateRequest visitCreateRequest) {	
		
		Employee employee = employeeService.getById(visitCreateRequest.getEmployeeId());
		
		Visit visit = Visit.builder()
				.startTime(visitCreateRequest.getStartTime())
				.duration(visitCreateRequest.getDuration())
				.field(visitCreateRequest.getField())
				.type(visitCreateRequest.getType())
				.employee(employee)
				.available(true)
				.build();
		Long visitId = visitService.add(visit).getId();
		log.info("New visit saved: "+visitId);
		return "redirect:all";   
	}
	
	@GetMapping({"/all","/"}) 
	public ModelAndView getAll() {
		
		List<Visit> visits = visitService.getAllAvailable();
		ModelAndView mav = new ModelAndView("list-visits-booking");
		mav.addObject("visits", visits);
		return mav;  
	}
	
	@GetMapping("/bookVisitForm")
	public ModelAndView bookVisitForm(@RequestParam("visitId") Long visitId) {	
		ModelAndView mav = new ModelAndView("book-visit-form");
		Client client = new Client();
		VisitBookingRequest visitBookingRequest = new VisitBookingRequest(visitId, client);
		mav.addObject("visitBookingRequest", visitBookingRequest);
		return mav;  
	}
	
	@PostMapping("/book") 
	public String book(@ModelAttribute VisitBookingRequest request) 
			throws NoSuchElementException, VisitNotAvailableException {	
		Long visitId = visitService.book(request).getId();
		log.info("New visit booked: "+visitId);
		return "redirect:all";  
	}
	
	@GetMapping("/unBookVisitForm")
	public ModelAndView unBookVisitForm(@RequestParam("visitId") Long visitId) {	
		ModelAndView mav = new ModelAndView("unbook-visit-form");
		Client client = new Client();
		VisitBookingRequest visitBookingRequest = new VisitBookingRequest(visitId, client);
		mav.addObject("visitBookingRequest", visitBookingRequest);
		return mav;  
	} 
	 
	@PostMapping("/unBook") 
	public String unBook(@ModelAttribute VisitBookingRequest request) 
			throws NoSuchElementException, VisitNotAvailableException {	
	
		visitService.unBook(request);
		log.info("Visit unBooked: "+request.getId());
		return "redirect:all";  
	}
	@DeleteMapping("/delete/{id}")
	public String deleteVisit(@RequestParam("visitId") Long visitId) {
		
		visitService.delete(visitId); 
		return "redirect:all";
	}

}
