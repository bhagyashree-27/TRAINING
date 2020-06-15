package com.example.springdatademo.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springdatademo.entity.User;
import com.example.springdatademo.exception.UserNotFoundException;
import com.example.springdatademo.repository.UserRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

	@Autowired
	UserService userService;

	@MockBean
	private UserRepository mockRepository;

	@Test
	public void testGetUsersById() throws UserNotFoundException {
		Optional<User> user1 = Optional.of(new User(101, "Ayush", "Agarwal", "Admin", 4));

		Mockito.when(mockRepository.findById(101)).thenReturn(user1);

		User result = userService.getUserById(101);

		assertEquals("Ayush", result.getFirstName());
	}

	@Test(expected = UserNotFoundException.class)
	public void testGetUsersByIdException() throws UserNotFoundException {
		userService.getUserById(103);
	}

	@Test
	public void testGetUsers() {
		List<User> userList = new ArrayList<User>();
		User user = new User(101, "Priti", "NA", "user", 2);
		userList.add(user);

		Mockito.when(mockRepository.findAll()).thenReturn(userList);

		List<User> result = userService.getUsers();

		assertEquals(new Integer(2), result.get(0).getYearsOfExperience());

	}

	@Test
	public void testAddUsers() throws UserNotFoundException {
		User user1 = new User(101, "Ayush", "Agarwal", "Admin", 4);

		Mockito.when(mockRepository.save(user1)).thenReturn(user1);

		User result = userService.addUser(user1);

		assertEquals("Admin", result.getUserRole());
	}

}
