package com.bartosztanski.visitreservation.dto;

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
public class EmployeeRequest {
	
	private UUID id;
	private String firstName;
	private String lastName;
	private Long phoneNumber;
	private String emailAddress;

}
