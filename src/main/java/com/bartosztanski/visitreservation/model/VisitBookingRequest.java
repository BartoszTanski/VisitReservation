package com.bartosztanski.visitreservation.model;

import com.bartosztanski.visitreservation.entity.ClientEntity;

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
	private ClientEntity client;
	
}
