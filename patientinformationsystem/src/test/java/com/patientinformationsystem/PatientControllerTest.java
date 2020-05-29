package com.patientinformationsystem;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patientinformationsystem.model.Patient;
import com.patientinformationsystem.service.PatientService;
import com.patientinformationsystem.service.PatientServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PatientControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

    @MockBean
    private  PatientService  patientService;
    
    @Autowired
    private WebApplicationContext wac;
    
    private ObjectMapper objectMapper = new ObjectMapper();
    

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}
	
	protected String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.writeValueAsString(obj);
	   }
	
	
	@Test
	public void createPatient() throws Exception {
		Patient patient = new Patient("1", "Geethu", new Date(15 - 06 - 1990), "sharma", "123", "ortho");
	  
	   mockMvc.perform(post("/patients/")
	        .contentType("application/json")
	        .param("Patient added successfully", "true")
	        .content(objectMapper.writeValueAsString(patient)))
	        .andExpect(status().isOk());
	}
	
	@Test
	   public void updatePatient() throws Exception {
		String uri = "/patients/2";
	      Patient  patients = new Patient();
	      patients.setName("Lemon");
	      String inputJson =mapToJson( patients);
	      MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "Patient updated successfully");
	   }
	
	
   
     
    @Test
    public void deletePatient() throws Exception {
       String uri = "/patients/4";
       MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
       int status = mvcResult.getResponse().getStatus();
       assertEquals(200, status);
       String content = mvcResult.getResponse().getContentAsString();
       assertEquals(content, "Patient deleted successfully");
    }
    

}
