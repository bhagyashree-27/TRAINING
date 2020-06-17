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
import org.springframework.web.bind.annotation.RequestHeader;
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

	/**
	 * @param logged_in_user
	 * @param demoUser
	 * @return ResponseEntity<User>
	 * @throws NumberFormatException
	 * @throws UserNotFoundException
	 */
	@ApiOperation("Add a new User")
	@PostMapping(produces = "application/json")
	public ResponseEntity<User> addUser(@RequestHeader String logged_in_user,@RequestBody  @Valid User demoUser) throws NumberFormatException, UserNotFoundException {
		
		return new ResponseEntity<>(service.addUser(demoUser), HttpStatus.CREATED);
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param yearsOfExperienceMin
	 * @param yearsOfExperienceMax
	 * @param sortBy
	 * @param logged_in_user
	 * @return ResponseEntity<List<User>>
	 */
	@ApiOperation(value ="View the list of all Users")
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<User>> getUsers(
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "5") Integer pageSize,
            @RequestParam(required=false) Integer yearsOfExperienceMin, 
            @RequestParam(required=false) Integer yearsOfExperienceMax,
            @RequestParam(defaultValue = "userId")String sortBy,
            @RequestHeader String logged_in_user
			) {
		logger.info("#####START:UserController.getUsers#####");
		return ResponseEntity.ok(service.getUsers(pageNo,pageSize,yearsOfExperienceMin,yearsOfExperienceMax,sortBy));
	}

	/**
	 * @param logged_in_user
	 * @param userId
	 * @return ResponseEntity<User>
	 * @throws UserNotFoundException
	 */
	@ApiOperation("View a User by userId")
	@GetMapping(value = "/{userId}",produces = "application/json")
	public ResponseEntity<User> getUserByUserId(@RequestHeader String logged_in_user,@PathVariable("userId") Integer userId) throws UserNotFoundException {
		logger.info("#####START:UserController.getUserByuserId#####");
		return ResponseEntity.ok(service.getUserById(userId));
	}

	/**
	 * @param logged_in_user
	 * @param userId
	 * @param demoUser
	 * @return ResponseEntity<User>
	 * @throws NumberFormatException
	 * @throws UserNotFoundException
	 */
	@ApiOperation("Update a User")
	@PutMapping(value="/{userId}",produces = "application/json")
	public ResponseEntity<User> updateUser( @RequestHeader String logged_in_user,@PathVariable("userId") Integer userId,@RequestBody  @Valid User demoUser) throws NumberFormatException, UserNotFoundException {
		logger.info("#####START:UserController.updateUser#####");
		return ResponseEntity.ok(service.updateUser(demoUser));
	}
	
	/**
	 * @param logged_in_user
	 * @param userId
	 * @return String
	 * @throws UserNotFoundException
	 */
	@ApiOperation("Delete a User")
	@DeleteMapping("/{userId}")
	public String deleteUser(@RequestHeader String logged_in_user,@PathVariable("userId") Integer userId) throws UserNotFoundException {
		logger.info("#####START:UserController.deleteUser#####");
		service.deleteUser(userId);
		return "Record deleted successfully";
		
	}
	
	/**
	 * @return ResponseEntity<List<UserDto>>
	 */
	@ApiOperation(value ="Count Users By Years Of Experience")
	@GetMapping(value= "/count",produces = "application/json")
	public ResponseEntity<List<UserDto>> groupUsersByExperience() {
		logger.info("#####START:UserController.groupUsersByExperience#####");
		return ResponseEntity.ok(service.groupUsersByExperience());
	}


}
