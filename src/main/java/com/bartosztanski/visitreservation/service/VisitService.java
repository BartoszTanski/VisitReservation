package com.bartosztanski.visitreservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bartosztanski.visitreservation.model.VisitBookingRequest;
import com.bartosztanski.visitreservation.model.VisitRequest;
import com.bartosztanski.visitreservation.model.VisitResponse;

@Service
public interface VisitService {

	VisitResponse getVisitById(Long id);

	VisitResponse bookVisit(VisitBookingRequest request);
	
	boolean deleteVisit(Long id);
	
	VisitResponse updateVisit(VisitRequest visitRequest);
	
	List<VisitResponse> getVisitsByEmployee(String employeeId);
	
	List<VisitResponse> getVisitsByClient(String clientId);
	
	List<VisitResponse> addNewVisits(List<VisitRequest> visitRequests);

}
