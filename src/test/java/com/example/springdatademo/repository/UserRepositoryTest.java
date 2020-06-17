package com.example.springdatademo.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.springdatademo.entity.User;

@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	  @Mock
	    private UserRepository userRepository;
	  
	  
	  @Test
	  public void testGetByUserId(){
	  User found = userRepository.findById(new Integer(5)).get();
	  
	  assertEquals(found.getUserId(),new Integer(5));
	  }
}
