package com.example.springdatademo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springdatademo.dto.UserDto;
import com.example.springdatademo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Page<User> findAll(Pageable pageable);

	@Query(value = "SELECT new com.example.springdatademo.dto.UserDto(u.yearsOfExperience,count(*)) from User u group by yearsOfExperience")
	List<UserDto> groupUsersByExperience();

	List<User> findAllOrderByFirstName(String firstName);

}
