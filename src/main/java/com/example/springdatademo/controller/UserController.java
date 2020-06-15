package com.example.springdatademo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdatademo.dto.UserDto;
import com.example.springdatademo.entity.User;
import com.example.springdatademo.exception.UserNotFoundException;
import com.example.springdatademo.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
public class UserController {
	Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService service;

	@ApiOperation("Add a new User")
	@PostMapping(produces = "application/json")
	public ResponseEntity<User> addUser(@RequestBody  @Valid User demoUser) {
		
		return new ResponseEntity<>(service.addUser(demoUser), HttpStatus.CREATED);
	}

	@ApiOperation(value ="View the list of all Users")
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<User>> getUsers(
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(required=false) Integer yearsOfExperienceMin, 
            @RequestParam(required=false) Integer yearsOfExperienceMax,
            @RequestParam(defaultValue = "userId")String sortBy
			) {
		logger.info("#####START:UserController.getUsers#####");
		return ResponseEntity.ok(service.getUsers(pageNo,pageSize,yearsOfExperienceMin,yearsOfExperienceMax,sortBy));
	}

	@ApiOperation("View a User by userId")
	@GetMapping(value = "/{userId}",produces = "application/json")
	public ResponseEntity<User> getUserByUserId(@PathVariable("userId") Integer userId) throws UserNotFoundException {
		logger.info("#####START:UserController.getUserByuserId#####");
		return ResponseEntity.ok(service.getUserById(userId));
	}

	@ApiOperation("Update a User")
	@PutMapping(value="/{userId}",produces = "application/json")
	public ResponseEntity<User> updateUser( @PathVariable("userId") Integer userId,@RequestBody  @Valid User demoUser) {
		logger.info("#####START:UserController.updateUser#####");
		return ResponseEntity.ok(service.updateUser(demoUser));
	}
	
	@ApiOperation("Delete a User")
	@DeleteMapping("/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) throws UserNotFoundException {
		logger.info("#####START:UserController.deleteUser#####");
		service.deleteUser(userId);
		return "Record deleted successfully";
		
	}
	
	@ApiOperation(value ="Count Users By Years Of Experience")
	@GetMapping(value= "/count",produces = "application/json")
	public ResponseEntity<List<UserDto>> groupUsersByExperience() {
		logger.info("#####START:UserController.groupUsersByExperience#####");
		return ResponseEntity.ok(service.groupUsersByExperience());
	}


}
