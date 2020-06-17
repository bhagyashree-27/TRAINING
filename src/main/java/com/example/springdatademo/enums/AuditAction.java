package com.example.springdatademo.enums;

public enum AuditAction {
	
	GET("GET"),
	POST("POST"),
	UPDATE("UPDATE"),
	DELETE("DELETE"), ;
	
	private String actionName;
	
	AuditAction(String actionName){
		this.actionName= actionName;
	}
	
	
	public String getActionName(){
		return actionName;
	}

}
