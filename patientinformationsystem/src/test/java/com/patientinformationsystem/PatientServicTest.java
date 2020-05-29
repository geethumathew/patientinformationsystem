package com.patientinformationsystem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.patientinformationsystem.exceptions.ResourceNotFoundException;
import com.patientinformationsystem.model.Patient;
import com.patientinformationsystem.repository.PatientRepository;
import com.patientinformationsystem.service.PatientServiceImpl;
import static org.mockito.BDDMockito.given;

@RunWith(SpringJUnit4ClassRunner.class)
public class PatientServicTest {

	@Mock
	private PatientRepository patientRepository;
	@Mock
	private ResourceNotFoundException patientRepository1;

	@InjectMocks
	private PatientServiceImpl patientServiceImpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testsavePatient() {
		Patient patient = new Patient("1", "Geethu", new Date(15 - 06 - 1990), "sharma", "123", "ortho");
		when(patientRepository.save(patient)).thenReturn(patient);
		Patient result = patientServiceImpl.savePatient(patient);
		assertEquals("1", result.getId());

	}
	
	
	

	@Test
	public void testupdaPatient() {

		Patient patient = new Patient();
		patient.setName("geethgkbukbj");
		when(patientRepository.findById(patient.getId())).thenReturn(Optional.of(patient));
		when(patientRepository.save(patient)).thenReturn(patient);
		Patient result = patientServiceImpl.updatePatient(patient);
		assertEquals("geethgkbukbj", result.getName());

	}

	@Test
	public void testdeletePatient() {
		final String id = "1";
		final Patient patient = new Patient("1", "Geethu Dains", new Date(15 - 06 - 1990), "sharma", "123", "ortho");
		when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
		patientServiceImpl.deletePatient(id);
		Mockito.verify(patientRepository, Mockito.times(1)).deleteById(id);

	}

	@Test(expected = ResourceNotFoundException.class)
	public void testdeletePatientById() {
		final String id = "1";
		Patient patient = new Patient();
		patientServiceImpl.deletePatient(id);
		Mockito.verify(patientRepository, Mockito.times(1)).deleteById(id);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void updatePatientById() {

		Patient patient = new Patient();
		patientServiceImpl.updatePatient(patient);
		Mockito.verify(patientRepository, Mockito.times(1)).save(patient);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void savePatientBy() {
		final String id = "1";
		final Patient patient = new Patient("1", "Geethu Dains", new Date(15 - 06 - 1990), "sharma", "123", "ortho");
		when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
		patientServiceImpl.savePatient(patient);
		Mockito.verify(patientRepository, Mockito.times(1)).save(patient);
	}

}
