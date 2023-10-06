package com.bartosztanski.visitreservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bartosztanski.visitreservation.entity.VisitEntity;
import com.bartosztanski.visitreservation.model.VisitBookingRequest;
import com.bartosztanski.visitreservation.model.VisitRequest;
import com.bartosztanski.visitreservation.model.VisitResponse;
import com.bartosztanski.visitreservation.repository.VisitRepository;

@Service
public class VisitServiceImpl implements VisitService{
	
	private final VisitRepository visitRepository;
	
	public VisitServiceImpl(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}
	
	@Override
	public VisitResponse getVisitById(Long id) {
		VisitEntity visit = visitRepository.findById(id).get();
		VisitResponse response = null; //todo 
		return response;
	}

	@Override
	public VisitResponse bookVisit(VisitBookingRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteVisit(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VisitResponse updateVisit(VisitRequest visitRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VisitResponse> getVisitsByEmployee(String employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VisitResponse> getVisitsByClient(String clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VisitResponse> addNewVisits(List<VisitRequest> visitRequests) {
		// TODO Auto-generated method stub
		return null;
	}

}
