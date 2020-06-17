package com.example.springdatademo.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springdatademo.entity.User;
import com.example.springdatademo.exception.UserNotFoundException;
import com.example.springdatademo.repository.UserRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
 

	@InjectMocks
	UserService userService = new UserServiceImpl();

	@Mock
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
	
	@Test
	public void testDeleteUser() throws UserNotFoundException {
		Optional<User> user1 = Optional.of(new User(101, "Ayush", "Agarwal", "Admin", 4));

		Mockito.when(mockRepository.findById(new Integer(101))).thenReturn(user1);

		userService.deleteUser(new Integer(101));
		
		Mockito.verify(mockRepository,Mockito.times(1)).delete(user1.get()) ;

	}

}
