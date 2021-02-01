package com.keepitclean.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keepitclean.model.Job;
import com.keepitclean.repository.JobRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository repository;
	
	public void saveJob(Job job) { repository.save(job);}
	public void deleteJob(Long jobId) { repository.deleteById(jobId);}
	public long count() { return repository.count();}
	public Optional<Job> findJob(Long jobId){ 
		return repository.findById(jobId);
	}
}
