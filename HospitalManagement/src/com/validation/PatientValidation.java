package com.validation;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.app.patient.Patient;
import com.app.patient.ServicePlan;
import com.patientException.PatientException;

public class PatientValidation {
	// Maintain sets to store unique Aadhar card numbers and PAN card numbers
	private static Set<String> adharCardSet = new HashSet<>();
	private static Set<String> panCardSet = new HashSet<>();

	public static void checkForDupPatient(String email, Map<String, Patient> patientsMap) throws PatientException {
		if (patientsMap.containsKey(email))
			throw new PatientException("Duplicate Email");
	}

	public static ServicePlan parseAndValidatePlanAndCharges(String plan, double regAmount) throws PatientException {
		ServicePlan servicePlan = ServicePlan.valueOf(plan.toUpperCase());
		if (servicePlan.getminRegAmt() == regAmount) {
			return servicePlan;
		}
		throw new PatientException("Registration amount doesn't match with the chosen plan");
	}

	public static Patient validatePatientInputs(String firstName, String lastName, String email, String password,
			double phoneNo, String adharCardNo, String panCardNo, double regAmount, String dob, String plan,
			Map<String, Patient> patientsMap) throws PatientException {
		checkForDupPatient(email, patientsMap);
		ServicePlan servicePlan = parseAndValidatePlanAndCharges(plan, regAmount);
		LocalDate pdate = parseAndValidateDob(dob);
		validatePassword(password);
		validateAdhar(adharCardNo);
		validatePanNo(panCardNo);

		// Check if Aadhar card number or PAN card number already exists
		if (adharCardSet.contains(adharCardNo))
			throw new PatientException("Aadhar card number already exists");
		if (panCardSet.contains(panCardNo))
			throw new PatientException("PAN card number already exists");

		// Add the new Aadhar card number and PAN card number to the respective sets
		adharCardSet.add(adharCardNo);
		panCardSet.add(panCardNo);

		return new Patient(plan, firstName, lastName, pdate, email, password, phoneNo, adharCardNo, panCardNo, pdate,
				pdate, regAmount, servicePlan);

	}

	public static void validatePassword(String password) throws PatientException {
		// Regex pattern for password validation
		String regex = "^(?=.*[0-9].*[0-9])(?=.*[!@#$%^&*()])(?=.*[a-zA-Z]).{8,}$";

		// Check if password matches the regex pattern
		if (!password.matches(regex)) {
			throw new PatientException("Password must contain at least 8 characters, including 2 digits and 1 symbol");
		}
	}

	/*
	 * ^ asserts the start of the string. (?=.*[0-9].*[0-9]) asserts that the string
	 * contains at least 2 digits. (?=.*[!@#$%^&*()]) asserts that the string
	 * contains at least 1 symbol. (?=.*[a-zA-Z]) asserts that the string contains
	 * at least 1 alphabet. .{8,} specifies that the string must be at least 8
	 * characters long. $ asserts the end of the string.
	 * 
	 */

	public static LocalDate parseAndValidateDob(String dob) throws PatientException {
		LocalDate birthdate = LocalDate.parse(dob);
		int age = java.time.Period.between(birthdate, java.time.LocalDate.now()).getYears();
		if (age < 10)
			throw new PatientException("Invalid age!");
		return birthdate;

	}

	public static void validateAdhar(String adharCardNo) throws PatientException {
		String regex = "\\d{12}";
		if (!adharCardNo.matches(regex)) {
			throw new PatientException("Invalid Aadhar number");
		}
	}

	public static void validatePanNo(String panCardNo) throws PatientException {
		String regex = "^[A-Za-z]{3}\\d{7}$";
		if (!panCardNo.matches(regex)) {
			throw new PatientException("Invalid Pan Card Number");
		}
		// Convert panCardNo to uppercase
		panCardNo = panCardNo.toUpperCase();
	}

}
