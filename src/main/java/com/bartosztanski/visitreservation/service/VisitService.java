package com.bartosztanski.visitreservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bartosztanski.visitreservation.model.VisitBookingRequest;
import com.bartosztanski.visitreservation.model.Visit;

@Service
public interface VisitService {
	
	Visit addVisit(Visit visit);

	Visit getVisitById(Long id);

	Visit bookVisit(VisitBookingRequest request);
	
	boolean deleteVisit(Long id);
	
	Visit updateVisit(Visit visit);
	
	List<Visit> getVisitsByEmployee(String employeeId);
	
	List<Visit> getVisitsByClient(String clientId);
	
	List<Visit> addNewVisits(List<Visit> visit);

}
