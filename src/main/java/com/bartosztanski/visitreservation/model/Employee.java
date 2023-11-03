package com.bartosztanski.visitreservation.model;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	private UUID id;
	private List<Visit> visits;
	private String firstName;
	private String lastName;
	private Long phoneNumber;
	private String emailAddress;

}
