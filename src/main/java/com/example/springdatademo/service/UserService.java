package com.example.springdatademo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springdatademo.dto.UserDto;
import com.example.springdatademo.entity.User;


@Service
public interface UserService {
	
	public List<User> getUsers(Integer pageNo, Integer pageSize, String sortBy);


	public void deleteUser(Integer id);

	public User updateUser(User User);

	public User getUserById(Integer id);

	List<UserDto> groupUsersByExperience();

	List<User> getUsers();

	User addUser(User demoUser);




}
