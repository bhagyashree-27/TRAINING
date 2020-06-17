package com.example.springdatademo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

	
	/* (non-Javadoc)
	 * @see com.example.springdatademo.service.AuditService#getAuditLogs(java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<AuditLog> getAuditLogs(Integer pageNo, Integer pageSize, String actionName, String startDate,
			String endDate) {

		Pageable page;
		Page<AuditLog> pagedResult;
		page = PageRequest.of(pageNo, pageSize);
		
		
		if(startDate!=null && endDate!=null){
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
			LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);
			
			pagedResult = auditRepository.findByActionNameAndActionDateBetween(actionName,startDateTime,endDateTime,page);
			
		}
		else		pagedResult = auditRepository.findByActionName(actionName, page);

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<AuditLog>();
		}

	}

}
