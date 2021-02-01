package com.keepitclean.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "CLIENT")
@Component
public class Client  implements Serializable{

	private static final long serialVersionUID = -6271404124273210412L;
	
	@Id
	@Column(name = "Client_Email", nullable = false)
	private String clientEmail;
	
	@Column(name = "Client_Name", nullable = false)
	private String clientName;
	
	@Column(name = "Client_Surname", nullable = false)
	private String clientSurname;
	
	@Column(name = "Client_Phone_Number", nullable = false)
	private String clientPhoneNumber;
	
	
	public Client() {
		clientName = "";
		clientSurname = "";
		clientEmail = "";
		clientPhoneNumber = "";
	}
	
	public Client(String name, String surname, String email, String phoneNumber) {
		
		clientName = name;
		clientSurname = surname;
		clientEmail = email;
		clientPhoneNumber = phoneNumber;
		
	}
	
	public String getClientName() { return clientName;}
	public String getClientSurname() { return clientSurname;}
	public String getClientEmail() { return clientEmail;}
	public String getClientPhoneNumber() { return clientPhoneNumber;}
	
	public void setClientName(String clientName) { this.clientName = clientName;}
	public void setClientSurname(String clientSurname) { this.clientSurname = clientSurname;}
	public void setClientEmail(String clientEmail) { this.clientEmail = clientEmail;}
	
	public void setClientPhoneNumber(String phoneNumber) {
		
		if(isPhoneNoValid(phoneNumber)) 
			clientPhoneNumber = phoneNumber;
		
		
	}
	
	private boolean isPhoneNoValid(String phoneNumber) {
		
		boolean isValid = true;
		
		if((phoneNumber.length() != 10 && phoneNumber.length() != 12) || 
		(!phoneNumber.startsWith("+27") &&
		!phoneNumber.startsWith("06") && 
		!phoneNumber.startsWith("07") &&
		!phoneNumber.startsWith("08")))
			isValid = !isValid;
		else {
			for(int i = 2; i < 10; i++) {
				if(!Character.isDigit(phoneNumber.charAt(i)))
					isValid = !isValid;
			}
		}
		
		return isValid;
	}
	
}
