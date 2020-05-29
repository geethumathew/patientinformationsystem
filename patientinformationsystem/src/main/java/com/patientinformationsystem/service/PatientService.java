package com.patientinformationsystem.service;

import java.util.List;

import com.patientinformationsystem.model.Patient;

public interface PatientService {
	

	Patient savePatient(Patient patient);

	void deletePatient(String id);

	Patient updatePatient(Patient patient);

}
