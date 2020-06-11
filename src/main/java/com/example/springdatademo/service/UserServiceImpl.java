package com.example.springdatademo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.example.springdatademo.dto.UserDto;
import com.example.springdatademo.entity.User;
import com.example.springdatademo.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository repository;

	@Override
	public List<User> getUsers() {
		
		
		return repository.findAll();
	}

	@Override
	public User addUser(User demoUser) {
		return repository.save(demoUser);
	}

	@Override
	public void deleteUser(Integer id) {
		Optional<User> demoUser = repository.findById(id);
		repository.delete(demoUser.get());		
	}

	@Override
	public User updateUser(User demoUser) {
		 return repository.save(demoUser);

	}

	@Override
	public User getUserById(Integer id) {
		return repository.findById(id).get();
	}
	
	@Override
	public List<UserDto> groupUsersByExperience() {
		return repository.groupUsersByExperience();
	}

	@Override
	public List<User> getUsers(Integer pageNo, Integer pageSize, String sortBy) {

		Pageable pageable = PageRequest.of(pageNo, pageSize,Sort.by(sortBy));

		Page<User> pagedResult = repository.findAll(pageable);

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<User>();
		}

	
	}



}
