package com.example.springdatademo.enums;

public enum UserRole {
	
	ADMIN("ADMIN"),
	USER("USER");
	
	private String userRole;
	
	UserRole(String userRole){
		this.userRole= userRole;
	}
	
	
	public String getUserRole(){
		return userRole;
	}

}
