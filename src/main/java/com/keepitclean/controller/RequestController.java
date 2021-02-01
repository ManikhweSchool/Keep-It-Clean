package com.keepitclean.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.keepitclean.model.Client;
import com.keepitclean.model.Request;
import com.keepitclean.service.RequestService;

@Controller
public class RequestController {

	// ../images/Web Pics/
	@Autowired
	private RequestService service;
	@Autowired
	private JobController jobController;
	
	
	@RequestMapping(value = "/surveyform", 
	method = RequestMethod.GET)
	public String requestForm(Model model) {
	
		model.addAttribute("request", new Request());
		return "SurveyRequestForm";
	}
	@RequestMapping(value = "/surveyform", 
	method = RequestMethod.POST)
	public String saveReqeust(
	@ModelAttribute(name="request")Request request, 
	Model model) { 
		
		String status = "";
		String page = "SurveyRequestForm";
		boolean requestAdded = false;
		
		Client client = request.getJob().getClient();
		
		if(client.getClientName().equals("")) {
			status += "Enter Name.";
		}
		else if(client.getClientSurname().equals("")) {
			status += "Enter Surname.";
		}
		else if(client.getClientEmail().equals("")) {
			status += "Enter Email.";
		}
		else if(client.getClientPhoneNumber()==null) {
			status += "Enter A Valid South African PhoneNumber.";
		}
		else if(client.getClientPhoneNumber().equals("")) {
			status += "Enter A South African PhoneNumber.";
		}
		else if(request.getJob().getJobNames().isEmpty()) {
			status += "Pick Atleast One Service.";
		}
		else if(request.getPreferedDate().equals("") &&
		request.getSelectedDay().equals("")) {
			status += "Pick Survey Date.";
		}
		else if(request.getJob().getJobAddress().getAddress().equals("")){
			status += "Enter The Address";
		}
		else {
			long count = service.count();
			
			jobController.saveJob(request.getJob());
			service.saveReqeust(request);
			
			if(count==service.count()) {
				status += "Couldn't Add Request On Database";
				
			}
			else {
				requestAdded = true;
				status += "Reqeust Added Successfully";
				page = "RequestConfirmation";
				
				model.addAttribute("reqeust", request);
				
			}
		}
		
		model.addAttribute("status", status);
		model.addAttribute("requestAddedOnDatabase", requestAdded);
		return page;
	}
	
	public Optional<Request> findRequest(long reqeustId){
		return service.findRequest(reqeustId);
	}
	
	public void deleteRequest(long requestId ,
	Model model) { 
			
		long initialSize = service.count();
		
		Optional<Request> request = service.findRequest(requestId);
		if(request.get().getIsSurveyDone())
			service.deleteReqeust(requestId);
		
		if(initialSize != service.count()) 
			model.addAttribute("requestRemovedFromDatabase", true);
		else 
			model.addAttribute("requestRemovedFromDatabase", false);
			
		model.addAttribute("requests", service.findAllRequests());
		
	}
	
	public void changeSurveyDone(long requestId,Model model) {

		Request request = service.findRequest(requestId).get();
						
		if(!request.getIsSurveyDone())
			request.setIsSurveyDone(!request.getIsSurveyDone());
		service.saveReqeust(request);
						
		model.addAttribute("requests", service.findAllRequests());
	}
	
	public List<Request> findAllRequests(){return service.findAllRequests();}
}

