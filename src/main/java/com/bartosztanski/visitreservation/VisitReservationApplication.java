package com.bartosztanski.visitreservation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VisitReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitReservationApplication.class, args);
		
		final Logger LOGGER = LoggerFactory.getLogger(VisitReservationApplication.class);
		LOGGER.info("Application Started Successfully!");
	}
	
}
