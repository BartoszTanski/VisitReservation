package com.bartosztanski.visitreservation.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.bartosztanski.visitreservation.dto.VisitBookingRequest;
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
	
	void delete(Long id);
	
	Visit update(Visit visit);
	
	List<Visit> getByEmployee(String employeeId);
	
	List<Visit> getByClient(String clientId);
	
	List<Visit> addAll(List<Visit> visit);

	void unBook(VisitBookingRequest request);

	List<Visit> getAll();

	List<Visit> getAllAvailable();

	//List<Visit> getAllAvailableByWeek(Date thisweek);

}
