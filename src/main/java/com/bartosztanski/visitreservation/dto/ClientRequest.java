package com.bartosztanski.visitreservation.dto;

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
public class ClientRequest {
	
	private String firstName;
	private String lastName;
	private Long phoneNumber;
	private String emailAddress;
}
