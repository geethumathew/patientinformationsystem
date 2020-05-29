package com.patientinformationsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patientinformationsystem.model.Patient;
import com.patientinformationsystem.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@PostMapping(value = "/")
	public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) {
		patientService.savePatient(patient);
		return new ResponseEntity("Patient added successfully", HttpStatus.OK);
	}

	@PutMapping(value = "update/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable String id, @Valid @RequestBody Patient patient) {
		patient.setId(id);
		patientService.updatePatient(patient);
		return new ResponseEntity("Patient updated successfully", HttpStatus.OK);
	}

	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable String id) {
		this.patientService.deletePatient(id);
		return new ResponseEntity("Patient deleted successfully", HttpStatus.OK);
	}

}
