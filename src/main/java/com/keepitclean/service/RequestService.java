package com.keepitclean.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keepitclean.model.Request;
import com.keepitclean.repository.RequestRepository;

@Service
public class RequestService {

	@Autowired
	private RequestRepository repository;
	
	public List<Request> findAllRequests(){
		
		List<Request> list = repository.findAll();
		Collections.sort(list);
		
		return list;
	}
	public long count() {
		return repository.count();
	}
	public void saveReqeust(Request reqeust) { 
		repository.save(reqeust);
	}
	public Optional<Request> findRequest(long reqeustId){
		return repository.findById(reqeustId);
	}
	public void deleteReqeust(long requestId) { 
		repository.deleteById(requestId);
	}
}
