package com.patientinformationsystem.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;
import com.mongodb.MongoException;
import com.patientinformationsystem.exceptions.ResourceNotFoundException;
import com.patientinformationsystem.model.Patient;
import com.patientinformationsystem.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Patient savePatient(Patient patient) {
		Optional<Patient> patientDb = patientRepository.findById(patient.getId());

		if (patientDb.isPresent()) {
			throw new ResourceNotFoundException("Record is already present with id : " + patient.getId());

		} else {

			return patientRepository.save(patient);
		}

	}

	@Override
	public Patient updatePatient(Patient patient) {

		Optional<Patient> patientDb = this.patientRepository.findById(patient.getId());

		if (patientDb.isPresent()) {
			Patient patientUpdate = patientDb.get();
			patientUpdate.setId(patient.getId());
			patientUpdate.setName(patient.getName());
			patientUpdate.setDob(patient.getDob());
			patientUpdate.setDoctorsName(patient.getDoctorsName());
			patientUpdate.setDoctorsId(patient.getDoctorsId());
			patientUpdate.setDoctorsDept(patient.getDoctorsDept());
			patientRepository.save(patientUpdate);
			return patientUpdate;
		} else {
			throw new ResourceNotFoundException("Record not found with id : " + patient.getId());
		}
	}

	@Override
	public void deletePatient(String id) {

		Optional<Patient> patientDb = this.patientRepository.findById(id);

		if (patientDb.isPresent()) {

			patientRepository.deleteById(id);
		} else {

			throw new ResourceNotFoundException("Record not found with id : " + id);
		}
	}
}