package com.bartosztanski.visitreservation.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bartosztanski.visitreservation.entity.VisitEntity;
import com.bartosztanski.visitreservation.model.VisitBookingRequest;
import com.bartosztanski.visitreservation.model.Visit;
import com.bartosztanski.visitreservation.repository.VisitRepository;
import com.bartosztanski.visitreservation.utils.ObjectMapperUtils;

@Component
public class VisitServiceImpl implements VisitService{
	
	private final VisitRepository visitRepository;
	
	public VisitServiceImpl(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}
	
	@Override
	public Visit addVisit(Visit visit) {
		VisitEntity visitEntity = ObjectMapperUtils.map(visit, VisitEntity.class);
		Long id = visitRepository.save(visitEntity).getId();
		visit.setId(id);
		return visit;
	}

	@Override
	public Visit getVisitById(Long id) {
		VisitEntity visitEntity = visitRepository.findById(id).get();
		Visit visit = ObjectMapperUtils.map(visitEntity, Visit.class);
		return visit;
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
	public Visit updateVisit(Visit visit) {
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
	public List<Visit> addNewVisits(List<Visit> visit) {
		// TODO Auto-generated method stub
		return null;
	}

}
