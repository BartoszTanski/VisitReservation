package com.bartosztanski.visitreservation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class HomeController {
	
	final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/status")
	public ResponseEntity<String> status() {
		
		LOGGER.info("Inside Homecontroller.status");
		String s = "Status: UP";
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
	
	@GetMapping("/home")
	public ModelAndView home() {
		
		LOGGER.info("Inside HomeController.home");
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("test.html");
	    return modelAndView;
	}
}
