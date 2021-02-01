package com.keepitclean.controller;

import java.util.HashSet;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.keepitclean.model.Administrator;
import com.keepitclean.service.AdministratorService;

@Controller
public class AdministratorController {

	// ../images/Web Pics/
	@Autowired
	private AdministratorService service;
	@Autowired
	private RequestController controller;
	private final HashSet<Key> keys =new HashSet<>();
	
	public void save(Administrator administrator) { 
		service.save(administrator);
	}
	
	public void deleteById(String email) {
		service.deleteById(email);
	}
	
	@RequestMapping(value = "/admin/survey/attendance", 
	method = RequestMethod.GET)
	public String changeSurveyDone(@RequestParam long requestId, 
	@RequestParam String email,
	Model model) {
		
		controller.changeSurveyDone(requestId, model);
		model.addAttribute("administrator",
		service.findAdministrator(email).get());

		return "RequestsData";
	}
	
	public long count() { return service.count();}
	
	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	public String login(Model model) {
		Administrator administrator = new Administrator();
		model.addAttribute("administrator", administrator);
		return "Login";
	}
	
	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String login(@ModelAttribute(name="administrator")Administrator administrator, Model model) { 
		
		String page;
		String status;
		
		Optional<Administrator> retrievedAdmin = findAdministrator(administrator.getEmail());
		if(retrievedAdmin.isPresent()) {
			if(!retrievedAdmin.get().equals(administrator)) {
				model.addAttribute("LogInPassed", false);
				status = "Incorrect Password";
				page = "Login";
			}
			else {
				model.addAttribute("LogInPassed", true);
				model.addAttribute("administrator", retrievedAdmin.get());
				model.addAttribute("requests", controller.findAllRequests());
				page = "RequestsData";
				status = "LogIn Passed";
			}
		}
		else {
			model.addAttribute("LogInPassed", false);
			status = "No Such Administrator";
			page = "Login";
			
			
		}
		
		model.addAttribute("status", status);
		return page;
	}
	
	@RequestMapping(value = "/admin/signin", method = RequestMethod.GET)
	public String signIn(Model model) {
		
		if(keys.isEmpty())
			for(int i = 0; i < 10;i++) {
				Key key = new Key();
				keys.add(key);
				System.out.println("Key " + i + " : " + key.getKey() + "\tKey Type " + key.getType());
			}
		
		Administrator administrator = new Administrator();
		model.addAttribute("administrator", administrator);
		return "SignIn";
	}
	
	
	@RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
	public String deleteReqeust(@RequestParam long requestId, 
	@RequestParam String email,Model model) {
		
		model.addAttribute("administrator",
		service.findAdministrator(email).get());
		controller.deleteRequest(requestId,model);
		
		return "RequestsData";
	}
	
	@RequestMapping(value = "/admin/signin", method = RequestMethod.POST)
	public String signIn(HttpServletRequest request, @ModelAttribute(name="administrator")Administrator administrator, Model model) { 
				
		String status = "";
		String page = "";
		
		if(findAdministrator(administrator.getEmail()).isPresent()) {
			model.addAttribute("SignInPassed", false);
			status = "Admin Email Exist";
			page = "SignIn";
				
		}
		else {
			Key adminKey = null;
			boolean keyMatch = false;
			for(Key key : keys)
				if(key.getKey().equals(administrator.getAdminKey())) {
					keyMatch = true;
					adminKey = key;
				}
			
			if(!keyMatch) {
				model.addAttribute("SignInPassed", false);
				status = "Invalid Registration Code";
				page = "SignIn";
			}
			else if(!adminKey.getType().equals(
			administrator.getAdminType())) {
				model.addAttribute("SignInPassed", false);
				status = "Invalid Admin Type";
				page = "SignIn";
			}
			else {
				String password = request.getParameter("password");
				String cpassword = request.getParameter("cpassword");
				if(!password.equals(cpassword)) {
					model.addAttribute("SignInPassed", false);
					status = "Password Doesn't Match";
					page = "SignIn";
				}
				else {
					model.addAttribute("SignInPassed", true);
					status = "SignIn Passed";
					service.save(administrator);
					model.addAttribute("administrator", administrator);
					model.addAttribute("requests", controller.findAllRequests());
					page = "RequestsData";
					
				}
				
			}
					
		}
		model.addAttribute("status", status);
		return page;
	}
	
	private static class Key{
		
		private String key = "";
		private String type = "";
		private static int count = 0;
		
		public Key() {
			generateKey();
			generateType();
			count++;
		}
		
		private void generateType() {
			
			if(count==0)
				type="Type|Admin";
			else if(count%5==0)
				type="Type||Admin";
			else
				type="Type|||Admin";
		}
		
		private void generateKey() {
			
			String abc = "abcdefghijklmnopqrstuvwxyz";
			String chars = "!@#$%^&*()-_=+]}[{,<.>/?/*+";
			String numbers = "0123456789";
			
			for(int i = 0; i < 15;i++) {
				int setNumber = (int)(Math.random()*3);
				switch(setNumber) {
				case 0 :
					key += abc.charAt((int)(Math.random()*abc.length()));
					break;
				case 1 :
					key += chars.charAt((int)(Math.random()*chars.length()));
					break;
				default :
					key += numbers.charAt((int)(Math.random()*numbers.length()));
				}
			}
		}
		
		public String getKey() { return key;}
		public String getType() { return type;}
		
	}
	
	public Optional<Administrator> findAdministrator(String email){
		return service.findAdministrator(email);
	}
	
	@RequestMapping(value = "/admin/index", 
	method = RequestMethod.GET)
	public String visitIndex() { return "index";}
	
	@RequestMapping(value = "/admin/about", 
	method = RequestMethod.GET)
	public String visitAbout() { return "About";}
			
	@RequestMapping(value = "/admin/contact", 
	method = RequestMethod.GET)
	public String visitContact() { return "Contact";}
}

