package com.bartosztanski.visitreservation.model;

import java.util.Date;

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
	private Employee employee;
	private VisitType type;
	private MedicalField field;
	private boolean available;
	
}
