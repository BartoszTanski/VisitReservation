package com.bartosztanski.visitreservation.dto;

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
	
	private String firstName;
	private String lastName;
	private Long phoneNumber;
	private String emailAddress;

}
