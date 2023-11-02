package com.bartosztanski.visitreservation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bartosztanski.visitreservation.entity.ClientEntity;
import com.bartosztanski.visitreservation.model.Client;

@Service
public interface ClientService {
	
	public Client add(Client client);
	public void deleteById(String clientId);
	public Client update(Client client);
	public Client getByName(String fName, String lName);
	public Client getByPhoneNr(Long phoneNumber);
	public Client getByEmail(String email);
	public Client getById(String clientId);
	public ClientEntity getEntityById(String clientId);
	public List<Client> getAllClients();
}
