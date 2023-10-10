package com.bartosztanski.visitreservation.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartosztanski.visitreservation.model.Client;
import com.bartosztanski.visitreservation.service.ClientService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/v1/client/")
public class ClientController { 
	
	private final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	private final ClientService clientService; 

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Client> addClient(@RequestBody Client client) {
		
		LOGGER.info("Inside ClientController.addClient");
		Client _client = clientService.add(client);
		return new ResponseEntity<>(_client,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable("id") String clientId) {
		
		clientService.deleteById(clientId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient(@RequestBody Client client) {
		
		Client _client = clientService.update(client);
		return new ResponseEntity<>(_client,HttpStatus.ACCEPTED);
	}
	@GetMapping("/search/name")
	public ResponseEntity<Client> getClientByName(
			@RequestParam("firstName") Optional<String> fName,
			@RequestParam("lastName") Optional<String> lName) {
		
		String firstName = fName.orElse(null);
		String lastName = lName.orElse(null);
		if(lastName==firstName&&firstName==null) throw new EntityNotFoundException();
		Client _client = clientService.getByName(firstName, lastName);
		if(_client == null) throw new EntityNotFoundException();
		return new ResponseEntity<>(_client, HttpStatus.FOUND); 
	}
	
	@GetMapping("/search/phone")
	public ResponseEntity<Client> getClientByPhoneNr(
			@RequestParam("phoneNumber") Optional<Long> phoneNumber) {
		
		Long _phoneNumber = phoneNumber.orElse(null); 
		if (_phoneNumber == null) throw new EntityNotFoundException();
		Client _client = clientService.getByPhoneNr(_phoneNumber);
		if (_client == null) throw new EntityNotFoundException();
		return new ResponseEntity<>(_client,HttpStatus.FOUND);
	}
}
