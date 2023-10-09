package com.bartosztanski.visitreservation.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.bartosztanski.visitreservation.model.VisitBookingRequest;
import com.bartosztanski.visitreservation.error.ClientDetailsNotMatchesException;
import com.bartosztanski.visitreservation.error.VisitNotAvailableException;
import com.bartosztanski.visitreservation.model.Client;
import com.bartosztanski.visitreservation.model.Visit;

@Service
public interface VisitService {
	
	Visit add(Visit visit);

	Visit getById(Long id);

	Visit book(VisitBookingRequest request) throws VisitNotAvailableException, NoSuchElementException;
	
	void delete(Client client, Long id) throws ClientDetailsNotMatchesException, NoSuchElementException;
	
	Visit update(Visit visit);
	
	List<Visit> getByEmployee(String employeeId);
	
	List<Visit> getByClient(String clientId);
	
	List<Visit> addAll(List<Visit> visit);

}
