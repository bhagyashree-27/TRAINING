package com.example.springdatademo.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdatademo.dto.UserDto;
import com.example.springdatademo.entity.User;
import com.example.springdatademo.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService service;

	@ApiOperation("Add a new User")
	@PostMapping(produces = "application/json")
	public ResponseEntity<User> addUser(@RequestBody User demoUser) {
		return new ResponseEntity<>(service.addUser(demoUser), HttpStatus.CREATED);
	}

	@ApiOperation(value ="View the list of all Users")
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<User>> getUsers(
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "3") Integer pageSize,
            @RequestParam Integer yearsOfExperience,
            @RequestParam String sortBy
			) {
		return ResponseEntity.ok(service.getUsers(pageNo,pageSize,yearsOfExperience,sortBy));
	}

	@ApiOperation("View a User by userId")
	@GetMapping(value = "/{userId}",produces = "application/json")
	public ResponseEntity<User> getUserByuserId(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok(service.getUserById(userId));
	}

	@ApiOperation("Update a User")
	@PutMapping(value="/{userId}",produces = "application/json")
	public ResponseEntity<User> updateUser( @PathVariable("userId") Integer userId,@RequestBody User demoUser) {
		return ResponseEntity.ok(service.updateUser(demoUser));
	}
	
	@ApiOperation("Delete a User")
	@DeleteMapping("/{userId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public String deleteUser(@PathVariable("userId") Integer userId) {
		service.deleteUser(userId);
		return "Record deleted successfully";
		
	}
	
	@ApiOperation("View Users with Exp Less than or Equal")
	@GetMapping(value="/expLessThan",produces = "application/json")
	public ResponseEntity<List<User>> getUsersWithYoeLessThan(@RequestParam("") Integer value) {
		return ResponseEntity.ok(service.getUsersWithYoeLessThan(value));
	}
	@ApiOperation("View Users with Exp greater than or Equal")
	@GetMapping(value="/expGreaterThan",produces = "application/json")
	public ResponseEntity<List<User>> getUsersWithYoeGreaterThan10(@RequestParam("") Integer value) {
		return ResponseEntity.ok(service.getUsersWithYoeGreaterThan(value));
	}
	@ApiOperation("View Users with Exp Between ")
	@GetMapping(value="/expBetween",produces = "application/json")
	public ResponseEntity<List<User>> getUsersWithYoeBetween5to10(Integer value1, Integer value2) {
		return ResponseEntity.ok(service.getUsersWithYoeBetween(value1,value2));
	}
	
	
	@ApiOperation(value ="Count Users By Years Of Experience")
	@GetMapping(value= "/count",produces = "application/json")
	public ResponseEntity<List<UserDto>> groupUsersByExperience() {
		return ResponseEntity.ok(service.groupUsersByExperience());
	}


}
