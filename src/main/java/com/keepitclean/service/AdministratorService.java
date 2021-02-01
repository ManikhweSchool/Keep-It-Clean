package com.keepitclean.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keepitclean.model.Administrator;
import com.keepitclean.repository.AdministratorRepository;

@Service
public class AdministratorService {

	@Autowired
	private AdministratorRepository repository;
	
	public void save(Administrator administrator) { 
		repository.save(administrator); 
	}
	
	public long count() {return repository.count();}
	
	public void deleteById(String email) {
		repository.deleteById(email);
	}
	
	public Optional<Administrator> findAdministrator(String email){
		return (Optional<Administrator>) repository.findById(email);
	}
}

