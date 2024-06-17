package com.java.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.java.model.owner.Owner;
import com.java.service.OwnerService;
import static org.hamcrest.Matchers.is;



import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class OwnerTestController {

	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private OwnerService ownerService ;
	
	@InjectMocks
	private OwnerController ownerController ;
	
	
	
	Owner owner1 = new Owner();
	
	Owner owner2 = new Owner();
	
	
	@Test
	void testFindAllOwner() throws Exception {
        // Mock the service response
		

       Mockito.when(ownerService.findAllOwner()).thenReturn(Arrays.asList(owner1, owner2));

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders
			   .get("/api/v1/owners").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", notNullValue()));
               
    }
	
	@Test
	void testGetOwnerById() throws Exception {
        // Mock the service response
		
       Mockito.when(ownerService.findOwnerById(1L)).thenReturn(owner1);

        // Perform the GET request
        mockMvc.perform(MockMvcRequestBuilders
			   .get("/api/v1/owners/1").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
               
    }
	
}
