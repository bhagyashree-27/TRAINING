package com.example.springdatademo.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springdatademo.entity.AuditLog;

@Repository
public interface AuditRepository extends JpaRepository<AuditLog, Integer> {

	Page<AuditLog> findByActionName(String actionName, Pageable page);

	Page<AuditLog> findByActionNameAndActionDateBetween(String actionName,LocalDateTime startDateTime, LocalDateTime endDateTime,
			Pageable page);

	
}
	