package com.example.springdatademo.dto;

public class UserDto {

	Integer yearsOfExperience;
	
	Long count;

	public Integer getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(Integer yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public UserDto(Integer yearsOfExperience, Long count) {
		super();
		this.yearsOfExperience = yearsOfExperience;
		this.count = count;
	}

	
	
	
}
