package com.bartosztanski.visitreservation.model;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VisitRequest {
		
	private Date startTime;
	private int duration;
	private String employeeFirstName;
	private String employeeLastName;
	private boolean available;

}
