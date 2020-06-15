package com.example.springdatademo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springdatademo.dto.UserDto;
import com.example.springdatademo.entity.User;
import com.example.springdatademo.exception.UserNotFoundException;


@Service
public interface UserService {

	public void deleteUser(Integer id) throws UserNotFoundException;

	public User updateUser(User User);

	public User getUserById(Integer id) throws UserNotFoundException;

	List<UserDto> groupUsersByExperience();

	List<User> getUsers();

	User addUser(User demoUser);

	public List<User> getUsers(Integer pageNo, Integer pageSize, Integer yearsOfExperience, Integer yearsOfExperienceMax, String sortBy);


}
