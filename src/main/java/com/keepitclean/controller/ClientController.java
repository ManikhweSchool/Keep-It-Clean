package com.keepitclean.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.keepitclean.model.Client;
import com.keepitclean.service.ClientService;

@Controller
public class ClientController {

	// ../images/Web Pics/
	@Autowired
	private ClientService service;
	
	public void saveClient(Client client) { 
		service.saveClient(client);
	}
	public void deleteClient(String clientEmail) { 
		service.deleteClient(clientEmail);
	}
	public Optional<Client> findClient(String clientEmail){
		return service.findClient(clientEmail);
	}
	
	@RequestMapping(value = "/index", 
	method = RequestMethod.GET)
	public String visitIndex() { return "index";}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String visitAbout() { return "About";}
	
	@RequestMapping(value = "/contact", 
	method = RequestMethod.GET)
	public String visitContact() { return "Contact";}
	
	@RequestMapping(value = "/background", 
	method = RequestMethod.GET)
	public String visitBackground() { return "Background";}
}
