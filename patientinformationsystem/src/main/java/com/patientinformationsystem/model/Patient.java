package com.patientinformationsystem.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="patients")
public class Patient {
	    @Id
	    private String id;
	    private String name;
	    private Date dob;
	    private String doctorsName;
	    private String doctorsId;
	    private String doctorsDept;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Date getDob() {
			return dob;
		}
		public void setDob(Date dob) {
			this.dob = dob;
		}
		public String getDoctorsName() {
			return doctorsName;
		}
		public void setDoctorsName(String doctorsName) {
			this.doctorsName = doctorsName;
		}
		public String getDoctorsId() {
			return doctorsId;
		}
		public void setDoctorsId(String doctorsId) {
			this.doctorsId = doctorsId;
		}
		public String getDoctorsDept() {
			return doctorsDept;
		}
		public void setDoctorsDept(String doctorsDept) {
			this.doctorsDept = doctorsDept;
		}
		public Patient(){
			
		}
		public Patient(String id, String name, Date dob, String doctorsName, String doctorsId, String doctorsDept) {
			this.id = id;
			this.name = name;
			this.dob = dob;
			this.doctorsName = doctorsName;
			this.doctorsId = doctorsId;
			this.doctorsDept = doctorsDept;
		}
		

}