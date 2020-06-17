package com.example.springdatademo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springdatademo.entity.AuditLog;


@Service
public interface AuditService {

	
	public List<AuditLog> getAuditLogs(Integer pageNo, Integer pageSize, String actionName, LocalDate startDate,
			LocalDate endDate);


}
