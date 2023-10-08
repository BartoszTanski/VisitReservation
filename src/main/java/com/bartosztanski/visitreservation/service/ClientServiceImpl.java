package com.bartosztanski.visitreservation.service;

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
	public Client addClient(Client client) {
		ClientEntity clientEntity = ObjectMapperUtils.map(client, ClientEntity.class);
	
		UUID id = clientRepository.save(clientEntity).getId();
		client.setId(id);
		return client;
	}

	@Override
	public void deleteClientById(String clientId) {
		
		
	}

	@Override
	public Client updateClient(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client getClientByName(String fName, String lName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client getClientByPhoneNr(Long phoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client getClientByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
