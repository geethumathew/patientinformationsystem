package com.patientinformationsystem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.patientinformationsystem.model.Patient;

public interface PatientRepository extends MongoRepository<Patient, String> {
	
	
	
	   

}
