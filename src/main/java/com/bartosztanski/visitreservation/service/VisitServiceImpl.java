package com.bartosztanski.visitreservation.service;

import java.util.List;

import com.bartosztanski.visitreservation.entity.VisitEntity;
import com.bartosztanski.visitreservation.model.VisitBookingRequest;
import com.bartosztanski.visitreservation.model.VisitRequest;
import com.bartosztanski.visitreservation.model.Visit;
import com.bartosztanski.visitreservation.repository.VisitRepository;

public class VisitServiceImpl implements VisitService{
	
	private final VisitRepository visitRepository;
	
	public VisitServiceImpl(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}
	
	@Override
	public Visit getVisitById(Long id) {
		VisitEntity visit = visitRepository.findById(id).get();
		Visit response = null; //todo 
		return response;
	}

	@Override
	public Visit bookVisit(VisitBookingRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteVisit(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Visit updateVisit(VisitRequest visitRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visit> getVisitsByEmployee(String employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visit> getVisitsByClient(String clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visit> addNewVisits(List<VisitRequest> visitRequests) {
		// TODO Auto-generated method stub
		return null;
	}

}
