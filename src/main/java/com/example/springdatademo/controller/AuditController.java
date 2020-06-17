package com.example.springdatademo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdatademo.entity.AuditLog;
import com.example.springdatademo.service.AuditService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/audit")
public class AuditController {
	Logger logger = LoggerFactory.getLogger(AuditController.class);
	
	@Autowired
	AuditService service;

	/**Method to fetch audit logs
	 * @param pageNo
	 * @param pageSize
	 * @param actionName
	 * @param startDate
	 * @param endDate
	 * @return ResponseEntity<List<AuditLog>>
	 */
	@ApiOperation(value ="View audit logs")
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<AuditLog>> getAuditLogs(
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(required=false) String actionName, 
            @RequestParam(required=false) String startDate,
            @RequestParam(required=false) String endDate
			) {
		logger.info("#####START:AuditController.getAuditLogs#####");
		return ResponseEntity.ok(service.getAuditLogs(pageNo,pageSize,actionName,startDate,endDate));
	}



}
