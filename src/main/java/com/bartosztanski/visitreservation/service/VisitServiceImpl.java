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
	public Visit add(Visit visit) {
		
		VisitEntity visitEntity = ObjectMapperUtils.map(visit, VisitEntity.class);
		Long id = visitRepository.save(visitEntity).getId();
		visit.setId(id);
		return visit;
	}

	@Override
	public Visit getById(Long id) {
		
		VisitEntity visitEntity = visitRepository.findById(id).get();
		Visit visit = ObjectMapperUtils.map(visitEntity, Visit.class);
		return visit;
	}

	@Override
	public Visit book(VisitBookingRequest request) 
			throws VisitNotAvailableException, NoSuchElementException {
		
		VisitEntity visitEntity = visitRepository.findById(request.getId()).orElseThrow();
		Visit visit = null;
		
		if (visitEntity.isAvailable()) {
			ClientEntity clientEntity = ObjectMapperUtils
					.map(clientService.add(request.getClient()),ClientEntity.class);
			
			visitEntity.setAvailable(false);
			visitEntity.setClient(clientEntity);
			visit = ObjectMapperUtils.map(visitRepository.save(visitEntity), Visit.class);
		} else throw new VisitNotAvailableException();
		
		return visit;
	}

	@Override
	public void delete(Client client, Long id) throws ClientDetailsNotMatchesException, NoSuchElementException{
		
		VisitEntity visitEntity = visitRepository.findById(id).orElseThrow();
		ClientEntity clientEntity = visitEntity.getClient();
		
		if(client.getFirstName().equalsIgnoreCase(clientEntity.getFirstName())
				&& client.getLastName().equalsIgnoreCase(clientEntity.getLastName())
				&& client.getEmailAddress().equalsIgnoreCase(clientEntity.getEmailAddress())
				&& client.getPhoneNumber()==clientEntity.getPhoneNumber()) {
			
			visitRepository.delete(visitEntity);
		}	else throw new ClientDetailsNotMatchesException();
	}

	@Override
	public Visit update(Visit visit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visit> getByEmployee(String employeeId) throws NoSuchElementException {
		List<Visit> visits = new ArrayList<>();
		EmployeeEntity employeeEntity = ObjectMapperUtils
				.map(employeeService.getById(employeeId),EmployeeEntity.class);
		
		visits = ObjectMapperUtils.mapAll(visitRepository.findByEmployee(employeeEntity),Visit.class);
		return visits;
	}

	@Override
	public List<Visit> getByClient(String clientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Visit> addAll(List<Visit> visit) {
		// TODO Auto-generated method stub
		return null;
	}

}
