package com.app.patient;

import java.time.LocalDate;

public class Patient {
	private String patientId;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String email;
	private String password;
	private double phoneNo;
	private String adharCardNo; // must be 12 digit
	private String panCardNo;
	private LocalDate admitDate;
	private LocalDate dischargeDate;
	private double regAmount;
	private ServicePlan plan;

	public Patient(String patientId, String firstName, String lastName, LocalDate dob, String email, String password,
			double phoneNo, String adharCardNo, String panCardNo, LocalDate admitDate, LocalDate dischargeDate,
			double regAmount, ServicePlan plan) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.phoneNo = phoneNo;
		this.adharCardNo = adharCardNo;
		this.panCardNo = panCardNo;
		this.admitDate = admitDate;
		this.dischargeDate = dischargeDate;
		this.regAmount = regAmount;
		this.plan = plan;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob="
				+ dob + ", email=" + email + ", password=" + password + ", phoneNo=" + phoneNo + ", adharCardNo="
				+ adharCardNo + ", panCardNo=" + panCardNo + ", admitDate=" + admitDate + ", dischargeDate="
				+ dischargeDate + ", regAmount=" + regAmount + ", plan=" + plan + "]";
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(double phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAdharCardNo() {
		return adharCardNo;
	}

	public void setAdharCardNo(String adharCardNo) {
		this.adharCardNo = adharCardNo;
	}

	public String getPanCardNo() {
		return panCardNo;
	}

	public void setPanCardNo(String panCardNo) {
		this.panCardNo = panCardNo;
	}

	public LocalDate getAdmitDate() {
		return admitDate;
	}

	public void setAdmitDate(LocalDate admitDate) {
		this.admitDate = admitDate;
	}

	public LocalDate getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(LocalDate dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public double getRegAmount() {
		return regAmount;
	}

	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}

	public ServicePlan getPlan() {
		return plan;
	}

	public void setPlan(ServicePlan plan) {
		this.plan = plan;
	}

}
