package com.bartosztanski.visitreservation.service;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bartosztanski.visitreservation.entity.ClientEntity;
import com.bartosztanski.visitreservation.model.Client;
import com.bartosztanski.visitreservation.repository.ClientRepository;
import com.bartosztanski.visitreservation.utils.ObjectMapperUtils;

@Component
public class ClientServiceImpl implements ClientService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);
	private final ClientRepository clientRepository;
	
	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public Client add(Client client) {
		LOGGER.info("Inside ClientServiceimpl.add");
		if (client.getId()!=null) throw new IllegalArgumentException("CUSTOMER ALREADY HAS ID!");
		ClientEntity clientEntity = ObjectMapperUtils.map(client, ClientEntity.class);
	
		UUID id = clientRepository.save(clientEntity).getId();
		client.setId(id);
		return client;
	}
	
	@Override
	public Client getById(String clientId) {
		LOGGER.info("Inside ClientServiceimpl.getById");
		UUID id = UUID.fromString(clientId);
		ClientEntity clientEntity = clientRepository
				.findById(id)
				.orElseThrow(
					()-> new NoSuchElementException(
                        "NO CUSTOMER PRESENT WITH ID = " + clientId));
		
		Client client = ObjectMapperUtils.map(clientEntity, Client.class);
		return client;
	}

	@Override
	public void deleteById(String clientId) {
		UUID id = UUID.fromString(clientId);
		if (!clientRepository.existsById(id)) 
			throw new NoSuchElementException("NO CUSTOMER PRESENT WITH ID = " + clientId);
		
		clientRepository.deleteById(id);
	}

	@Override
	public Client update(Client client) throws NoSuchElementException {
		UUID id = client.getId();
		ClientEntity clientEntity = clientRepository
				.findById(id)
				.orElseThrow(
					()-> new NoSuchElementException(
		                "NO CUSTOMER PRESENT WITH ID = " + id));
		
		clientEntity = ObjectMapperUtils.map(client, ClientEntity.class);
		clientRepository.save(clientEntity);
		client = ObjectMapperUtils.map(clientEntity, Client.class);
		return client;
	}

	@Override
	public Client getByName(String fName, String lName) {
		ClientEntity clientEntity = clientRepository
				.findByFirstNameLastName(fName, lName)
				.orElseThrow(
					()-> new NoSuchElementException(
						"NO CUSTOMER PRESENT WITH NAME = "+fName+" "+lName));
		
		Client client = ObjectMapperUtils.map(clientEntity, Client.class);
		return client;
	}

	@Override
	public Client getByPhoneNr(Long phoneNumber) {
		ClientEntity clientEntity = clientRepository
				.findByPhoneNumber(phoneNumber)
				.orElseThrow(
					()-> new NoSuchElementException(
		               "NO CUSTOMER PRESENT WITH PHONE NUMBER = " + phoneNumber));
		
		Client client = ObjectMapperUtils.map(clientEntity, Client.class);
		return client;
	}

	@Override
	public Client getByEmail(String email) {
		ClientEntity clientEntity = clientRepository
				.findByEmailAddress(email)
				.orElseThrow(
				    ()-> new NoSuchElementException(
                       "NO CUSTOMER PRESENT WITH EMAIL = " + email));
		
		Client client = ObjectMapperUtils.map(clientEntity, Client.class);
		return client;
	}
	
	@Override
	public ClientEntity getEntityById(String clientId) {
		UUID id = UUID.fromString(clientId);
		ClientEntity clientEntity = clientRepository
				.findById(id)
				.orElseThrow(
					()-> new NoSuchElementException(
                        "NO CUSTOMER PRESENT WITH ID = " + clientId));
		
		return clientEntity;
	}

}
