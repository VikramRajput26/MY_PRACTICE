package com.app.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.app.patient.Patient;

public interface patientDao {
	Patient signIn(String email, String password) throws SQLException;

	List<Patient> getPatientDetails(Date begin, Date end) throws SQLException;

	String changePassword(String email, String oldpasswd, String newpasswd);
	String deletePatient(int patientId);
}
