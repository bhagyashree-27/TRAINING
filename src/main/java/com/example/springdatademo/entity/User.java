package com.example.springdatademo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name= "USER")
public class User {

   
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer userId;
	
	@NotBlank(message = "First Name must not be Empty")
	String firstName;

	String lastName;
	
	String userRole;
	
	@NotNull(message="Years of experience must not be null")
	Integer yearsOfExperience;
	

	
	public User() {
		super();
	}


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


	public User(Integer userId, @NotBlank(message = "First Name must not be Empty") String firstName, String lastName,
			String userRole, @NotNull(message = "Years of experience must not be null") Integer yearsOfExperience) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userRole = userRole;
		this.yearsOfExperience = yearsOfExperience;
	}
	
	

}
