package com.bartosztanski.visitreservation.service;

import org.springframework.stereotype.Service;

import com.bartosztanski.visitreservation.model.Client;

@Service
public interface ClientService {
	Client add(Client client);
	void deleteById(String clientId);
	Client update(Client client);
	Client getByName(String fName, String lName);
	Client getByPhoneNr(Long phoneNumber);
	Client getByEmail(String email);
}
