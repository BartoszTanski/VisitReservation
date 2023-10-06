package com.bartosztanski.visitreservation.service;

import org.springframework.stereotype.Service;

import com.bartosztanski.visitreservation.model.Client;

@Service
public interface ClientService {
	Client addClient(Client client);
	void deleteClientById(String clientId);
	Client updateClient(Client client);
	Client getClientByName(String fName, String lName);
	Client getClientByPhoneNr(Long phoneNumber);
	Client getClientByEmail(String email);
}
