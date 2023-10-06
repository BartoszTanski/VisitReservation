package com.bartosztanski.visitreservation.model;

import java.util.Date;

import com.bartosztanski.visitreservation.entity.ClientEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VisitResponse {
	
	private Long id;
	private Date startTime;
	private ClientEntity client;
	private int duration;
	private String employeeFirstName;
	private String employeeLastName;
	private boolean available;
	
}
