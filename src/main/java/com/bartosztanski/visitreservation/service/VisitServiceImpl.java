package com.bartosztanski.visitreservation.service;

import com.bartosztanski.visitreservation.entity.VisitEntity;
import com.bartosztanski.visitreservation.model.VisitRequest;
import com.bartosztanski.visitreservation.model.VisitResponse;
import com.bartosztanski.visitreservation.repository.VisitRepository;

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
	public VisitResponse bookNewVisit(VisitRequest visitRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
