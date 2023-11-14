package com.bartosztanski.visitreservation.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bartosztanski.visitreservation.dto.ClientRequest;
import com.bartosztanski.visitreservation.dto.VisitBookingRequest;
import com.bartosztanski.visitreservation.model.Client;
import com.bartosztanski.visitreservation.model.KeyValuePair;
import com.bartosztanski.visitreservation.model.Visit;
import com.bartosztanski.visitreservation.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/clients/")
public class ClientControllerThymeleaf {
		
	private final ClientService clientService;
		
	@GetMapping("/client")
	public ModelAndView singleClientForm(@RequestParam("clientId") String clientId) {	
		log.info("showing client: "+clientId);
		ModelAndView mav = new ModelAndView("single-client-view");
		Client client = clientService.getById(clientId);
		List<KeyValuePair> keyValuePairs = new ArrayList<>();
		keyValuePairs.add(new KeyValuePair("Id",client.getId()));
		keyValuePairs.add(new KeyValuePair("First Name",client.getFirstName()));
		keyValuePairs.add(new KeyValuePair("Last Name",client.getLastName()));
		keyValuePairs.add(new KeyValuePair("Email",client.getEmailAddress()));
		keyValuePairs.add(new KeyValuePair("Phone number",client.getPhoneNumber()));
		List<Visit> visits = client.getVisits();
		mav.addObject("keyValuePairs", keyValuePairs);
		mav.addObject("visits", visits);
		mav.addObject("clientId", client.getId());
		return mav;  
	}
	
	@GetMapping({"/all","/"})
	public ModelAndView getAll() {
		
		List<Client> clients= clientService.getAll();
		ModelAndView mav = new ModelAndView("list-clients");
		mav.addObject("clients", clients);
		return mav;  
	}
	
	@GetMapping("/addClientForm")
	public ModelAndView addClientForm() {	
		ModelAndView mav = new ModelAndView("add-client-form");
		ClientRequest newClientRequest = new ClientRequest();
		mav.addObject("client", newClientRequest);
		return mav;  
	}
	
	@PostMapping("/saveClient")
	public String saveEmployeeHandler(@ModelAttribute ClientRequest clientRequest) {	
		Client client = Client.builder()
				.firstName(clientRequest.getFirstName())
				.lastName(clientRequest.getLastName())
				.emailAddress(clientRequest.getEmailAddress())
				.phoneNumber(clientRequest.getPhoneNumber())
				.build();
		clientService.add(client);
		return "redirect:all";  
	}
	
	@GetMapping("/editClientForm")
	public ModelAndView editClientForm(@RequestParam("clientId") String clientId) {	
		log.info("editing client: "+clientId);
		ModelAndView mav = new ModelAndView("edit-client-form");
		Client client = clientService.getById(clientId);
		mav.addObject("employee", client);
		return mav;  
	}
	
	@PostMapping("/editClient")
	public String editClientHandler(@ModelAttribute Client client) {	
		clientService.update(client);
		return "redirect:all";  
	}
	
	@GetMapping("/delete")
	public String deleteClient(@RequestParam("clientId") String clientId) {	
		log.info("deleting client: "+clientId);
		clientService.deleteById(clientId); 
		return "redirect:/client/all";
	}
	
}
