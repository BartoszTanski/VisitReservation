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
		ClientEntity clientEntity = ObjectMapperUtils.map(client, ClientEntity.class);
	
		UUID id = clientRepository.save(clientEntity).getId();
		client.setId(id);
		return client;
	}

	@Override
	public void deleteById(String clientId) {
		UUID id = UUID.fromString(clientId);
		if (!clientRepository.existsById(id)) throw new NoSuchElementException();
		clientRepository.deleteById(id);
	}

	@Override
	public Client update(Client client) throws NoSuchElementException {
		ClientEntity clientEntity = clientRepository.findById(client.getId()).orElseThrow();
		clientEntity = ObjectMapperUtils.map(client, ClientEntity.class);
		clientRepository.save(clientEntity);
		return client;
	}

	@Override
	public Client getByName(String fName, String lName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client getByPhoneNr(Long phoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
