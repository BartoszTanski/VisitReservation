package com.bartosztanski.visitreservation.dto;

import com.bartosztanski.visitreservation.model.Client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitBookingRequest {
	
	private Long id;
	private Client client;
	
}
