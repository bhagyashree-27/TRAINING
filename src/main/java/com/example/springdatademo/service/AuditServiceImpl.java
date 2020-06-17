package com.example.springdatademo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.example.springdatademo.entity.AuditLog;
import com.example.springdatademo.repository.AuditRepository;

@Component
public class AuditServiceImpl implements AuditService {
	Logger logger = LoggerFactory.getLogger(AuditServiceImpl.class);

	@Autowired
	AuditRepository auditRepository;

	@Override
	public List<AuditLog> getAuditLogs(Integer pageNo, Integer pageSize, String actionName, LocalDate startDate,
			LocalDate endDate) {
		
		Pageable page;
		Page<AuditLog> pagedResult;
			page = PageRequest.of(pageNo, pageSize);

		
			pagedResult = auditRepository.findByActionName(actionName, page);
		

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<AuditLog>();
		}

	
	}

}
