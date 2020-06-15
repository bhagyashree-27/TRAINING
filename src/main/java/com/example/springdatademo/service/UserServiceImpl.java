package com.example.springdatademo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.example.springdatademo.dto.UserDto;
import com.example.springdatademo.entity.User;
import com.example.springdatademo.exception.UserNotFoundException;
import com.example.springdatademo.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService{
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
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
	public void deleteUser(Integer id) throws UserNotFoundException {
		logger.info("#####START:UserServiceImpl.deleteUser()#####");
		Optional<User> demoUser = repository.findById(id);
		if(!demoUser.isPresent()){
			logger.error("No User with this Id exists ");
			throw new UserNotFoundException("No User with this Id exists ");
		}
		repository.delete(demoUser.get());
		logger.info("#####END:UserServiceImpl.deleteUser()#####");
	}

	@Override
	public User updateUser(User demoUser) {
		 return repository.save(demoUser);

	}

	@Override
	public User getUserById(Integer id) throws UserNotFoundException {
		logger.info("#####START:UserServiceImpl.getUserById()#####");
		Optional<User> optional = repository.findById(id);
		if(!optional.isPresent()){
			logger.error("No User with this Id exists ");
			throw new UserNotFoundException("No User with this Id exists ");
		}
		logger.info("#####END:UserServiceImpl.getUserById()#####");
		return optional.get();
	}
	
	@Override
	public List<UserDto> groupUsersByExperience() {
		return repository.groupUsersByExperience();
	}

	@Override
	public List<User> getUsers(Integer pageNo, Integer pageSize, Integer yearsOfExperienceMin,
			Integer yearsOfExperienceMax, String sortBy) {
		Pageable page;
		Page<User> pagedResult;
		if (Strings.isBlank(sortBy))
			page = PageRequest.of(pageNo, pageSize);
		else
			page = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));


		if (yearsOfExperienceMin != null && yearsOfExperienceMax != null) {

			pagedResult = repository.findByYearsOfExperienceGreaterThanEqualAndYearsOfExperienceLessThanEqual(
					yearsOfExperienceMin, yearsOfExperienceMax, page);

		} else {
			pagedResult = repository.findAll(page);
		}

		if (pagedResult.hasContent()) {
			return pagedResult.getContent();
		} else {
			return new ArrayList<User>();
		}

	}

}
