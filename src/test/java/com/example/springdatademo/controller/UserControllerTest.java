package com.example.springdatademo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.springdatademo.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	 @Autowired
	    private MockMvc mvc;
	 
	    @MockBean
	    private UserService service;
	    
	    @Test
	    public void testGetEmployees() throws Exception
	       {
	        
	        mvc.perform(get("/users")
	          .contentType(MediaType.APPLICATION_JSON))
	          .andExpect(status().isOk());
	          
	    }

}
