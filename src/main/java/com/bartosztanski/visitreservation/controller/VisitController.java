package com.bartosztanski.visitreservation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bartosztanski.visitreservation.model.VisitBookingRequest;
import com.bartosztanski.visitreservation.model.Visit;
import com.bartosztanski.visitreservation.service.VisitService;

@RestController
@RequestMapping("/api/v1/visit")
public class VisitController {
	
	private final VisitService visitService;
	
	public VisitController(VisitService visitService) {
		this.visitService = visitService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Visit> getVisitById(@PathVariable("id") Long id) {
		Visit response = visitService.getVisitById(id);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/book")
	public ResponseEntity<Visit> bookNewVisit(@RequestBody VisitBookingRequest request) {
		Visit response = visitService.bookVisit(request);
		return ResponseEntity.ok(response);
	}
}
