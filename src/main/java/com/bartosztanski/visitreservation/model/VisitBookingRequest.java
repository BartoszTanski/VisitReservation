package com.bartosztanski.visitreservation.model;

import com.bartosztanski.visitreservation.entity.ClientEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VisitBookingRequest {
	
	private Long id;
	private ClientEntity client;
	
}
