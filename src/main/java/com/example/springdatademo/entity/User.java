package com.example.springdatademo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer userId;
	
	String firstName;

	String lastName;
	
	String userRole;
	
	Integer yearsOfExperience;
	

	public Integer getUserId() {
		return userId;
	}
	

	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getUserRole() {
		return userRole;
	}



	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}



	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}



	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	

}
