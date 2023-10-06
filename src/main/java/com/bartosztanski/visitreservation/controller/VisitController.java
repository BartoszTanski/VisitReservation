package com.bartosztanski.visitreservation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bartosztanski.visitreservation.model.VisitRequest;
import com.bartosztanski.visitreservation.model.VisitResponse;
import com.bartosztanski.visitreservation.service.VisitService;

@RestController
@RequestMapping("/api/v1/visit")
public class VisitController {
	
	private final VisitService visitService;
	
	public VisitController(VisitService visitService) {
		this.visitService = visitService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VisitResponse> getVisitById(@PathVariable("id") Long id) {
		VisitResponse response = visitService.getVisitById(id);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/book")
	public ResponseEntity<VisitResponse> bookNewVisit(@RequestBody VisitRequest visitRequest) {
		VisitResponse response = visitService.bookNewVisit(visitRequest);
		return ResponseEntity.ok(response);
	}
}
