package com.keepitclean.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "ADMINISTRATOR")
@Component
public class Administrator  implements Serializable{

	private static final long serialVersionUID = 5495284409734810633L;
	
	@Id
	@Column(name = "Administrator_Email", nullable = false)
	private String email;
	
	
	@Column(name="Admin_Key", nullable=false)
	private String adminKey;
	@Column(name="Admin_Type", nullable=false)
	private String adminType;
	
	@Column(name = "Administrator_First_Name", nullable = false)
	private String firstName;
	@Column(name = "Administrator_Last_Name", nullable = false)
	private String lastName;
	@Column(name = "Administrator_Password", nullable = false)
	private String password;
	
	public Administrator() {
		firstName = "";
		lastName = "";
		email = "";
		password = "";
		adminKey = "";
		adminType = "";
	}
	
	public Administrator(String firstName, 
	String lastName, String email, String password,
	String adminType, String adminKey) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.adminType = adminType;
		this.adminKey = adminKey;
	}
	

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public String getAdminType() { return adminType;}
	public void setAdminType(String adminType) { 
		this.adminType = adminType;
	}
	
	@Override
	public boolean equals(Object object) { 
		Administrator newAdministrator = (Administrator)object;
		
		return newAdministrator.getEmail().equals(email) 
		&& newAdministrator.getPassword().equals(password);
	}
	
	public String getAdminKey() {
		
		return adminKey;
	}
	
	
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setAdminKey(String adminKey) {
		this.adminKey = adminKey;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}

}

