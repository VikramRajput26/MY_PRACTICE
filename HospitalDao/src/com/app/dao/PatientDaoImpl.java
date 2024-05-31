package com.app.dao;

import static com.app.utils.DBUtils.openConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.app.patient.Patient;

public class PatientDaoImpl implements patientDao{
	private Connection cn;
	private PreparedStatement pst1 , pst2 , pst3;
//	private PreparedStatement pst1, pst2, pst3, pst4, pst5;

	// def ctor of the DAO layer
	public PatientDaoImpl() throws SQLException {
		
		cn = openConnection();
		pst1=cn.prepareStatement("select * from patient where email=? and password=?");
		
		
		
	}

	@Override
	public Patient signIn(String email, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> getPatientDetails(Date begin, Date end) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changePassword(String email, String oldpasswd, String newpasswd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deletePatient(int patientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
