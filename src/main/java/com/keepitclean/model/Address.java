package com.keepitclean.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

@Embeddable
@Component
public class Address {

	@Column(name = "Address", nullable = false)
	private String address;
	
	public Address() {
		
		address = "";
	}
	
	public Address(String address) {
		
		setAddress(address);
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() { return address;}
}
