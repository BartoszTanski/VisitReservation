package com.bartosztanski.visitreservation.model;

import java.util.Date;

import com.bartosztanski.visitreservation.entity.ClientEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Visit {
	
	private Long id;
	private Date startTime;
	private Client client;
	private int duration;
	private String employeeFirstName;
	private String employeeLastName;
	private boolean available;
	
}
