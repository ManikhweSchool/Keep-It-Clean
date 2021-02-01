package com.keepitclean.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keepitclean.model.Client;
import com.keepitclean.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public void saveClient(Client client) { 
		repository.save(client);
	}
	public void deleteClient(String clientEmail) { 
		repository.deleteById(clientEmail);
	}
	public Optional<Client> findClient(String clientEmail){
		return ((ClientService) repository).findClient(clientEmail);
	}
	public long count() {return repository.count();}
}
