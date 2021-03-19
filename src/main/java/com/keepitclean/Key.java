package com.keepitclean;

import java.io.Serializable;

public class Key implements Serializable{
	
	private static final long serialVersionUID = 3943400470630454530L;
	
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
	
	@Override
	public String toString() {
		return "Key : " + key + "\tType : " + type; 
	}
	
	@Override
	public boolean equals(Object object) {
		
		Key anotherKey = (Key)object;
		return anotherKey.getKey().equals(key) &&
		anotherKey.getType().equals(type);
	}
	
	public String getKey() { return key;}
	public String getType() { return type;}
	
}
