package com.example.springdatademo.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name= "AUDIT")
public class AuditLog  {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	
	String actionName;

	Integer actionBy;
	
	Integer actionOnUser;
	
	LocalDate actionDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	

	public AuditLog( String actionName, Integer actionBy, Integer actionOnUser,LocalDate actionDate) {
		super();
		this.actionName = actionName;
		this.actionBy = actionBy;
		this.actionDate = actionDate;
		this.actionOnUser =actionOnUser;
	}

	public Integer getActionBy() {
		return actionBy;
	}

	public void setActionBy(Integer actionBy) {
		this.actionBy = actionBy;
	}

	public LocalDate getActionDate() {
		return actionDate;
	}

	public void setActionDate(LocalDate actionDate) {
		this.actionDate = actionDate;
	}

	public Integer getActionOnUser() {
		return actionOnUser;
	}

	public void setActionOnUser(Integer actionOnUser) {
		this.actionOnUser = actionOnUser;
	}

	public AuditLog() {
		super();
	}
	

}
