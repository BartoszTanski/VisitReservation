package com.bartosztanski.visitreservation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bartosztanski.visitreservation.model.Endpoint;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
public class HomeController {

	
	@GetMapping("/status")
	public ResponseEntity<String> status() {
		
		String s = "Status: UP";
		log.info("Returned server status");
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
	@GetMapping({"/home","/"})
	public ModelAndView home() {
	    ModelAndView mav = new ModelAndView("home");
	    List<Endpoint> endpoints = getEndpoints();
	    mav.addObject("endpoints",endpoints);
	    return mav;
	}
	
	
	private static List<Endpoint> getEndpoints() {
		List<Endpoint> endpoints = new ArrayList<>();
		endpoints.add(new Endpoint("/employees/","GET","-","List of all employees"));
		return endpoints;
	}
}
