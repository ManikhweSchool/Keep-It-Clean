package com.keepitclean.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.keepitclean.model.Job;
import com.keepitclean.service.JobService;

@Controller
public class JobController {

	// ../images/Web Pics/
	@Autowired
	private JobService service;
	@Autowired
	private ClientController clientController;
	
	public void saveJob(Job job) { 
		clientController.saveClient(job.getClient());
		service.saveJob(job);
	}
	public void deleteJob(Long jobId) { service.deleteJob(jobId);}
	public long count() { return service.count();}
	public Optional<Job> findJob(Long jobId){ 
		return service.findJob(jobId);
	}
	
	@GetMapping(value="/testimonials")
	public String findEvidance() {
		return "Testimonial";
	}
}
