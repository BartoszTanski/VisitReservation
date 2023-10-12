package com.bartosztanski.visitreservation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Component;

import com.bartosztanski.visitreservation.entity.ClientEntity;
import com.bartosztanski.visitreservation.entity.EmployeeEntity;
import com.bartosztanski.visitreservation.entity.VisitEntity;
import com.bartosztanski.visitreservation.error.ClientDetailsNotMatchesException;
import com.bartosztanski.visitreservation.error.VisitNotAvailableException;
import com.bartosztanski.visitreservation.model.VisitBookingRequest;
import com.bartosztanski.visitreservation.model.Client;
import com.bartosztanski.visitreservation.model.Visit;
import com.bartosztanski.visitreservation.repository.VisitRepository;
import com.bartosztanski.visitreservation.utils.ObjectMapperUtils;

@Component
public class VisitServiceImpl implements VisitService{
	
	private final VisitRepository visitRepository;
	private final ClientService clientService;
	private final EmployeeService employeeService;
	
	public VisitServiceImpl(VisitRepository visitRepository,
				ClientService clientService,
				EmployeeService employeeService) {
		
		this.visitRepository = visitRepository;
		this.clientService = clientService;
		this.employeeService = employeeService;
	}
	
	@Override
	public Visit add(Visit visit) throws IllegalArgumentException {
		if (visit.getId()!=null) throw new IllegalArgumentException("VISIT ALREADY HAS ID!");
		VisitEntity visitEntity = ObjectMapperUtils.map(visit, VisitEntity.class);
		Long id = visitRepository.save(visitEntity).getId();
		visit.setId(id);
		return visit;
	}

	@Override
	public Visit getById(Long id) throws NoSuchElementException {
		
		VisitEntity visitEntity = visitRepository
				.findById(id)
				.orElseThrow(
					()-> new NoSuchElementException(
                      "NO VISIT PRESENT WITH ID = " + id));
		
		Visit visit = ObjectMapperUtils.map(visitEntity, Visit.class);
		return visit;
	}

	@Override
	public Visit book(VisitBookingRequest request) 
			throws VisitNotAvailableException, NoSuchElementException {
		
		VisitEntity visitEntity = visitRepository
				.findById(request.getId())
				.orElseThrow(
					()-> new NoSuchElementException(
	                    "NO VISIT PRESENT WITH ID = " + request.getId()));
		
		Visit visit = null;
		
		if (visitEntity.isAvailable()) {
			ClientEntity clientEntity = ObjectMapperUtils
					.map(clientService.add(request.getClient()),ClientEntity.class);
			
			visitEntity.setAvailable(false);
			visitEntity.setClient(clientEntity);
			visit = ObjectMapperUtils.map(visitRepository.save(visitEntity), Visit.class);
		} else throw new VisitNotAvailableException(
				"VISIT WITH ID = " + request.getId()+ " IS UNAVAILABLE");
		
		return visit;
	}

	@Override
	public void delete(Client client, Long id) throws ClientDetailsNotMatchesException, NoSuchElementException{
		
		VisitEntity visitEntity = visitRepository
				.findById(id)
				.orElseThrow(
					()-> new NoSuchElementException(
	                   "NO VISIT PRESENT WITH ID = " + id));
		
		ClientEntity clientEntity = visitEntity.getClient();
		
		if(client.getFirstName().equalsIgnoreCase(clientEntity.getFirstName())
				&& client.getLastName().equalsIgnoreCase(clientEntity.getLastName())
				&& client.getEmailAddress().equalsIgnoreCase(clientEntity.getEmailAddress())
				&& client.getPhoneNumber()==clientEntity.getPhoneNumber()) {
			
			visitRepository.delete(visitEntity);
		}	else throw new ClientDetailsNotMatchesException("VISIT DETAILS DO NOT MATCH CUSTOMER");
	}

	@Override
	public Visit update(Visit visit) throws NoSuchElementException {
		if (!visitRepository.existsById(visit.getId()))
			throw new NoSuchElementException(
					"NO VISIT PRESENT WITH ID = " + visit.getId());
		
		VisitEntity visitEntity = ObjectMapperUtils.map(visit, VisitEntity.class);
		visitEntity = visitRepository.save(visitEntity);
		visit = ObjectMapperUtils.map(visitEntity, Visit.class);
		return visit;
	}

	@Override
	public List<Visit> getByEmployee(String employeeId) {
		List<Visit> visits = new ArrayList<>();
		EmployeeEntity employeeEntity = employeeService.getEntityById(employeeId);
		List<VisitEntity> visitEntities = employeeEntity.getVisits();
		visits = ObjectMapperUtils.mapAll(visitEntities,Visit.class);
		return visits;
	}

	@Override
	public List<Visit> getByClient(String clientId) {
		List<Visit> visits = new ArrayList<>();
		ClientEntity clientEntity = clientService.getEntityById(clientId);
		List<VisitEntity> visitEntities = clientEntity.getVisits();
		visits = ObjectMapperUtils.mapAll(visitEntities,Visit.class);
		return visits;
	}

	@Override
	public List<Visit> addAll(List<Visit> visits) {
		
		List<Visit> _visits = new ArrayList<>();
		
		if (visits.stream().map(v -> v.getId()).allMatch(v -> v == null)) {
			List<VisitEntity> visitEntities = ObjectMapperUtils.mapAll(visits,VisitEntity.class);
			visitEntities = visitRepository.saveAll(visitEntities);
			_visits = ObjectMapperUtils.mapAll(visitEntities,Visit.class);
		} else throw new IllegalArgumentException("AT LEAST ONE OF VISITITS ALREADY HAVE ID");
		
		return _visits;
	}

}
